package com.acrylic.universal.renderer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PlayerRangeRenderer implements RangedPacketRenderer, Renderer {

    private final List<Player> viewers;
    private Location location;
    private float range = 32;

    public PlayerRangeRenderer(@NotNull Location location, float range, boolean shouldScanUponCreation) {
        this(location, new ArrayList<>());
        this.range = range;
        if (shouldScanUponCreation)
            scan();
    }

    public PlayerRangeRenderer(@NotNull Location location) {
        this(location, new ArrayList<>());
    }

    public PlayerRangeRenderer(@NotNull Location location, @NotNull List<Player> viewers) {
        this.viewers = viewers;
        this.location = location;
    }

    public void scan() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (isPlayerWithinRange(onlinePlayer))
                viewers.add(onlinePlayer);
        }
    }

    @Override
    public void setLocation(@NotNull Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
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
    public boolean isPlayerWithinRange(@Nullable Player player) {
        return location != null && isPlayerOnline(player) && location.distanceSquared(player.getLocation()) <= getSquaredRange();
    }

    @Override
    public void handle(@NotNull Consumer<Player> action) {
        viewers.forEach(action);
    }
}
