package com.acrylic.universal.entityai.strategy;

import com.acrylic.math.ProbabilityKt;
import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimpleFollowerStrategy<T extends LivingEntityAnimator>
        implements EntityFollowingStrategy<T> {

    private LivingEntity entityTarget;
    private float distance = 32;
    private float maxDistanceFromTarget = 32;
    private long searchForNewTargetTime = 0;
    private long searchForNewTargetCooldown = 0;
    private final FollowerAI<T> ai;

    public SimpleFollowerStrategy(@NotNull FollowerAI<T> ai) {
        this.ai = ai;
        ai.setFollowingStrategy(this);
    }

    @Override
    public boolean shouldClearFollower(@Nullable LivingEntity entity) {
        return entityTarget instanceof Player && !Bukkit.getOnlinePlayers().contains(entityTarget);
    }

    @Override
    public boolean canFollow(@NotNull LivingEntity entity) {
        return entity instanceof Player && ((Player) entity).isOnline();
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
    public EntityFollowingStrategy<T> setNewTargetDistance(float targetDistance) {
        this.distance = targetDistance;
        return this;
    }

    @Override
    public float getNewTargetDistance() {
        return distance;
    }

    @Override
    public EntityFollowingStrategy<T> setDistanceFromTargetToSwitch(float distanceFromTargetToSwitch) {
        this.maxDistanceFromTarget = distanceFromTargetToSwitch;
        return this;
    }

    @Override
    public float getDistanceFromTargetToSwitch() {
        return maxDistanceFromTarget;
    }

    @Override
    public void setTarget(@Nullable LivingEntity entity) {
        this.entityTarget = entity;
    }

    @Nullable
    @Override
    public LivingEntity getTarget() {
        return entityTarget;
    }

    @NotNull
    @Override
    public FollowerAI<T> getAI() {
        return ai;
    }

    @Override
    public void update() {
        LivingEntity target = getTarget();
        EntityPathfinder<T> pathfinder = ai.getPathfinder();
        if (pathfinder == null)
            return;
        if (shouldClearFollower(target)) {
            setTarget(null);
            pathfinder.setTargetLocation((Location) null);
            target = null;
        }
        if (target != null) {
            if (shouldSwitchTarget(target.getLocation())) {
                setTarget(null);
                target = null;
            } else {
                pathfinder.setTargetLocation(target);
            }
        }
        if (shouldSearchForTarget() && target == null)
            searchForTarget();
    }

    public boolean shouldSwitchTarget(@NotNull Location location) {
        return location.distanceSquared(getAnimator().getBukkitEntity().getLocation()) >= maxDistanceFromTarget * maxDistanceFromTarget;
    }

    public void searchForTarget() {
        T entityAnimator = getAnimator();
        LivingEntity target = null;
        List<LivingEntity> entities = getPossibleTargets(entityAnimator.getBukkitEntity().getLocation());
        int size = entities.size();
        if (size > 0) {
            setSearchForNewTargetTime(System.currentTimeMillis() + getSearchForNewTargetTimeCooldown());
            target = entities.get(ProbabilityKt.getRandom(0, size - 1));
            setTarget(target);
        }
        if (target != null) {
            assert ai.getPathfinder() != null;
            ai.getPathfinder().setTargetLocation(target);
        }
    }

}
