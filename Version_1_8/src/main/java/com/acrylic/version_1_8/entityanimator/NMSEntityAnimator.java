package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.renderer.PacketRenderer;
import com.acrylic.version_1_8.packets.EntityDestroyPacket;
import com.acrylic.version_1_8.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.TeleportPacket;
import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class NMSEntityAnimator implements com.acrylic.universal.emtityanimator.NMSEntityAnimator {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets = new LivingEntityDisplayPackets();
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private PacketRenderer packetRenderer;

    public NMSEntityAnimator(@NotNull Location location) {}

    @Override
    public abstract Entity getNMSEntity();

    @Override
    public PacketRenderer getRenderer() {
        return packetRenderer;
    }

    @Override
    public void setRenderer(PacketRenderer packetRenderer) {
        this.packetRenderer = packetRenderer;
    }

    @NotNull
    @Override
    public TeleportPacket getTeleportPacket() {
        return teleportPacket;
    }

    @NotNull
    @Override
    public EntityDestroyPacket getDestroyPacket() {
        return entityDestroyPacket;
    }

    @NotNull
    @Override
    public LivingEntityDisplayPackets getDisplayPackets() {
        return displayPackets;
    }
}
