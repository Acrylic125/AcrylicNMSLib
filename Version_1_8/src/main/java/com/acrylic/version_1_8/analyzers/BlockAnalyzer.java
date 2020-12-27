package com.acrylic.version_1_8.analyzers;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.version_1_8.NMSBukkitConverter;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockAnalyzer
        implements com.acrylic.universal.analyzers.BlockAnalyzer {

    private final net.minecraft.server.v1_8_R3.Block nmsBlock;
    private final Block block;
    private BoundingBoxExaminer boundingBoxExaminer;

    public BlockAnalyzer(@NotNull Block block) {
        this.block = block;
        this.nmsBlock = NMSBukkitConverter.convertToNMSBlock(block);
    }

    @NotNull
    @Override
    public net.minecraft.server.v1_8_R3.Block getNMSBlock() {
        return this.nmsBlock;
    }

    @NotNull
    @Override
    public Block getBlock() {
        return this.block;
    }

    @NotNull
    @Override
    public BoundingBoxExaminer getBoundingBox() {
        if (boundingBoxExaminer == null)
            boundingBoxExaminer = NMSBridge.getBridge().getAnalyzerFactory().getNewBoundingBoxExaminer(block.getLocation());
        return boundingBoxExaminer;
    }

    @Override
    public String getStepSound() {
        return nmsBlock.stepSound.getStepSound();
    }

    @Override
    public String getBreakSound() {
        return nmsBlock.stepSound.getBreakSound();
    }

    @Override
    public String getPlaceSound() {
        return nmsBlock.stepSound.getPlaceSound();
    }
}
