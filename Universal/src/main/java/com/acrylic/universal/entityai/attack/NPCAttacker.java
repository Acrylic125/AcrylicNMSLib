package com.acrylic.universal.entityai.attack;

import com.acrylic.universal.emtityanimator.instances.PlayerNPC;
import com.acrylic.universal.entityai.TargetableAI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class NPCAttacker implements EntityAttacker<PlayerNPC> {

    private final TargetableAI<PlayerNPC> ai;
    private float attackDistance = 3;
    private long attackCooldown = 700;
    private long attackTime = 0;

    public NPCAttacker(@NotNull TargetableAI<PlayerNPC> ai) {
        this.ai = ai;
    }

    @Override
    public void setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
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
    public void setAttackRange(float attackRange) {
        this.attackDistance = attackRange;
    }

    @Override
    public float getAttackRange() {
        return attackDistance;
    }

    @Override
    public void attack() {
        Entity entity = ai.getTarget();
        if (entity instanceof LivingEntity)
            ai.getAnimator().attack((LivingEntity) entity);
    }

    @NotNull
    @Override
    public TargetableAI<PlayerNPC> getAI() {
        return this.ai;
    }

    @Override
    public void update() {
        Entity target = ai.getTarget();
        if (target instanceof LivingEntity && canAttack((LivingEntity) target)) {
            attack();
            setAttackTime(System.currentTimeMillis() + getAttackCooldown());
        }
    }
}
