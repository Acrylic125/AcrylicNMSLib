package com.acrylic.universal.packets;

import com.acrylic.universal.util.BukkitHashCode;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockCrackPacket extends SinglePacketSender {

    void apply(@NotNull Block block, int blockID, int damage);

    default void apply(@NotNull Block block, int damage) {
        apply(block, BukkitHashCode.getHashCode(block), damage);
    }


}
