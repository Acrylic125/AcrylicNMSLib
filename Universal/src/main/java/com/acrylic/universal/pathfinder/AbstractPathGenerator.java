package com.acrylic.universal.pathfinder;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPathGenerator implements PathGenerator {

    private int lookUpThreshold = 150;
    private int searchDown = 2;
    private int searchUp = 1;
    private BlockExaminer blockExaminer = BlockExaminer.SIMPLE_BLOCK_EXAMINER_B;

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
