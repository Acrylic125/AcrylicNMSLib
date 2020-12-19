package com.acrylic.universal.nmsentityanimator;

import com.acrylic.universal.emtityanimator.LivingEntityInstance;
import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.packets.EntityAnimationPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;

public interface NMSLivingEntityAnimator extends NMSEntityAnimator {

    int getMaxDamageCooldown();

    void setMaxDamageCooldown(int ticks);

    boolean isNoClip();

    void setNoClip(boolean b);

    @Override
    LivingEntityInstance getEntityInstance();

    EntityAnimationPackets getAnimationPackets();

    EntityEquipmentPackets getEquipmentPackets();

}
