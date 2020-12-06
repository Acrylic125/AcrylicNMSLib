package com.acrylic.universal.entityai;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockExaminer {

    boolean shouldClimb();

    boolean canPass(@Nullable Material material);

    static boolean isAir(@Nullable Material material) {
        return material == null || material.equals(Material.AIR);
    }

    static boolean isLiquid(@NotNull Material material) {
        return material.equals(Material.LAVA) || material.equals(Material.WATER);
    }

    static boolean isClimbable(@NotNull Material material) {
        return material.equals(Material.LADDER) || material.equals(Material.VINE);
    }

    static boolean isWalkable(@NotNull Block block) {
        Material down = block.getRelative(BlockFace.DOWN).getType();
        Material on = block.getType();
        Material up = block.getRelative(BlockFace.UP).getType();
        return !isAir(down) && isAir(up) && (isAir(on) || isLiquid(on));
    }

}
