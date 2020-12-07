package com.acrylic.universal.pathfinder;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPathGenerator implements PathGenerator {

    private static final BlockFace[] BLOCK_FACES_4 = new BlockFace[] {
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST
    } ;
    private static final BlockFace[] BLOCK_FACES_8 = new BlockFace[] {
            BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST,
            BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST
    } ;

    private BlockFace[] blockFaces = BLOCK_FACES_8;
    private int lookUpThreshold = 50;
    private int searchDown = 1;
    private int searchUp = 1;
    private BlockExaminer blockExaminer = BlockExaminer.SIMPLE_BLOCK_EXAMINER;

    @Override
    public PathGenerator setLookUpFaces(BlockFace... blockFaces) {
        this.blockFaces = blockFaces;
        return this;
    }

    @NotNull
    @Override
    public BlockFace[] getLookUpFaces() {
        return blockFaces;
    }

    @Override
    public PathGenerator setLookUpThreshold(int lookUpThreshold) {
        this.lookUpThreshold = lookUpThreshold;
        return this;
    }

    @Override
    public int getLookUpThreshold() {
        return lookUpThreshold;
    }

    @Override
    public PathGenerator setSearchDownAmount(int searchDownAmount) {
        this.searchDown = searchDownAmount;
        return this;
    }

    @Override
    public int getSearchDownAmount() {
        return this.searchDown;
    }

    @Override
    public PathGenerator setSearchUpAmount(int searchUpAmount) {
        this.searchUp = searchUpAmount;
        return this;
    }

    @Override
    public int getSearchUpAmount() {
        return this.searchUp;
    }

    @Override
    public PathGenerator setBlockExaminer(@NotNull BlockExaminer blockExaminer) {
        this.blockExaminer = blockExaminer;
        return this;
    }

    @Override
    public Location[] traverseAndCompute(@NotNull Location start, @NotNull Location end) {
        PathTraverser traverser = getPathTraverser(start, end);
        traverser.traverse();
        return traverser.getComputedLocations();
    }

    @NotNull
    @Override
    public BlockExaminer getBlockExaminer() {
        return blockExaminer;
    }

}
