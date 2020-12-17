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

    public Block getWalkableBlock(Block block) {
        Location location = block.getLocation();
        double y = location.getY();
        PathGenerator pathGenerator = getPathGenerator();
        Block result = null;
        for (int i = -pathGenerator.getSearchDownAmount(); i <= pathGenerator.getSearchUpAmount(); i++) {
            location.setY(y + i);
            Block blockCheck = location.getBlock();
            BlockExaminer.NavigationStyle blockStyle = pathGenerator.getBlockExaminer().getNavigationStyle(blockCheck);
            if (blockStyle != null && !blockStyle.equals(BlockExaminer.NavigationStyle.NONE))
                result = blockCheck;
        }
        return result;
    }

}
