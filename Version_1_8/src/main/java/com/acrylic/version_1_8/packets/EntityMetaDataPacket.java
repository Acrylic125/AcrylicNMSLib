package com.acrylic.version_1_8.packets;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Entity;

public class EntityMetaDataPacket extends PacketSender implements com.acrylic.universal.packets.EntityMetaDataPacket  {



    @Override
    public void apply(Entity entity) {

    }

    @Override
    public Object getPacket() {
        return null;
    }

    @Override
    public Packet<?>[] getPackets() {
        return new Packet[0];
    }
}
