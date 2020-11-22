package com.acrylic.universal.packets;

import org.bukkit.entity.Entity;

public interface EntityMetaDataPacket extends SinglePacketSender {

    void apply(Entity entity);

}
