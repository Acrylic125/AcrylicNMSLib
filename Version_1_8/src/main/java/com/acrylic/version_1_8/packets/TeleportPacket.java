package com.acrylic.version_1_8.packets;

import com.acrylic.universal.utils.TeleportationUtils;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class TeleportPacket
        extends SinglePacketSender
        implements com.acrylic.universal.packets.TeleportPacket  {

    private PacketPlayOutEntityTeleport packet;

    @Override
    public PacketPlayOutEntityTeleport getPacket() {
        return packet;
    }

    @Override
    public void teleport(@NotNull Entity entity, @NotNull Location location) {
        TeleportationUtils.tp(entity, location);
        teleport(entity);
    }

    @Override
    public void teleport(@NotNull Entity entity) {
        packet = new PacketPlayOutEntityTeleport(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    @Override
    public boolean hasInitialized() {
        return packet != null;
    }

}
