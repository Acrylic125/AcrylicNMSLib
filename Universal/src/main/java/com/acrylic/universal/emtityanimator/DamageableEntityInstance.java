package com.acrylic.universal.emtityanimator;

import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface DamageableEntityInstance {

    boolean isDead();

    void healEntity(double amount);

    void healEntity();

    void knockbackEntity(@NotNull LivingEntity entity);

    void damageEntity(@NotNull LivingEntity attacker);

    void damageEntity(@NotNull LivingEntity attacker, float amount);

    void damageEntity(float damage);

}
