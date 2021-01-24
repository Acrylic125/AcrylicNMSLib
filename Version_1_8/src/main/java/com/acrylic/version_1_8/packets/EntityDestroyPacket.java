package com.acrylic.version_1_8.packets;

import com.acrylic.universal.utils.TeleportationUtils;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityDestroyPacket
        extends SinglePacketSender
        implements com.acrylic.universal.packets.EntityDestroyPacket {

    private PacketPlayOutEntityDestroy packet;

    @Override
    public PacketPlayOutEntityDestroy getPacket() {
        return packet;
    }

    public void delete(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        packet = new PacketPlayOutEntityDestroy(entity.getId());
    }

    @Override
    public void apply(@NotNull Entity entity) {
        packet = new PacketPlayOutEntityDestroy(entity.getEntityId());
    }

    @Override
    public boolean hasInitialized() {
        return packet != null;
    }
}
