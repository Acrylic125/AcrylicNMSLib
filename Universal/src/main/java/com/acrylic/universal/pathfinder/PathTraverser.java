package com.acrylic.universal.pathfinder;

import org.bukkit.Location;
import org.bukkit.block.Block;

public abstract class PathTraverser {

    private Location[] computedLocations;

    public Location[] getComputedLocations() {
        return computedLocations;
    }

    public void setComputedLocations(Location[] locations) {
        this.computedLocations = locations;
    }

    public abstract void traverse();

    public abstract PathGenerator getPathGenerator();

    public Block getWalkable(Block block) {
        Location location = block.getLocation();
        double y = location.getY();
        PathGenerator pathGenerator = getPathGenerator();
        for (int i = -pathGenerator.getSearchDownAmount(); i <= pathGenerator.getSearchUpAmount(); i++) {
            location.setY(y + i);
            Block blockCheck = location.getBlock();
            if (BlockExaminer.isWalkable(blockCheck))
                return blockCheck;
        }
        return null;
    }

}
