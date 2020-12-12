package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;

public interface EntityAI<T extends LivingEntityAnimator>
        extends Cloneable {

    T getAnimator();

    void update();

}
