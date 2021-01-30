package com.acrylic.universal.entityai.searcher;

import com.acrylic.universal.entityai.TargetableAI;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import math.ProbabilityKt;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimpleEntitySearcher<T extends LivingEntityAnimator>
        implements EntitySearcher<T> {

    private float distance = 32;
    private float maxDistanceFromTarget = 32;
    private long searchForNewTargetTime = 0;
    private long searchForNewTargetCooldown = 0;
    private final TargetableAI<T> ai;

    public SimpleEntitySearcher(@NotNull TargetableAI<T> ai) {
        this.ai = ai;
    }

    @Override
    public boolean shouldClearFollower() {
        return ai.getTarget() == null;
    }

    @Override
    public boolean canFollow(@NotNull LivingEntity entity) {
        return entity != ai.getAnimator().getBukkitEntity(); //entity instanceof Player && ((Player) entity).isOnline();
    }

    @Override
    public List<LivingEntity> getPossibleTargets(@NotNull Location location) {
        ArrayList<LivingEntity> entities = new ArrayList<>();
        for (LivingEntity entity : location.getWorld().getLivingEntities()) {
            if (canFollow(entity))
                entities.add(entity);
        }
        return entities;
    }

    @Override
    public void setSearchForNewTargetTime(long time) {
        this.searchForNewTargetTime = time;
    }

    @Override
    public long getSearchForNewTargetTime() {
        return searchForNewTargetTime;
    }

    @Override
    public void setSearchForNewTargetTimeCooldown(long cooldown) {
        this.searchForNewTargetCooldown = cooldown;
    }

    @Override
    public long getSearchForNewTargetTimeCooldown() {
        return searchForNewTargetCooldown;
    }

    @Override
    public void setNewTargetDistance(float targetDistance) {
        this.distance = targetDistance;
    }

    @Override
    public float getNewTargetDistance() {
        return distance;
    }

    @Override
    public void setDistanceFromTargetToSwitch(float distanceFromTargetToSwitch) {
        this.maxDistanceFromTarget = distanceFromTargetToSwitch;
    }

    @Override
    public float getDistanceFromTargetToSwitch() {
        return maxDistanceFromTarget;
    }

    @NotNull
    @Override
    public TargetableAI<T> getAI() {
        return ai;
    }

    @Override
    public void update() {
        Entity target = ai.getTarget();
        if (shouldClearFollower()) {
            ai.setTarget(null);
            ai.setTargetLocation((Location) null);
            target = null;
        }
        if (target != null) {
            if (shouldSwitchTarget(target.getLocation())) {
                ai.setTarget(null);
                target = null;
            } else {
                ai.setTargetLocation(target.getLocation());
            }
        }
        if (shouldSearchForTarget() && target == null)
            searchForTarget();
    }

    public boolean shouldSwitchTarget(@NotNull Location location) {
        return location.distanceSquared(getAnimator().getBukkitEntity().getLocation()) >= maxDistanceFromTarget * maxDistanceFromTarget;
    }

    public void searchForTarget() {
        setSearchForNewTargetTime(System.currentTimeMillis() + 2_000);
        T entityAnimator = getAnimator();
        LivingEntity target = null;
        List<LivingEntity> entities = getPossibleTargets(entityAnimator.getBukkitEntity().getLocation());
        //Search for new target.
        int size = entities.size();
        if (size > 0) {
            setSearchForNewTargetTime(System.currentTimeMillis() + getSearchForNewTargetTimeCooldown());
            target = entities.get(ProbabilityKt.getRandom(0, size - 1));
            ai.setTarget(target);
        }
        if (target != null) {
            ai.setTargetLocation(target);
        }
    }

}
