package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface EntityAttackingStrategy<T extends LivingEntityAnimator> extends EntityFollowingStrategy<T> {

    EntityAttackingStrategy<T> setAttackCooldown(long attackCooldown);

    long getAttackCooldown();

    void setAttackTime(long time);

    long getAttackTime();

    EntityAttackingStrategy<T> setAttackRange(float attackRange);

    float getAttackRange();

    void attack();

}
