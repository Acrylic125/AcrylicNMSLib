package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerRangeRenderer implements PacketRenderer {

    private float range = 32;
    private ArrayList<UUID> storedIds = new ArrayList<>();

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
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
}
