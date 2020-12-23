package com.acrylic.version_1_8.packets;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class BlockCrackPacket
        extends SinglePacketSender
        implements com.acrylic.universal.packets.BlockCrackPacket {

    private PacketPlayOutBlockBreakAnimation packet;

    @Override
    public void apply(@NotNull Block block, int blockID, int damage) {
        this.packet = new PacketPlayOutBlockBreakAnimation(blockID, NMSBukkitConverter.getBlockPosition(block), damage);
    }

    @Override
    public PacketPlayOutBlockBreakAnimation getPacket() {
        return packet;
    }

}
