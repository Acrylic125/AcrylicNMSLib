package com.acrylic.universal.packets;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.analyzers.BlockAnalyzer;
import math.ProbabilityKt;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Please use Bukkit APIs sound implementation
 * instead of this. {@link org.bukkit.Sound}
 *
 * This class is meant to aid in NMS as there is no
 * direct bridge that can be accessed via the bukkit
 * sound implementation. ("sound.a" /= Sound.A)
 */
public interface SoundPacket extends SinglePacketSender {

    void apply(@NotNull String soundString, @NotNull Location location, float volume, float pitch);

    default void applyBreakSound(@NotNull Block block, float volume, float pitch) {
        BlockAnalyzer blockAnalyzer = NMSBridge.getBridge().getAnalyzerFactory().getNewBlockAnalyzer(block);
        apply(blockAnalyzer.getBreakSound(), block.getLocation(), volume, pitch);
    }

    default void applyBreakSound(@NotNull Block block, float volume) {
        applyBreakSound(block, volume, 1);
    }

    default void applyBreakSound(@NotNull Block block) {
        applyBreakSound(block, 1f, ProbabilityKt.getRandom(0.8f, 0.9f));
    }

    default void applyStepSound(@NotNull Block block, float volume, float pitch) {
        BlockAnalyzer blockAnalyzer = NMSBridge.getBridge().getAnalyzerFactory().getNewBlockAnalyzer(block);
        apply(blockAnalyzer.getStepSound(), block.getLocation(), volume, pitch);
    }

    default void applyStepSound(@NotNull Block block, float volume) {
        applyStepSound(block, volume, 1);
    }

    default void applyStepSound(@NotNull Block block) {
        applyStepSound(block, 1);
    }

    default void applyPlaceSound(@NotNull Block block, float volume, float pitch) {
        BlockAnalyzer blockAnalyzer = NMSBridge.getBridge().getAnalyzerFactory().getNewBlockAnalyzer(block);
        apply(blockAnalyzer.getPlaceSound(), block.getLocation(), volume, pitch);
    }

    default void applyPlaceSound(@NotNull Block block, float volume) {
        applyPlaceSound(block, volume, 1);
    }

    default void applyPlaceSound(@NotNull Block block) {
        applyPlaceSound(block, 1);
    }

}
