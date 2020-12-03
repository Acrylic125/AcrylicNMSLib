package com.acrylic.version_1_8.packets;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import org.bukkit.entity.Entity;

public class EntityMetaDataPacket extends SinglePacketSender implements com.acrylic.universal.packets.EntityMetaDataPacket  {

    private PacketPlayOutEntityMetadata packet;

    public void apply(net.minecraft.server.v1_8_R3.Entity entity) {
        apply(entity, false);
    }

    public void apply(net.minecraft.server.v1_8_R3.Entity entity, boolean b) {
        packet = new PacketPlayOutEntityMetadata(entity.getId(), entity.getDataWatcher(), b);
    }

    @Override
    public void apply(Entity entity) {
        apply(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    @Override
    public PacketPlayOutEntityMetadata getPacket() {
        return packet;
    }

}
