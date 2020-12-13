package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.quitterquirk.EntityQuitterStrategy;
import com.acrylic.universal.entityai.strategy.EntityFollowingStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public class FollowerAI<T extends LivingEntityAnimator>
        implements EntityAI<T> {

    private EntityPathfinder<T> entityPathfinder;
    private EntityQuitterStrategy<T> quitter;
    private EntityFollowingStrategy<T> followingStrategy;
    private final T animator;

    public FollowerAI(T animator) {
        this.animator = animator;
    }

    public void setEntityQuitter(@Nullable EntityQuitterStrategy<T> entityQuitterStrategy) {
        this.quitter = entityQuitterStrategy;
    }

    @Nullable
    public EntityQuitterStrategy<T> getEntityQuitter() {
        return quitter;
    }

    public void setPathfinder(@Nullable EntityPathfinder<T> pathfinder) {
        this.entityPathfinder = pathfinder;
    }

    @Nullable
    public EntityPathfinder<T> getPathfinder() {
        return entityPathfinder;
    }

    @Nullable
    public EntityFollowingStrategy<T> getFollowingStrategy() {
        return followingStrategy;
    }

    public void setFollowingStrategy(@Nullable EntityFollowingStrategy<T> followingStrategy) {
        this.followingStrategy = followingStrategy;
    }

    @Override
    public T getAnimator() {
        return animator;
    }

    @Override
    public void update() {
        if (entityPathfinder != null)
            entityPathfinder.update();
        if (followingStrategy != null)
            followingStrategy.update();
        if (quitter != null)
            quitter.update();
    }

}
