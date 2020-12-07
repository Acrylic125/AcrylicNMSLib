package com.acrylic.universal.pathfinder.astar;

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
    private final AStarNode start;
    private final AStarNode end;
    private final Map<Integer, AStarNode> openNodes = new HashMap<>();
    private final Map<Integer, AStarNode> closedNodes = new HashMap<>();

    public AStarTraverser(PathGenerator pathGenerator, Block start, Block end) {
        this.start = addNodeTo(openNodes, new AStarNode(0, start, end, start));
        this.end = new AStarNode(-1, start, end, end);
        this.pathGenerator = pathGenerator;
    }

    public AStarNode getStart() {
        return start;
    }

    public AStarNode getEnd() {
        return end;
    }

    /** HELPER METHODS **/
    private int computeHash(double a, int hash) {
        long b = Double.doubleToLongBits(a);
        return (int) (19 * hash + (b ^ b >>> 32));
    }

    /**
     * This is used as an identifier.
     *
     * It is adapted from the {@link Location} hashcode.
     */
    private int getID(@NotNull Location location) {
        int hash = 3;
        hash = computeHash(location.getX(), hash);
        hash = computeHash(location.getY(), hash);
        hash = computeHash(location.getZ(), hash);
        return hash;
    }

    private void removeNodeFrom(@NotNull Map<Integer, AStarNode> map, @NotNull AStarNode node) {
        map.remove(getID(node.getLocation()));
    }

    @NotNull
    private AStarNode addNodeTo(@NotNull Map<Integer, AStarNode> map, @NotNull AStarNode node) {
        map.put(getID(node.getLocation()), node);
        return node;
    }

    @Nullable
    private AStarNode getNodeFrom(@NotNull Map<Integer, AStarNode> map, @NotNull Block block) {
        return map.get(getID(block.getLocation()));
    }
    /** END OF HELPERS **/

    @Override
    public PathGenerator getPathGenerator() {
        return pathGenerator;
    }

    private void checkNode(@NotNull Block block, @NotNull AStarNode parent) {
        AStarNode checkNode = getNodeFrom(openNodes, block);
        AStarNode newNode = new AStarNode(parent, this, block);
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

    @Override
    public void traverse() {
        int i = 0; //Iterations
        AStarNode computed = null;
        do {
            AStarNode closest = getClosestNode();
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
                    checkNode(block, closest);
                }
            }
            addNodeTo(closedNodes, closest);
            computed = closest;
        } while (!openNodes.isEmpty() && i <= pathGenerator.getLookUpThreshold());
        computeLocations(computed);
    }

    private void computeLocations(@Nullable AStarNode computed) {
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

    private AStarNode getClosestNode() {
        AStarNode currentNode = null;
        for (AStarNode node : openNodes.values()) {
            if (currentNode == null || currentNode.getFCost() > node.getFCost())
                currentNode = node;
        }
        return currentNode;
    }

}
