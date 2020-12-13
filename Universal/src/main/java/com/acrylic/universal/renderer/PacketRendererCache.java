package com.acrylic.universal.renderer;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface PacketRendererCache {

    List<UUID> getCached();

    default void add(@NotNull UUID uuid) {
        getCached().add(uuid);
    }

    default void add(@NotNull Player player) {
        add(player.getUniqueId());
    }

    default void remove(@NotNull UUID uuid) {
        getCached().remove(uuid);
    }

    default void remove(@NotNull Player player) {
        remove(player.getUniqueId());
    }

}
