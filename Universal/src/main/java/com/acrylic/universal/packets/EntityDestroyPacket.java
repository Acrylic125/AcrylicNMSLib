package com.acrylic.universal.packets;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityDestroyPacket extends SinglePacketSender {

    void apply(@NotNull Entity entity);

}
