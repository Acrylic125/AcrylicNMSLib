package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class PlayerRangeRenderer
        implements EntityRenderer, RangedPacketRenderer, PacketRendererCache {

    private final List<UUID> stored = new ArrayList<>();
    private float range = 30;
    private Location location;
    private Consumer<Player> initializeAction;
    private Consumer<Player> terminationAction;

    public Consumer<Player> getTerminationAction() {
        return terminationAction;
    }

    public void setTerminationAction(@NotNull Consumer<Player> terminationAction) {
        this.terminationAction = terminationAction;
    }

    @Override
    public boolean hasInitialized(@NotNull UUID uuid) {
        return stored.contains(uuid);
    }

    @Override
    public void checkTermination() {
        final Iterator<UUID> each = stored.iterator();
        while (each.hasNext()) {
            UUID uuid = each.next();
            if (!isPlayerWithinRange(uuid)) {
                each.remove();
                Player player = Bukkit.getPlayer(uuid);
                if (player != null) {
                    terminate(player);
                }
            }
        }
    }

    @Override
    public void terminate(@NotNull Player player) {
        terminationAction.accept(player);
    }

    @Override
    public void setInitializationAction(@NotNull Consumer<Player> initializationAction) {
        this.initializeAction = initializationAction;
    }

    @Override
    public Consumer<Player> getInitializationAction() {
        return initializeAction;
    }

    @Override
    public void checkInitialization() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            UUID id = onlinePlayer.getUniqueId();
            if (!stored.contains(id) && isPlayerWithinRange(onlinePlayer)) {
                stored.add(id);
                initialize(onlinePlayer);
            }
        }
    }

    @Override
    public void initialize(@NotNull Player player) {
        initializeAction.accept(player);
    }

    @Override
    public void send(PacketSender packetSender) {
        for (UUID uuid : stored) {
            Player player = Bukkit.getPlayer(uuid);
            if (isPlayerOnline(player))
                packetSender.send(player);
        }
    }

    @Override
    public void sendWithAction(PacketSender packetSender, @NotNull Consumer<Player> sendWithAction) {
        for (UUID uuid : stored) {
            Player player = Bukkit.getPlayer(uuid);
            if (isPlayerOnline(player))
                packetSender.sendWithAction(player, sendWithAction);
        }
    }

    @Override
    public List<UUID> getCached() {
        return stored;
    }

    @Override
    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public void setLocation(@NotNull Location location) {
        this.location = location;
    }

    @Nullable
    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    @SuppressWarnings("all")
    public boolean isPlayerWithinRange(@Nullable Player player) {
        return location != null && isPlayerOnline(player) && (System.currentTimeMillis() - player.getLastPlayed()) >= 3000 && location.distanceSquared(player.getLocation()) <= getSquaredRange();
    }
}
