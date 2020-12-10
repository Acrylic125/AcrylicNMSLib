package com.acrylic.version_1_8.packets;

import com.acrylic.universal.NMSUtils;
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
        apply(entity, entity.getBukkitEntity().getLocation().getYaw());
    }

    public void apply(net.minecraft.server.v1_8_R3.Entity entity, float angle) {
        packet = new PacketPlayOutEntityHeadRotation(entity, NMSUtils.getByteAngle(angle));
    }

}
