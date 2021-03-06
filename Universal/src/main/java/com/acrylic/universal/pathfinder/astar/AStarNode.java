package com.acrylic.universal.pathfinder.astar;

import com.acrylic.universal.pathfinder.PathNode;
import org.bukkit.Location;
import org.bukkit.block.Block;

public final class AStarNode extends PathNode {

    private final float gCost;
    private final float hCost;
    private AStarNode parent;

    protected AStarNode(int index, Location start, Location end, Location location) {
        super(location, index);
        this.gCost = (float) getDistanceSquared(start);
        this.hCost = (float) getDistanceSquared(end);
    }

    protected AStarNode(int index, AStarTraverser traverser, Location location) {
        super(location, index);
        this.gCost = (float) getDistanceSquared(traverser.getStart());
        this.hCost = (float) getDistanceSquared(traverser.getEnd());
    }

    protected AStarNode(AStarNode parent, AStarTraverser traverser, Location location) {
        this(parent.getIndex() + 1, traverser, location);
        this.parent = parent;
    }

    public AStarNode getParent() {
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

    public boolean equals(AStarNode o) {
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
