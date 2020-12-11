package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAI<T extends LivingEntityAnimator> {

    EntityAI<T> setStrategy(@Nullable EntityStrategy<T> strategy);

    @Nullable
    EntityStrategy<T> getStrategy();

    EntityAI<T> setPathfinder(@Nullable EntityPathfinder<T> pathfinder);

    @Nullable
    EntityPathfinder<T> getPathfinder();

    EntityAI<T> setEntityQuitter(@Nullable EntityQuitterQuirk<T> entityQuitterQuirk);

    @Nullable
    EntityQuitterQuirk<T> getEntityQuitter();

    default void update(@NotNull T entityAnimator) {
        EntityStrategy<T> entityStrategy = getStrategy();
        if (entityStrategy != null)
            entityStrategy.update(entityAnimator, this);
        EntityPathfinder<T> entityPathfinder = getPathfinder();
        if (entityPathfinder != null)
            entityPathfinder.update(entityAnimator, this);
        EntityQuitterQuirk<T> entityQuitterQuirk = getEntityQuitter();
        if (entityQuitterQuirk != null) {
            entityQuitterQuirk.update(entityAnimator, this);
        }
    }

}
