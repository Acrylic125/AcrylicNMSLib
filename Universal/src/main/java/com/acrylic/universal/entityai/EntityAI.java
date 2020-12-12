package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.quitterquirk.EntityQuitterQuirk;
import com.acrylic.universal.entityai.strategy.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAI<T extends LivingEntityAnimator>
        extends Cloneable {

    EntityAI<T> setStrategy(@Nullable EntityStrategy<T> strategy);

    @Nullable
    EntityStrategy<T> getStrategy();

    EntityAI<T> setPathfinder(@Nullable EntityPathfinder<T> pathfinder);

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
