package com.acrylic.universal.pathfinder;

import com.acrylic.universal.pathfinder.astar.AStarGenerator;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface PathGenerator {

    /** Default path generator(s).**/
    AStarGenerator A_STAR_GENERATOR = new AStarGenerator();

    PathGenerator setLookUpFaces(BlockFace... blockFaces);

    @NotNull
    BlockFace[] getLookUpFaces();

    PathGenerator setLookUpThreshold(int lookUpThreshold);

    int getLookUpThreshold();

    PathGenerator setSearchDownAmount(int searchDownAmount);

    int getSearchDownAmount();

    PathGenerator setSearchUpAmount(int searchUpAmount);

    int getSearchUpAmount();

    Location[] traverseAndCompute(@NotNull Location start, @NotNull Location end);

    PathGenerator setBlockExaminer(@NotNull BlockExaminer blockExaminer);

    @NotNull
    BlockExaminer getBlockExaminer();

    @NotNull
    PathTraverser getPathTraverser(@NotNull Location start, @NotNull Location end);

}
