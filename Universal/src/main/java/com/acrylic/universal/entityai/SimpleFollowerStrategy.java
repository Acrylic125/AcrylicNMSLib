package com.acrylic.universal.entityai;

import com.acrylic.math.ProbabilityKt;
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
    private float distanceToSwitch = 32;
    private long searchForNewTargetTime = 0;
    private long searchForNewTargetCooldown = 0;

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
        this.distanceToSwitch = distanceFromTargetToSwitch;
        return this;
    }

    @Override
    public float getDistanceFromTargetToSwitch() {
        return distanceToSwitch;
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

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        LivingEntity target = getTarget();
        if (target != null) {
            if (target.getLocation().distanceSquared(entityAnimator.getBukkitEntity().getLocation()) >= distanceToSwitch * distanceToSwitch) {
                setTarget(null);
                target = null;
            } else {
                EntityPathfinder<T> pathfinder = entityAI.getPathfinder();
                if (pathfinder != null)
                    pathfinder.setTargetLocation(target);
            }
        }
        if (shouldSearchForTarget() && target == null ) {
            List<LivingEntity> entities = getPossibleTargets(entityAnimator.getBukkitEntity().getLocation());
            int size = entities.size();
            if (size > 0) {
                setSearchForNewTargetTime(System.currentTimeMillis() + getSearchForNewTargetTimeCooldown());
                target = entities.get(ProbabilityKt.getRandom(0, size -1));
                setTarget(target);
            }
            EntityPathfinder<T> pathfinder = entityAI.getPathfinder();
            if (pathfinder != null && target != null)
                pathfinder.setTargetLocation(target);
        }
    }
}
