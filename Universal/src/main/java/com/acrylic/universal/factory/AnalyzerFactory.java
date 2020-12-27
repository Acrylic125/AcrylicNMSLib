package com.acrylic.universal.factory;

import com.acrylic.universal.analyzers.BlockAnalyzer;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface AnalyzerFactory {

    @NotNull
    BoundingBoxExaminer getNewBoundingBoxExaminer();

    @NotNull
    default BoundingBoxExaminer getNewBoundingBoxExaminer(@NotNull Location location) {
        BoundingBoxExaminer boundingBoxExaminer = getNewBoundingBoxExaminer();
        boundingBoxExaminer.examine(location);
        return boundingBoxExaminer;
    }

    @NotNull
    default BoundingBoxExaminer getNewBoundingBoxExaminer(@NotNull Block block) {
        return getNewBoundingBoxExaminer(block.getLocation());
    }

    @NotNull
    default BoundingBoxExaminer getNewBoundingBoxExaminer(@NotNull Entity entity) {
        BoundingBoxExaminer boundingBoxExaminer = getNewBoundingBoxExaminer();
        boundingBoxExaminer.examine(entity);
        return boundingBoxExaminer;
    }

    @NotNull
    BlockAnalyzer getNewBlockAnalyzer(@NotNull Block block);

    @NotNull
    default BlockAnalyzer getNewBlockAnalyzer(@NotNull Location location) {
        return getNewBlockAnalyzer(location.getBlock());
    }

}
