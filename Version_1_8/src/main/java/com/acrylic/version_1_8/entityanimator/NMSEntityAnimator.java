package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.exceptions.NoRendererException;
import com.acrylic.universal.renderer.EntityRenderer;
import com.acrylic.universal.renderer.InitializerPacketRenderer;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.EntityDestroyPacket;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.TeleportPacket;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public abstract class NMSEntityAnimator
        implements com.acrylic.universal.emtityanimator.NMSEntityAnimator {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets;
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private EntityRenderer packetRenderer;

    public NMSEntityAnimator() {
        this(new LivingEntityDisplayPackets());
    }

    public NMSEntityAnimator(@NotNull LivingEntityDisplayPackets livingEntityDisplayPackets) {
        displayPackets = livingEntityDisplayPackets;
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        getNMSEntity().move(x, y, z);
    }

    @Override
    public abstract Entity getNMSEntity();

    @NotNull
    @Override
    public EntityRenderer getRenderer() {
        if (packetRenderer == null)
            throw new NoRendererException();
        return packetRenderer;
    }

    @Override
    public void setRenderer(EntityRenderer packetRenderer) {
        this.packetRenderer = packetRenderer;
    }

    public void addToWorld(@NotNull WorldServer worldServer) {
        worldServer.addEntity(getNMSEntity());
    }

    @Override
    public void addToWorld(@NotNull World world) {
        addToWorld(NMSBukkitConverter.convertToWorldServer(world));
    }

    public void removeFromWorld(@NotNull WorldServer worldServer) {
        worldServer.removeEntity(getNMSEntity());
    }

    @Override
    public void removeFromWorld(@NotNull World world) {
        removeFromWorld(NMSBukkitConverter.convertToWorldServer(world));
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
