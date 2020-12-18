package com.acrylic.universal.pathfinder.newimp;

import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.universal.pathfinder.BlockExaminer;
import com.acrylic.universal.pathfinder.PathFace;
import com.acrylic.universal.pathfinder.PathGenerator;
import com.acrylic.universal.pathfinder.PathTraverser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

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

    private void clearNodes() {
        this.openNodes.clear();
        this.closedNodes.clear();
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
            if (closest == null) {
                clearNodes();
                break;
            }
            if (closest.equals(end)) {
                computed = closest;
                clearNodes();
                break;
            }
            i++;
            Location loc = closest.getLocation().clone();
            loc.setY(loc.getY() - 1);
            loc.setY(boundingBoxExaminer.examine(loc) ? boundingBoxExaminer.getMaxY() : loc.getY() + 1);
            //Original location x, y, z.
            double oX = loc.getX(), oY = loc.getY(), oZ = loc.getZ();
            removeNodeFrom(openNodes, closest);
            int conjointBlockerMask = 0x000;
            for (PathFace pathFace : PathFace.PATH_FACES) {
                if (pathFace.isBlocked(conjointBlockerMask))
                    continue;
                BlockFace blockFace = pathFace.getFace();
                loc.setX(oX + blockFace.getModX()); loc.setY(oY + blockFace.getModY()); loc.setZ(oZ + blockFace.getModZ());
                Location location = getWalkableLocation(loc);
                if (location != null)
                    checkNode(location, closest);
                else
                    conjointBlockerMask = pathFace.addToMask(conjointBlockerMask);
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

    public Location getWalkableLocation(Location location) {
        double y = location.getY();
        PathGenerator pathGenerator = getPathGenerator();
        for (int i = -pathGenerator.getSearchDownAmount(); i <= pathGenerator.getSearchUpAmount(); i++) {
            location.setY(y + i);
            BlockExaminer.NavigationStyle blockStyle = pathGenerator.getBlockExaminer().getNavigationStyle(location);
            if (blockStyle != null && !blockStyle.equals(BlockExaminer.NavigationStyle.NONE))
                return location.clone();
        }
        return null;
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
