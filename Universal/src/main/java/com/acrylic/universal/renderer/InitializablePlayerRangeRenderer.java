package com.acrylic.universal.renderer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class InitializablePlayerRangeRenderer
        implements InitializerLocationalRenderer, RangedPacketRenderer, RendererCache {

    private final List<UUID> stored;
    private float range = 30;
    private Location location;
    private Consumer<Player> initializeAction;
    private Consumer<Player> terminationAction;

    public InitializablePlayerRangeRenderer() {
        this(new LinkedList<>());
    }

    public InitializablePlayerRangeRenderer(@NotNull List<UUID> stored) {
        this.stored = stored;
    }

    public Consumer<Player> getTerminationAction() {
        return terminationAction;
    }

    public void setTerminationAction(@NotNull Consumer<Player> terminationAction) {
        this.terminationAction = terminationAction;
    }

    @Override
    public boolean canInitialize(@NotNull Player player) {
        return !stored.contains(player.getUniqueId()) && isPlayerWithinRange(player);
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
    public void terminate(@NotNull Collection<? extends Player> players) {
        for (Player player : players)
            terminate(player);
    }

    @Override
    public void terminateWithCache(@NotNull RendererCache rendererCache) {
        iterateUUIDs(getCached(), this::terminate);
    }

    @Override
    public void terminateAll() {
        terminateWithCache(this);
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
            if (canInitialize(onlinePlayer))
                render(onlinePlayer);
        }
    }

    @Override
    public void initialize(@NotNull Player player) {
        initializeAction.accept(player);
    }

    @Override
    public void initialize(@NotNull Collection<? extends Player> players) {
        for (Player player : players)
            initialize(player);
    }

    @Override
    public void initializeWithRendererCache(@NotNull RendererCache rendererCache) {
        iterateUUIDs(getCached(), this::initialize);
    }

    @Override
    public void initializeAll() {
        initializeWithRendererCache(this);
    }

    @Override
    public void render(@NotNull Player player) {
        synchronized (stored) {
            stored.add(player.getUniqueId());
            initialize(player);
        }
    }

    @Override
    public void render(@NotNull Collection<? extends Player> players) {
        for (Player player : players)
            render(player);
    }

    @Override
    public void renderWithRendererCache(@NotNull RendererCache rendererCache) {
        iterateUUIDs(getCached(), this::render);
    }

    @Override
    public void unrender(@NotNull Player player) {
        synchronized (stored) {
            stored.remove(player.getUniqueId());
            terminate(player);
        }
    }

    @Override
    public void unrender(@NotNull Collection<? extends Player> players) {
        for (Player player : players)
            unrender(player);
    }

    @Override
    public void unrenderWithRendererCache(@NotNull RendererCache rendererCache) {
        iterateUUIDs(getCached(), this::unrender);
    }

    @Override
    public void unrenderAll() {
        synchronized (stored) {
            iterateUUIDs(stored, this::terminate);
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

    @Override
    public void handle(@NotNull Consumer<Player> action) {
        iterateUUIDs(getCached(), action);
    }

    private void iterateUUIDs(@NotNull Collection<UUID> uuids, @NotNull Consumer<Player> action) {
        for (UUID uuid : uuids) {
            Player player = Bukkit.getPlayer(uuid);
            if (isPlayerOnline(player))
                action.accept(player);
        }
    }

}
