package com.acrylic.universal.entityinstances;

import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface DamageableEntityInstance {

    boolean isDead();

    int getMaxDamageCooldown();

    void setMaxDamageCooldown(int ticks);

    void healEntity(double amount);

    void healEntity();

    void knockbackEntity(@NotNull LivingEntity entity);

    void damageEntity(@NotNull LivingEntity attacker);

    void damageEntity(@NotNull LivingEntity attacker, float amount);

    void damageEntity(float damage);

}
