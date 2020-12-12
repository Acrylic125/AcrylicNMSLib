package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.quitterquirk.EntityQuitterQuirk;
import com.acrylic.universal.entityai.strategy.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public class EntityAnimatorAI<T extends LivingEntityAnimator>
        implements EntityAI<T> {

    private EntityStrategy<T> strategy;
    private EntityPathfinder<T> pathfinder;

    @Override
    public EntityAnimatorAI<T> setStrategy(@Nullable EntityStrategy<T> strategy) {
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

}
