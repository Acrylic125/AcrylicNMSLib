package com.acrylic.universal.pathfinder.astar;

import com.acrylic.universal.NMSBridge;
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

public final class AStarTraverser extends PathTraverser {

    private final PathGenerator pathGenerator;
    private final AStarNode start;
    private final AStarNode end;
    private final Map<Integer, AStarNode> openNodes = new HashMap<>();
    private final Map<Integer, AStarNode> closedNodes = new HashMap<>();

    public AStarTraverser(PathGenerator pathGenerator, Location start, Location end) {
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

    private void removeNodeFrom(@NotNull Map<Integer, AStarNode> map, @NotNull AStarNode node) {
        map.remove(getID(node.getLocation()));
    }

    @NotNull
    private AStarNode addNodeTo(@NotNull Map<Integer, AStarNode> map, @NotNull AStarNode node) {
        map.put(getID(node.getLocation()), node);
        return node;
    }

    @Nullable
    private AStarNode getNodeFrom(@NotNull Map<Integer, AStarNode> map, @NotNull Location location) {
        return map.get(getID(location));
    }
    /** END OF HELPERS **/

    @Override
    public PathGenerator getPathGenerator() {
        return pathGenerator;
    }

    private void checkNode(@NotNull Location location, @NotNull AStarNode parent) {
        AStarNode checkNode = getNodeFrom(openNodes, location);
        AStarNode newNode = new AStarNode(parent, this, location);
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

    private boolean isNearEndNode(AStarNode node) {
        Location nLoc = node.getLocation();
        Location endLoc = end.getLocation();
        return Math.abs(nLoc.getX() - endLoc.getX()) <= 0.5 && Math.abs(nLoc.getZ() - endLoc.getZ()) <= 0.5;
    }

    @Override
    public void traverse() {
        int i = 0; //Iterations
        AStarNode computed = null;
        AStarNode closest;
        BoundingBoxExaminer boundingBoxExaminer = NMSBridge.getBridge().getUtils().getBlockExaminer();
        do {
            closest = getClosestNode();
            if (closest == null) {
                clearNodes();
                break;
            }
            if (isNearEndNode(closest)) {
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
        } while (!openNodes.isEmpty() && i <= pathGenerator.getLookUpThreshold());
        if (computed == null && closest != null)
            computed = closest;
        computeLocations(computed);
    }

    private void computeLocations(@Nullable AStarNode computed) {
        Location[] computedLocations;
        if (computed != null) {
           computedLocations = new Location[computed.getIndex() + 1];
           do {
               Location loc = computed.getLocation();
               BoundingBoxExaminer boundingBoxExaminer = NMSBridge.getBridge().getUtils().getBlockExaminer(loc.getBlock().getRelative(BlockFace.DOWN).getLocation());
               if (boundingBoxExaminer.canExamine())
                   loc.setY(boundingBoxExaminer.getMaxY());
               computedLocations[computed.getIndex()] = loc;
               computed = computed.getParent();
           } while (computed != null);
        } else
            computedLocations = new Location[0];
        setComputedLocations(computedLocations);
    }

    public Location getWalkableLocation(Location location) {
        double y = location.getY();
        PathGenerator pathGenerator = getPathGenerator();
        Location result = null;
        for (int i = -pathGenerator.getSearchDownAmount(); i <= pathGenerator.getSearchUpAmount(); i++) {
            location.setY(y + i);
            BlockExaminer.NavigationStyle blockStyle = pathGenerator.getBlockExaminer().getNavigationStyle(location);
            if (blockStyle != null && !blockStyle.equals(BlockExaminer.NavigationStyle.NONE)) {
                result = location.clone();
            }
        }
        return result;
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
