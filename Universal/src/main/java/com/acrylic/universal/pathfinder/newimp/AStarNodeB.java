package com.acrylic.universal.pathfinder.newimp;

import com.acrylic.universal.pathfinder.PathNode;
import org.bukkit.Location;
import org.bukkit.block.Block;

public final class AStarNodeB extends PathNode {

    private final float gCost;
    private final float hCost;
    private AStarNodeB parent;

    protected AStarNodeB(int index, Location start, Location end, Location location) {
        super(location, index);
        this.gCost = (float) getDistanceSquared(start);
        this.hCost = (float) getDistanceSquared(end);
    }

    protected AStarNodeB(int index, AStarTraverserB traverser, Location location) {
        super(location, index);
        this.gCost = (float) getDistanceSquared(traverser.getStart());
        this.hCost = (float) getDistanceSquared(traverser.getEnd());
    }

    protected AStarNodeB(AStarNodeB parent, AStarTraverserB traverser, Location location) {
        this(parent.getIndex() + 1, traverser, location);
        this.parent = parent;
    }

    public AStarNodeB getParent() {
        return parent;
    }

    public float getGCost() {
        return gCost;
    }

    public float getHCost() {
        return hCost;
    }

    public float getFCost() {
        return (gCost + hCost);
    }

    public boolean equals(AStarNodeB o) {
        return equals(o.getLocation());
    }

    public boolean equals(Block o) {
        return equals(o.getLocation());
    }

    public boolean equals(Location o) {
        Location l = getLocation();
        return (o != null) &&
                o.getX() == l.getX() &&
                o.getY() == l.getY() &&
                o.getZ() == l.getZ();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

}
