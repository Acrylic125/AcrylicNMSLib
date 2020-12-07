package com.acrylic.universal.emtityanimator;

import org.jetbrains.annotations.NotNull;

public interface EntityInstance {

    void tickingEntity();

    @NotNull
    NMSEntityAnimator getAnimatior();

}
