package com.acrylic.version_1_8.packets;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityHeadRotationPacket
        extends SinglePacketSender
        implements com.acrylic.universal.packets.EntityHeadRotationPacket {

    private PacketPlayOutEntityHeadRotation packet;

    @Override
    public PacketPlayOutEntityHeadRotation getPacket() {
        return packet;
    }

    @Override
    public void apply(@NotNull Entity entity) {
        apply(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public void apply(net.minecraft.server.v1_8_R3.Entity entity) {
        packet = new PacketPlayOutEntityHeadRotation(entity, (byte) ((entity.getBukkitEntity().getLocation().getYaw() * 256f) / 360f));
    }

}
