package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public class EntityAnimatorAI<T extends LivingEntityAnimator> implements EntityAI<T> {

    private EntityStrategy<T> strategy;
    private EntityPathfinder<T> pathfinder;

    @Override
    public EntityAI<T> setStrategy(@Nullable EntityStrategy<T> strategy) {
        this.strategy = strategy;
        return this;
    }

    @Nullable
    @Override
    public EntityStrategy<T> getStrategy() {
        return strategy;
    }

    @Override
    public EntityPathfinder<T> setPathfinder(@Nullable EntityPathfinder<T> pathfinder) {
        this.pathfinder = pathfinder;
        return null;
    }

    @Nullable
    @Override
    public EntityPathfinder<T> getPathfinder() {
        return pathfinder;
    }
}
