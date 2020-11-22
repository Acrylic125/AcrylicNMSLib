package com.acrylic.universal.packets;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface TeleportPacket extends SinglePacketSender {

    void teleport(@NotNull Entity entity, @NotNull Location location);

}
