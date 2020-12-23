package com.acrylic.universal.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class to retrieve the hash code for locations
 * and blocks. This is meant to use for identification.
 *
 * @see com.acrylic.universal.pathfinder.astar.AStarTraverser for a use case.
 */
public class BukkitHashCode {

    private static int computeHash(double a, int hash) {
        long b = Double.doubleToLongBits(a);
        return (int) (19 * hash + (b ^ b >>> 32));
    }

    /**
     * This is used as an identifier.
     *
     * It is adapted from the {@link Location} hashcode.
     */
    public static int getHashCode(@NotNull Location location) {
        return getHashCode(location, false);
    }

    public static int getHashCode(@NotNull Location location, boolean withWorld) {
        int hash = 3;
        hash = computeHash(location.getX(), hash);
        hash = computeHash(location.getY(), hash);
        hash = computeHash(location.getZ(), hash);
        if (withWorld)
            hash = computeHash(location.getWorld().hashCode(), hash);
        return hash;
    }

    public static int getHashCode(@NotNull Block block) {
        return getHashCode(block, false);
    }

    public static int getHashCode(@NotNull Block block, boolean withWorld) {
        int hash = 3;
        hash = computeHash(block.getX(), hash);
        hash = computeHash(block.getY(), hash);
        hash = computeHash(block.getZ(), hash);
        if (withWorld)
            hash = computeHash(block.getWorld().hashCode(), hash);
        return hash;
    }

}
