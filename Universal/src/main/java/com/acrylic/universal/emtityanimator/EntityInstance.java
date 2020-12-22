package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.interfaces.Deletable;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.TeleportPacket;
import org.jetbrains.annotations.NotNull;

/**
 * EntityInstance are to be used for custom NMS entities
 * for custom behaviour.
 *
 * @see com.acrylic.universal.entityai.EntityAI
 * @see com.acrylic.universal.entityai.pathfinder.EntityPathfinder
 * @see com.acrylic.universal.entityai.strategy.EntityStrategy
 */
public interface EntityInstance
        extends WorldEntity, Deletable {

    void setFireTicks(int ticks);

    int getFireTicks();

    int getTicksLived();

    void tickingEntity();

    @NotNull
    NMSEntityAnimator getAnimatior();

    @NotNull
    TeleportPacket getTeleportPacket();

    @NotNull
    EntityDestroyPacket getDestroyPacket();

    @NotNull
    LivingEntityDisplayPackets getDisplayPackets();

    void setupShowPackets();

    default void render() {
        if (getTicksLived() % 20 == 0)
            forceRender();
    }

    void forceRender();

    @Override
    default void delete() {
        getAnimatior().delete();
    }

}
