package com.acrylic.universal.pathfinder;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public class SimpleBlockExaminer implements BlockExaminer {

    private final BoundingBoxExaminer boundingBoxExaminer = NMSBridge.getBridge().getUtils().getBlockExaminer();

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

    @Override
    public boolean isTraversable(@NotNull Block block) {
        Material type = block.getType();
        return BlockExaminer.isAir(type) || isClimbable(block) || !type.isSolid() || shouldNoClip(block);
    }

    @Override
    public boolean isLiquid(@NotNull Block block) {
        return BlockExaminer.isLiquid(block.getType());
    }

    @Override
    public boolean isClimbable(@NotNull Block block) {
        return BlockExaminer.isClimbable(block.getType());
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
