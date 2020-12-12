package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.strategy.EntityAttackingStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AttackerAI<T extends LivingEntityAnimator> extends FollowerAI<T> {

    private EntityAttackingStrategy<T> attackingStrategy;

    public AttackerAI(@NotNull EntityPathfinder<T> entityPathfinder, @NotNull EntityAttackingStrategy<T> attackingStrategy, T animator) {
        super(entityPathfinder, animator);
        this.attackingStrategy = attackingStrategy;
    }

    public void setAttackingStrategy(@Nullable EntityAttackingStrategy<T> strategy) {
        this.attackingStrategy = strategy;
    }

    @NotNull
    public EntityAttackingStrategy<T> getAttackingStrategy() {
        return attackingStrategy;
    }

    @Override
    public void update() {
        attackingStrategy.update(this);
        super.update();
    }
}
