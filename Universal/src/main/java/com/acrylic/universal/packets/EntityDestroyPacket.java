package com.acrylic.universal.packets;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityDestroyPacket extends SinglePacketSender {

    void delete(@NotNull Entity entity);

}
