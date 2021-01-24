package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.entityinstances.WorldEntity;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.TeleportPacket;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public abstract class NMSEntityAnimator implements EntityAnimator, NMSEntity, WorldEntity {

    private InitializerLocationalRenderer renderer;

    public NMSEntityAnimator(@NotNull InitializerLocationalRenderer renderer) {
        this.renderer = renderer;
    }

    @NotNull
    public InitializerLocationalRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(@NotNull InitializerLocationalRenderer packetRenderer) {
        this.renderer = packetRenderer;
    }

    @Override
    public void setVelocity(@NotNull Vector velocity) {
        setVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        getEntityInstance().setVelocity(x, y, z);
    }

    @Override
    public void teleport(@NotNull Location location) {
        TeleportPacket teleportPacket = getEntityInstance().getTeleportPacket();
        teleportPacket.teleport(getBukkitEntity(), location);
        teleportPacket.send(getRenderer());
    }

    @Override
    public void delete() {
        EntityDestroyPacket destroyPacket = getEntityInstance().getDestroyPacket();
        if (!destroyPacket.hasProcessedPacket())
            destroyPacket.apply(getBukkitEntity());
        removeFromWorld();
        getRenderer().unrenderAll();
    }

    @Override
    public void addToWorld() {
        getEntityInstance().addToWorld();
    }

    @Override
    public void removeFromWorld() {
        getEntityInstance().removeFromWorld();
    }

    public boolean isNoClip() {
        return getEntityInstance().isNoClip();
    }

    public void setNoClip(boolean b) {
        getEntityInstance().setNoClip(b);
    }

    public void setYaw(float yaw) {
        getEntityInstance().setYaw(yaw);
    }

    public void setPitch(float pitch) {
        getEntityInstance().setPitch(pitch);
    }

    public void setYawAndPitch(float yaw, float pitch) {
        setYaw(yaw);
        setPitch(pitch);
    }


}
