package com.acrylic.universal.pathfinder;

import org.bukkit.Location;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

public abstract class PathNode {

    private final Location location;
    private final int index;

    public PathNode(Location location, int index) {
        this.location = location;
        this.index = index;
    }

    public double getDistanceSquared(@NotNull PathNode node) {
        return getDistanceSquared(node.getLocation());
    }

    /**
     * This method makes the assumption that the world of location is
     * the same as the node location's world.
     *
     * @param location The location.
     * @return The distance.
     */
    public double getDistanceSquared(@NotNull final Location location) {
        return Math.sqrt(NumberConversions.square(this.location.getX() - location.getX()) +
                        NumberConversions.square(this.location.getY() - location.getY()) +
                 NumberConversions.square(this.location.getZ() - location.getZ()));
    }


    public Location getLocation() {
        return location;
    }

    public int getIndex() {
        return index;
    }
}
