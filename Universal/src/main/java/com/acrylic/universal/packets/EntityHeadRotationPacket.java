package com.acrylic.universal.packets;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityHeadRotationPacket extends SinglePacketSender {

    void apply(@NotNull Entity entity);

}
