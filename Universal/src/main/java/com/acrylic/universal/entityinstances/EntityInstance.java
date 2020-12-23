package com.acrylic.universal.entityinstances;

import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.interfaces.Deletable;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.TeleportPacket;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * EntityInstance are to be used for custom NMS entities
 * for custom behaviour.
 *
 * @see com.acrylic.universal.entityai.EntityAI
 * @see com.acrylic.universal.entityai.pathfinder.EntityPathfinder
 * @see com.acrylic.universal.entityai.strategy.EntityStrategy
 */
public interface EntityInstance
        extends WorldEntity, Deletable, TickingEntityInstance {

    void initialize(@NotNull Location location);

    Entity getBukkitEntity();

    void setMCAI(boolean ai);

    void setAI(EntityAI<?> ai);

    @Nullable
    EntityAI<?> getAI();

    boolean isUsingMCAI();

    void setNameVisible(boolean b);

    void setName(@NotNull String name);

    void setVisible(boolean b);

    boolean isNoClip();

    void setNoClip(boolean b);

    float getYaw();

    void setYaw(float yaw);

    float getPitch();

    void setPitch(float pitch);

    void setVelocity(double x, double y, double z);

    @NotNull
    NMSEntityAnimator getAnimator();

    @NotNull
    TeleportPacket getTeleportPacket();

    @NotNull
    EntityDestroyPacket getDestroyPacket();

    @NotNull
    LivingEntityDisplayPackets getDisplayPackets();

    void setupTermination();

    void setupShowPackets();

    default void render() {
        if (getTicksLived() % 20 == 0)
            forceRender();
    }

    void forceRender();

    @Override
    default void delete() {
        getAnimator().delete();
    }

}
