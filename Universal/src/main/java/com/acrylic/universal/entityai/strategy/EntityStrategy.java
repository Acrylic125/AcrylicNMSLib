package com.acrylic.universal.entityai.strategy;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface EntityStrategy<T extends LivingEntityAnimator> {

    void update(@NotNull EntityAI<T> ai);

}
