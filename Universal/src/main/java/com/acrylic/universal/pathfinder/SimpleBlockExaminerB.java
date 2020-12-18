package com.acrylic.universal.pathfinder;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public class SimpleBlockExaminerB implements BlockExaminer {

    private final BoundingBoxExaminer boundingBoxExaminer = NMSBridge.getBridge().getUtils().getBlockExaminer();
    private static final float heightReq = 1.8f;

    @Override
    public boolean shouldNoClip(@NotNull Block block) {
        switch (block.getType()) {
            case WOOD_DOOR:
            case WOODEN_DOOR:
            case BIRCH_DOOR:
            case SPRUCE_DOOR:
            case JUNGLE_DOOR:
            case DARK_OAK_DOOR:
            case ACACIA_DOOR:
            case IRON_DOOR:
            case FENCE_GATE:
            case BIRCH_FENCE_GATE:
            case SPRUCE_FENCE_GATE:
            case JUNGLE_FENCE_GATE:
            case DARK_OAK_FENCE_GATE:
            case ACACIA_FENCE_GATE:
            case IRON_TRAPDOOR:
            case TRAP_DOOR:
                return true;
            default:
                return false;
        }
    }

    public boolean isTraversable(@NotNull Block block, @NotNull BoundingBoxExaminer examiner) {
        Material type = block.getType();
        return BlockExaminer.isAir(type) || isClimbable(block) || !examiner.canExamine() || shouldNoClip(block);
    }

    @Override
    public boolean isTraversable(@NotNull Block block) {
        Material type = block.getType();
        return BlockExaminer.isAir(type) || isClimbable(block) || shouldNoClip(block);
    }

    @Override
    public boolean isLiquid(@NotNull Block block) {
        return BlockExaminer.isLiquid(block.getType());
    }

    @Override
    public boolean isClimbable(@NotNull Block block) {
        return BlockExaminer.isClimbable(block.getType());
    }

    private BoundingBoxExaminer getNewBBExaminer(@NotNull Location location) {
        return NMSBridge.getBridge().getUtils().getBlockExaminer(location);
    }

    public NavigationStyle getNavigationStyle(@NotNull Location location) {
        location = location.clone();
        BoundingBoxExaminer boundingBoxExaminer = getNewBBExaminer(location);
        double baseY;
        boolean isSolidBase;
        if (!isTraversable(location.getBlock()) && boundingBoxExaminer.canExamine()) {
            baseY = boundingBoxExaminer.getMaxY();
            isSolidBase = true;
        } else {
            boundingBoxExaminer.examine(location.add(0, -1, 0));
            isSolidBase = !isTraversable(location.getBlock()) && boundingBoxExaminer.canExamine();
            baseY = (isSolidBase) ? boundingBoxExaminer.getMaxY() : location.getY();
        }
        int max = (int) Math.ceil(heightReq);
        boolean isSwimming = false;
        boolean isClimbable = false;
        for (int i = 0; i <= max; i++) {
            location.setY(baseY + i);
            boundingBoxExaminer.examine(location);
            Block block = location.getBlock();
            boolean canExamine = boundingBoxExaminer.canExamine();
            boolean canPass = isTraversable(block, boundingBoxExaminer);
            if (i == 0) {
                if (isLiquid(block))
                    isSwimming = true;
                else if (isClimbable(block))
                    isClimbable = true;
            }
            else if (i == max || !canPass) {
                double highestY = (canExamine) ? boundingBoxExaminer.getMinY() : block.getY() + 1;
                boolean canFit = (highestY - baseY) >= heightReq;
                if (!canFit)
                    return NavigationStyle.NONE;
                else if (isSwimming)
                    return (BlockExaminer.isLiquid(block.getType())) ? NavigationStyle.SWIM : NavigationStyle.CASUAL_SWIM;
                else if (isSolidBase)
                    return (isClimbable) ? NavigationStyle.CLIMB : NavigationStyle.WALK;
                break;
            }
        }
        return NavigationStyle.NONE;
    }

    @Override
    public NavigationStyle getNavigationStyle(@NotNull Block block) {

        Block up = block.getRelative(BlockFace.UP);
        if (isTraversable(up) && isTraversable(block)) {
            if (isLiquid(block))
                return isLiquid(up) ? NavigationStyle.SWIM : NavigationStyle.CASUAL_SWIM;
            else if (isClimbable(block))
                return NavigationStyle.CLIMB;
            else if (block.getRelative(BlockFace.DOWN).getType().isSolid())
                return NavigationStyle.WALK;
        }
        return NavigationStyle.NONE;
    }

    @Override
    public boolean canPassThrough(@NotNull Block block) {
        return isTraversable(block.getRelative(BlockFace.UP)) && isTraversable(block);
    }

    @Override
    public BoundingBoxExaminer getBoundingBoxExaminer() {
        return boundingBoxExaminer;
    }


}
