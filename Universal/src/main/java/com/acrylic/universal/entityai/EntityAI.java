package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAI<T extends LivingEntityAnimator> {

    @Nullable
    EntityStrategy<T> getStrategy();

    @Nullable
    EntityPathfinder<T> getPathfinder();

    default void update(@NotNull T entityAnimator) {
        EntityStrategy<T> entityStrategy = getStrategy();
        if (entityStrategy != null)
            entityStrategy.update(entityAnimator, this);
        EntityPathfinder<T> entityPathfinder = getPathfinder();
        if (entityPathfinder != null)
            entityPathfinder.update(entityAnimator, this);
    }

}
