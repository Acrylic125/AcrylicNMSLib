package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface EntityAI<T extends LivingEntityAnimator>
        extends Cloneable {

    T getAnimator();

    void update();

}
