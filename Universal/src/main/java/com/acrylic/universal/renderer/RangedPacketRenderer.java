package com.acrylic.universal.renderer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface RangedPacketRenderer extends LocationalRenderer {

    void setRange(float range);

    float getRange();

    default float getSquaredRange() {
        float range = getRange();
        return range * range;
    }

    default boolean isPlayerOnline(@Nullable Player player) {
        return player != null && player.isOnline();
    }

    boolean isPlayerWithinRange(@Nullable Player player);

    default boolean isPlayerWithinRange(@NotNull UUID uuid) {
        return isPlayerWithinRange(Bukkit.getPlayer(uuid));
    }

}
