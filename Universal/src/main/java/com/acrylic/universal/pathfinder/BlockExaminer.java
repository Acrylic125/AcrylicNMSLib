package com.acrylic.universal.pathfinder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BlockExaminer {

    SimpleBlockExaminer SIMPLE_BLOCK_EXAMINER = new SimpleBlockExaminer();

    enum NavigationStyle {
        CASUAL_SWIM(3), SWIM(4), WALK(1), CLIMB(4), NONE(-1);

        private final int weight;

        NavigationStyle(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    boolean isDoor(@NotNull Block block);

    boolean isFenceGate(@NotNull Block block);

    boolean isTraversable(@NotNull Block block);

    boolean isLiquid(@NotNull Block block);

    boolean isClimbable(@NotNull Block block);

    NavigationStyle getNavigationStyle(@NotNull Block block);

    boolean canPassThrough(@NotNull Block block);

    static boolean isAir(@Nullable Material material) {
        return material == null || material.equals(Material.AIR);
    }

    static boolean isLiquid(@NotNull Material material) {
        return material.equals(Material.STATIONARY_LAVA) || material.equals(Material.LAVA) || material.equals(Material.STATIONARY_WATER) || material.equals(Material.WATER);
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
