package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.strategy.EntityAttackingStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AttackerAI<T extends LivingEntityAnimator> extends FollowerAI<T> {

    private EntityAttackingStrategy<T> attackingStrategy;

    public AttackerAI(EntityPathfinder<T> entityPathfinder, EntityAttackingStrategy<T> entityAttackingStrategy) {
        super(entityPathfinder);
        this.attackingStrategy = entityAttackingStrategy;
    }

    public void setAttackingStrategy(@Nullable EntityAttackingStrategy<T> strategy) {
        this.attackingStrategy = strategy;
    }

    @NotNull
    public EntityAttackingStrategy<T> getAttackingStrategy() {
        return attackingStrategy;
    }

    @Override
    public void update(@NotNull T entityAnimator) {
        attackingStrategy.update(entityAnimator, this);
        super.update(entityAnimator);
    }
}
