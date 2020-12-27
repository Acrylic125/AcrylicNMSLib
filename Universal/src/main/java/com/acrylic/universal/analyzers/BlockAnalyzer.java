package com.acrylic.universal.analyzers;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockAnalyzer {

    @NotNull
    Object getNMSBlock();

    @NotNull
    Block getBlock();

    @NotNull
    BoundingBoxExaminer getBoundingBox();
       // return NMSBridge.getBridge().getNewBlockExaminer(getBlock().getLocation());

    String getStepSound();

    String getBreakSound();

    String getPlaceSound();

}
