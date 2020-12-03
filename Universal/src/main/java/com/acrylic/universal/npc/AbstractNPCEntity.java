package com.acrylic.universal.npc;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface AbstractNPCEntity extends NMSLivingEntityAnimator {

    void setSkin(@NotNull String texture, @NotNull String signature);

    default void setSkin(@NotNull String name) {
        SimpleNPCSkin simpleNPCSkin = UniversalNMS.getSkinMap().getAndAddIfNotExist(name);
        setSkin(simpleNPCSkin.getTexture(), simpleNPCSkin.getSignature());
    }

    default void setSkin(@NotNull SimpleNPCSkin simpleNPCSkin) {
        setSkin(simpleNPCSkin.getTexture(), simpleNPCSkin.getSignature());
    }

}
