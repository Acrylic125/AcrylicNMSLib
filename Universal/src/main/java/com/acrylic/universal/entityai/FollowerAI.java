package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FollowerAI<T extends LivingEntityAnimator> implements EntityAI<T> {

    private EntityPathfinder<T> entityPathfinder;

    public FollowerAI(EntityPathfinder<T> entityPathfinder) {
        this.entityPathfinder = entityPathfinder;
    }

    public void setPathfinder(@Nullable EntityPathfinder<T> pathfinder) {
        this.entityPathfinder = pathfinder;
    }

    @NotNull
    public EntityPathfinder<T> getPathfinder() {
        return entityPathfinder;
    }

    @Override
    public void update(@NotNull T entityAnimator) {
        entityPathfinder.update(entityAnimator, this);
    }

}
