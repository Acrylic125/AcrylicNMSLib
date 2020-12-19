package com.acrylic.universal.nmsentityanimator;

import com.acrylic.universal.emtityanimator.EntityInstance;
import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.TeleportPacket;
import com.acrylic.universal.utils.TeleportationUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface NMSEntityAnimator
        extends EntityAnimator, WorldEntity, EntityRenderable {

    Object getNMSEntity();

    EntityInstance getEntityInstance();

    @Override
    default void addToWorld() {
        addToWorld(getBukkitEntity().getWorld());
    }

    @Override
    default void setVelocity(@NotNull Vector velocity) {
        setVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
    }

    @Override
    void setVelocity(double x, double y, double z);

    @NotNull
    TeleportPacket getTeleportPacket();

    @NotNull
    EntityDestroyPacket getDestroyPacket();

    @NotNull
    LivingEntityDisplayPackets getDisplayPackets();

    void delete();

    void setYaw(float yaw);

    void setPitch(float pitch);

    default void setYawAndPitch(float yaw, float pitch) {
        setYaw(yaw);
        setPitch(pitch);
    }

    default Location getLocation() {
        return getBukkitEntity().getLocation();
    }

    @Override
    default void teleport(@NotNull Location location) {
        TeleportPacket teleportPacket = getTeleportPacket();
        teleportPacket.teleport(getBukkitEntity(), location);
        getRenderer().send(teleportPacket);
    }

    /**@Override
    default void delete() {
        EntityDestroyPacket destroyPacket = getDestroyPacket();
        sendPacketsViaRenderer(destroyPacket);
        removeFromWorld(getBukkitEntity().getWorld());
    }**/

}
