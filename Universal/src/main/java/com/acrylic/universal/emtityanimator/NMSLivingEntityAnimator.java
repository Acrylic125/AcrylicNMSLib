package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.packets.EntityAnimationPackets;

public interface NMSLivingEntityAnimator
        extends LivingEntityAnimator, NMSEntityAnimator {

    boolean isNoClip();

    void setNoClip(boolean b);

    void setYaw(float yaw);

    void setPitch(float pitch);

    default void setYawAndPitch(float yaw, float pitch) {
        setYaw(yaw);
        setPitch(pitch);
    }

    @Override
    LivingEntityInstance getEntityInstance();

    default void animate(EntityAnimationEnum... animation) {
        EntityAnimationPackets entityAnimationPackets = getEntityInstance().getAnimationPackets();
        entityAnimationPackets.apply(getBukkitEntity(), animation);
        entityAnimationPackets.send(getRenderer());
    }

}
