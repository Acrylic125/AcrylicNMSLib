package com.acrylic.universal.entityai;

import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface EntityAttackingStrategy extends EntityStrategy {

    long getAttackCooldown();

    long getAttackTime();

    void setAttackTime(long time);

    float getAttackRange();

    float getNewTargetDistance();

    void setTarget(@NotNull LivingEntity entity);

    LivingEntity getTarget();

    void attack();

}
