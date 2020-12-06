package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public class EntityAnimatorAI<T extends LivingEntityAnimator> implements EntityAI<T> {


    @Nullable
    @Override
    public EntityStrategy<T> getStrategy() {
        return null;
    }

    @Nullable
    @Override
    public EntityPathfinder<T> getPathfinder() {
        return null;
    }
}
