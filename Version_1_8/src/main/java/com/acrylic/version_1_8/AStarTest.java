package com.acrylic.version_1_8;

import com.acrylic.universal.pathfinder.BlockExaminer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * This is only an experimental class. Please do
 * not use.
 *
 * @see com.acrylic.universal.pathfinder.astar.AStarGenerator
 */
@Setter @Getter @Deprecated
public class AStarTest {

    private static final BlockFace[] faces = new BlockFace[] {
       BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST
    } ;

    int y1 = -3;
    int y2 = 3;
    int maxThreshold = 50;
    private Node start;
    private Node end;
    private Node computed;
    private Map<Integer, Node> openNodes = new HashMap<>();
    private Map<Integer, Node> closedNodes = new HashMap<>();

    public AStarTest(Block start, Block end) {
        this.start = addNodeTo(openNodes, new Node(start, end, start));
        this.end = new Node(start, end, end);
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    private void removeNodeFrom(Map<Integer, Node> map, @NotNull Node node) {
        map.remove(node.getLocation().hashCode());
    }

    @NotNull
    private Node addNodeTo(Map<Integer, Node> map, @NotNull Node node) {
        map.put(node.getLocation().hashCode(), node);
        return node;
    }

    @Nullable
    private Node getNodeFrom(Map<Integer, Node> map, Block block) {
        return map.get(block.getLocation().hashCode());
    }

   public void traverse() {
        int i = 0;
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
            for (BlockFace face : faces) {
                Block block = getWalkable(referenceBlock.getRelative(face));
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
                        } else {
                            addNodeTo(openNodes, newNode);
                        }
                    }
                }
            }
            addNodeTo(closedNodes, closest);
            computed = closest;
        } while (!openNodes.isEmpty() && i <= maxThreshold);
   }

    private Block getWalkable(Block block) {
        Location location = block.getLocation();
        float y = (float) location.getY();
        for (int i = y1; i <= y2; i++) {
            location.setY(y + i);
            Block blockCheck = location.getBlock();
            if (BlockExaminer.isWalkable(blockCheck))
                return blockCheck;
        }
        return null;
    }

   public void climb(Consumer<Location> action) {
        Node node = computed;
        do {
            if (node != null) {
                action.accept(node.getLocation());
                node = node.getParent();
            }
        } while (node != null);
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

        private Node parent;
        private final float gCost;
        private final float hCost;
        private final Location location;

        public Node(Block start, Block end, Block block) {
            this.location = block.getLocation();
            this.gCost = (float) start.getLocation().distanceSquared(location);
            this.hCost = (float) end.getLocation().distanceSquared(location);
        }

        public Node(AStarTest aStarTest, Block block) {
            this.location = block.getLocation();
            this.gCost = (float) aStarTest.getStart().getLocation().distanceSquared(location);
            this.hCost = (float) aStarTest.getEnd().getLocation().distanceSquared(location);
        }

        public Node(Node parent, AStarTest aStarTest, Block location) {
            this(aStarTest, location);
            this.parent = parent;
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
            return gCost + hCost;
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
