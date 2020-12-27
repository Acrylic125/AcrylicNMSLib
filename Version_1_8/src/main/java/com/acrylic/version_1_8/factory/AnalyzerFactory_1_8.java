package com.acrylic.version_1_8.factory;

import com.acrylic.universal.analyzers.BlockAnalyzer;
import com.acrylic.universal.factory.AnalyzerFactory;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class AnalyzerFactory_1_8 implements AnalyzerFactory {

    @NotNull
    @Override
    public BoundingBoxExaminer getNewBoundingBoxExaminer() {
        return new com.acrylic.version_1_8.misc.BoundingBoxExaminer();
    }

    @NotNull
    @Override
    public BlockAnalyzer getNewBlockAnalyzer(@NotNull Block block) {
        return new com.acrylic.version_1_8.analyzers.BlockAnalyzer(block);
    }
}
