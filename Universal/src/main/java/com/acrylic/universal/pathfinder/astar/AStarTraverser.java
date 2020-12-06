package com.acrylic.universal.pathfinder.astar;

import com.acrylic.universal.pathfinder.BlockExaminer;
import com.acrylic.universal.pathfinder.PathGenerator;
import com.acrylic.universal.pathfinder.PathTraverser;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class AStarTraverser extends PathTraverser {

    private final PathGenerator pathGenerator;
    private final Node start;
    private final Node end;
    private final Map<Integer, Node> openNodes = new HashMap<>();
    private final Map<Integer, Node> closedNodes = new HashMap<>();

    public AStarTraverser(PathGenerator pathGenerator, Block start, Block end) {
        this.start = addNodeTo(openNodes, new Node(0, start, end, start));
        this.end = new Node(-1, start, end, end);
        this.pathGenerator = pathGenerator;
        traverse();
    }

    private int getID(Location location) {
        return location.hashCode();
    }

    private void removeNodeFrom(@NotNull Map<Integer, Node> map, @NotNull Node node) {
        map.remove(getID(node.getLocation()));
    }

    @NotNull
    private Node addNodeTo(@NotNull Map<Integer, Node> map, @NotNull Node node) {
        map.put(getID(node.getLocation()), node);
        return node;
    }

    @Nullable
    private Node getNodeFrom(@NotNull Map<Integer, Node> map, @NotNull Block block) {
        return map.get(getID(block.getLocation()));
    }

    @Override
    public PathGenerator getPathGenerator() {
        return pathGenerator;
    }

    @Override
    public void traverse() {
        int i = 0;
        Node computed = null;
        main: do {
            Node closest = getClosestNode();
            if (closest == null)
                break;
            if (closest.equals(end)) {
                computed = closest;
                break;
            }
            i++;
            Block referenceBlock = closest.getLocation().getBlock();
            removeNodeFrom(openNodes, closest);
            for (BlockFace face : pathGenerator.getLookUpFaces()) {
                Block block = getWalkableBlock(referenceBlock.getRelative(face));
                if (block != null) {
                    Node checkNode = getNodeFrom(openNodes, block);
                    Node newNode = new Node(closest, this, block);
                    if (newNode.equals(end)) {
                        computed = newNode;
                        break main;
                    }
                    float current_cost = newNode.getFCost();
                    if (checkNode != null) {
                        if (checkNode.getFCost() > current_cost)
                            addNodeTo(closedNodes, newNode);
                    } else {
                        checkNode = getNodeFrom(closedNodes, block);
                        if (checkNode != null) {
                            if (checkNode.getFCost() > current_cost)
                                addNodeTo(closedNodes, newNode);
                        } else
                            addNodeTo(openNodes, newNode);
                    }
                }
            }
            addNodeTo(closedNodes, closest);
            computed = closest;
        } while (!openNodes.isEmpty() && i <= pathGenerator.getLookUpThreshold());
        computeLocations(computed);
    }

    private void computeLocations(@Nullable Node computed) {
        Location[] computedLocations;
        if (computed != null) {
            computedLocations = new Location[computed.getIndex() + 1];
            do {
                computedLocations[computed.getIndex()] = computed.getLocation();
                computed = computed.getParent();
            } while (computed != null);
        } else
            computedLocations = new Location[0];
        setComputedLocations(computedLocations);
    }

    private Node getClosestNode() {
        Node currentNode = null;
        for (Node node : openNodes.values()) {
            if (currentNode == null || currentNode.getFCost() > node.getFCost())
                currentNode = node;
        }
        return currentNode;
    }

    public static class Node {

        private final float gCost;
        private final float hCost;
        private final Location location;
        private final int index;
        private Node parent;

        public Node(int index, Block start, Block end, Block block) {
            this.index = index;
            this.location = block.getLocation();
            this.gCost = (float) start.getLocation().distanceSquared(location);
            this.hCost = (float) end.getLocation().distanceSquared(location);
        }

        public Node(int index, AStarTraverser aStarTest, Block block) {
            this.index = index;
            this.location = block.getLocation();
            this.gCost = (float) aStarTest.start.getLocation().distanceSquared(location);
            this.hCost = (float) aStarTest.end.getLocation().distanceSquared(location);
        }

        public Node(Node parent, AStarTraverser aStarTest, Block location) {
            this(parent.getIndex() + 1, aStarTest, location);
            this.parent = parent;
        }

        public int getIndex() {
            return index;
        }

        public Node getParent() {
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

        public Location getLocation() {
            return location;
        }

        public boolean equals(Node o) {
            return equals(o.getLocation());
        }

        public boolean equals(Block o) {
            return equals(o.getLocation());
        }

        public boolean equals(Location o) {
            Location l = getLocation();
            return (o != null) && o.getWorld().equals(l.getWorld()) &&
                    o.getX() == l.getX() && o.getY() == l.getY() && o.getZ() == l.getZ();
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }

    }

}
