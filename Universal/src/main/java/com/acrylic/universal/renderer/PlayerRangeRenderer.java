package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class PlayerRangeRenderer implements PacketRenderer {

    private float range = 32;
    private ArrayList<UUID> storedIds = new ArrayList<>();
    private Consumer<Player> deleteAction;

    public Consumer<Player> getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(Consumer<Player> deleteAction) {
        this.deleteAction = deleteAction;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public synchronized void check(@NotNull final Location location) {
        checkRemoval(location);
        checkUsers(location);
    }

    public void checkRemoval(@NotNull final Location location) {
        final float radius = getRange() * getRange();
        storedIds.iterator().forEachRemaining(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if (!checkUser(player, radius, location)) {
                if (player != null && deleteAction != null)
                    deleteAction.accept(player);
                storedIds.remove(uuid);
            }
        });
    }

    public void checkUsers(@NotNull final Location location) {
        final float radius = getRange() * getRange();
        final ArrayList<UUID> storedIds = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (checkUser(player, radius, location))
                storedIds.add(player.getUniqueId());
        });
        this.storedIds = storedIds;
    }

    private boolean checkUser(@Nullable Player player, float radiusSquared, @NotNull Location location) {
        return (player != null && player.isOnline() && player.getLocation().distanceSquared(location) <= radiusSquared);
    }

    @Override
    public void send(PacketSender packetSender) {
        for (UUID uuid : storedIds) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null)
                packetSender.send(player);
        }
    }

    @Override
    public void sendWithAction(PacketSender packetSender, @NotNull Consumer<Player> sendWithAction) {
        for (UUID uuid : storedIds) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null)
                packetSender.sendWithAction(player, sendWithAction);
        }
    }
}
