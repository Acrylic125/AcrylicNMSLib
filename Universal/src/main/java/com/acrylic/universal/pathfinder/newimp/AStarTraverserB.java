package com.acrylic.universal.pathfinder.newimp;

import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.universal.pathfinder.PathFace;
import com.acrylic.universal.pathfinder.PathGenerator;
import com.acrylic.universal.pathfinder.PathTraverser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public final class AStarTraverserB extends PathTraverser {

    private final PathGenerator pathGenerator;
    private final AStarNodeB start;
    private final AStarNodeB end;
    private final Map<Integer, AStarNodeB> openNodes = new HashMap<>();
    private final Map<Integer, AStarNodeB> closedNodes = new HashMap<>();

    public AStarTraverserB(PathGenerator pathGenerator, Location start, Location end) {
        this.start = addNodeTo(openNodes, new AStarNodeB(0, start, end, start));
        this.end = new AStarNodeB(-1, start, end, end);
        this.pathGenerator = pathGenerator;
    }

    public AStarNodeB getStart() {
        return start;
    }

    public AStarNodeB getEnd() {
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

    private void removeNodeFrom(@NotNull Map<Integer, AStarNodeB> map, @NotNull AStarNodeB node) {
        map.remove(getID(node.getLocation()));
    }

    @NotNull
    private AStarNodeB addNodeTo(@NotNull Map<Integer, AStarNodeB> map, @NotNull AStarNodeB node) {
        map.put(getID(node.getLocation()), node);
        return node;
    }

    @Nullable
    private AStarNodeB getNodeFrom(@NotNull Map<Integer, AStarNodeB> map, @NotNull Location location) {
        return map.get(getID(location));
    }
    /** END OF HELPERS **/

    @Override
    public PathGenerator getPathGenerator() {
        return pathGenerator;
    }

    private void checkNode(@NotNull Location location, @NotNull AStarNodeB parent) {
        AStarNodeB checkNode = getNodeFrom(openNodes, location);
        AStarNodeB newNode = new AStarNodeB(parent, this, location);
        float current_cost = newNode.getFCost();
        if (checkNode != null) {
            if (checkNode.getFCost() > current_cost)
                addNodeTo(closedNodes, newNode);
        } else {
            checkNode = getNodeFrom(closedNodes, location);
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
        AStarNodeB computed = null;
        BoundingBoxExaminer boundingBoxExaminer = pathGenerator.getBlockExaminer().getBoundingBoxExaminer();
        do {
            AStarNodeB closest = getClosestNode();
            if (closest == null)
                break;
            if (closest.equals(end)) {
                computed = closest;
                break;
            }
            i++;
            Location loc = closest.getLocation();
            loc.setY(loc.getY() - 1);
            if (boundingBoxExaminer.examine(loc))
                loc.setY(boundingBoxExaminer.getMaxY());
            else
                loc.setY(loc.getY() + 1);
            //
            Block referenceBlock = loc.getBlock();
            removeNodeFrom(openNodes, closest);
            int bitMask = 0x000;
            for (PathFace pathFace : PathFace.PATH_FACES) {
                if (pathFace.isBlocked(bitMask))
                    continue;
                Block block = getWalkableBlock(referenceBlock.getRelative(pathFace.getFace()));
                if (block != null)
                    checkNode(block.getLocation(), closest);
                else
                    bitMask = pathFace.addToMask(bitMask);
            }
            addNodeTo(closedNodes, closest);
            computed = closest;
        } while (!openNodes.isEmpty() && i <= pathGenerator.getLookUpThreshold());
        computeLocations(computed);
    }

    private void computeLocations(@Nullable AStarNodeB computed) {
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

    private AStarNodeB getClosestNode() {
        AStarNodeB currentNode = null;
        for (AStarNodeB node : openNodes.values()) {
            if (currentNode == null || currentNode.getFCost() > node.getFCost())
                currentNode = node;
        }
        return currentNode;
    }

}
