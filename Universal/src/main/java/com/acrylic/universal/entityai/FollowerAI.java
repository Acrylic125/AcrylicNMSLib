package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public class FollowerAI<T extends LivingEntityAnimator>
        implements EntityAI<T> {

    private EntityPathfinder<T> entityPathfinder;
    private final T animator;

    public FollowerAI(EntityPathfinder<T> entityPathfinder, T animator) {
        this.entityPathfinder = entityPathfinder;
        this.animator = animator;
    }

    public void setPathfinder(@NotNull EntityPathfinder<T> pathfinder) {
        this.entityPathfinder = pathfinder;
    }

    @NotNull
    public EntityPathfinder<T> getPathfinder() {
        return entityPathfinder;
    }

    @Override
    public T getAnimator() {
        return animator;
    }

    @Override
    public void update() {
        entityPathfinder.update(this);
    }

}
