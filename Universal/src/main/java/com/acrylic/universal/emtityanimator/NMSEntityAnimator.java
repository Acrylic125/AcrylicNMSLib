package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.TeleportPacket;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface NMSEntityAnimator extends EntityAnimator, NMSEntity, WorldEntity {

    @Override
    default void setVelocity(@NotNull Vector velocity) {
        setVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
    }

    @Override
    void setVelocity(double x, double y, double z);

    @Override
    default void teleport(@NotNull Location location) {
        TeleportPacket teleportPacket = getEntityInstance().getTeleportPacket();
        teleportPacket.teleport(getBukkitEntity(), location);
        teleportPacket.send(getRenderer());
    }

    @Override
    default void delete() {
        EntityDestroyPacket destroyPacket = getEntityInstance().getDestroyPacket();
        if (!destroyPacket.hasProcessedPacket())
            destroyPacket.apply(getBukkitEntity());
        removeFromWorld();
    }

    @Override
    default void addToWorld() {
        getEntityInstance().addToWorld();
    }

    @Override
    default void removeFromWorld() {
        getEntityInstance().removeFromWorld();
    }

}
