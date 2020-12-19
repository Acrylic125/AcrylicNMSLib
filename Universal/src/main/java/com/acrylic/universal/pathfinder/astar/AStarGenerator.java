package com.acrylic.universal.pathfinder.astar;

import com.acrylic.universal.pathfinder.AbstractPathGenerator;
import com.acrylic.universal.pathfinder.PathTraverser;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Generates a path with the A* algorithm.
 */
public class AStarGenerator extends AbstractPathGenerator {

    @NotNull
    @Override
    public PathTraverser getPathTraverser(@NotNull Location start, @NotNull Location end) {
        return new AStarTraverser(this, start, end);
    }
}
