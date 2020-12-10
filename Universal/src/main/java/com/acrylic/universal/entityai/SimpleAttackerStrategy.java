package com.acrylic.universal.entityai;

import com.acrylic.universal.npc.PlayerNPCEntity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class SimpleAttackerStrategy<T extends PlayerNPCEntity>
        extends SimpleFollowerStrategy<T>
        implements EntityAttackingStrategy<T> {

    private float attackDistance = 3;
    private long attackCooldown = 700;
    private long attackTime = 0;

    @Override
    public EntityAttackingStrategy<T> setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
        return this;
    }

    @Override
    public long getAttackCooldown() {
        return attackCooldown;
    }

    @Override
    public void setAttackTime(long time) {
        this.attackTime = time;
    }

    @Override
    public long getAttackTime() {
        return attackTime;
    }

    @Override
    public EntityAttackingStrategy<T> setAttackRange(float attackRange) {
        this.attackDistance = attackRange;
        return this;
    }

    @Override
    public float getAttackRange() {
        return attackDistance;
    }

    @Override
    public void attack(@NotNull T entityAnimator) {
        LivingEntity entity = getTarget();
        if (entity != null)
            entityAnimator.attack(entity);
    }

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        super.update(entityAnimator, entityAI);
        LivingEntity target = getTarget();
        if (target != null && canAttack(entityAnimator, target)) {
            attack(entityAnimator);
            setAttackTime(System.currentTimeMillis() + getAttackCooldown());
        }
    }
}
