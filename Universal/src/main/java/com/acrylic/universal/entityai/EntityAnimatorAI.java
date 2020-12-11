package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public class EntityAnimatorAI<T extends LivingEntityAnimator> implements EntityAI<T> {

    private EntityStrategy<T> strategy;
    private EntityPathfinder<T> pathfinder;
    private EntityQuitterQuirk<T> quitter;

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
    public EntityAnimatorAI<T> setPathfinder(@Nullable EntityPathfinder<T> pathfinder) {
        this.pathfinder = pathfinder;
        return this;
    }

    @Nullable
    @Override
    public EntityPathfinder<T> getPathfinder() {
        return pathfinder;
    }

    @Override
    public EntityAI<T> setEntityQuitter(@Nullable EntityQuitterQuirk<T> entityQuitterQuirk) {
        this.quitter = entityQuitterQuirk;
        return this;
    }

    @Nullable
    @Override
    public EntityQuitterQuirk<T> getEntityQuitter() {
        return quitter;
    }
}
