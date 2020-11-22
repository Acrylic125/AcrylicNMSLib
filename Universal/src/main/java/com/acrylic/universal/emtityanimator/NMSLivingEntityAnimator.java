package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;

public interface NMSLivingEntityAnimator extends LivingEntityAnimator, NMSEntityAnimator {

    EntityEquipmentPackets getEquipmentPackets();

    default void show() {
        LivingEntityDisplayPackets showPackets = getDisplayPackets();
        showPackets.show(this);
        sendPacketsViaRenderer(showPackets);
    }
}
