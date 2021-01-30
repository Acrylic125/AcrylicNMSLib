package com.acrylic.universal.entityai.attack;

import com.acrylic.universal.entityai.TargetableAI;
import com.acrylic.universal.entityai.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface EntityAttacker<T extends LivingEntityAnimator>
        extends EntityStrategy<T> {

    void setAttackCooldown(long attackCooldown);

    long getAttackCooldown();

    void setAttackTime(long time);

    long getAttackTime();

    void setAttackRange(float attackRange);

    float getAttackRange();

    void attack();

    default boolean canAttack(@NotNull LivingEntity entity) {
        float range = getAttackRange();
        return System.currentTimeMillis() > getAttackTime() && getAnimator().getLocation().distanceSquared(entity.getLocation()) <= range * range;
    }

    @NotNull
    @Override
    TargetableAI<T> getAI();
}
