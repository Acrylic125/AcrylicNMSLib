package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.pathfinder.PathGenerator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEntityPathfinder<T extends LivingEntityAnimator>
        implements EntityPathfinder<T> {

    private PathGenerator pathGenerator = PathGenerator.A_STAR_GENERATOR;
    private PathingPhase phase = PathingPhase.RESTING;
    private float speed = 0.2f;
    private float distanceToPath = 32;
    private long maximumTraverseTime = 10_000;
    private long restDuration = 0;
    private long pathingTime = 0;
    private Location target;

    @Override
    public void setPathingPhase(@NotNull PathingPhase phase) {
        this.phase = phase;
    }

    @NotNull
    @Override
    public PathingPhase getPathingPhase() {
        if (phase == null)
            phase = PathingPhase.RESTING;
        return phase;
    }

    @Override
    public EntityPathfinder<T> setPathGenerator(@NotNull PathGenerator pathGenerator) {
        this.pathGenerator = pathGenerator;
        return this;
    }

    @NotNull
    @Override
    public PathGenerator getPathGenerator() {
        return pathGenerator;
    }

    @Override
    public EntityPathfinder<T> setMaximumTraverseTime(long maximumTraverseTime) {
        this.maximumTraverseTime = maximumTraverseTime;
        return this;
    }

    @Override
    public long getMaximumTraverseTime() {
        return maximumTraverseTime;
    }

    @Override
    public EntityPathfinder<T> setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public EntityPathfinder<T> setDistanceToPath(float distanceToPath) {
        this.distanceToPath = distanceToPath;
        return this;
    }

    @Override
    public float getDistanceToPath() {
        return distanceToPath;
    }

    @Override
    public EntityPathfinder<T> setRestTimeAmount(long restTimeAmount) {
        return null;
    }

    @Override
    public void setRestTimeDuration(long time) {
        this.restDuration = time;
    }

    @Override
    public long getRestTimeDuration() {
        return restDuration;
    }

    @Override
    public long getLastTimed() {
        return pathingTime;
    }

    @Override
    public void setLastTimed(long l) {
        this.pathingTime = l;
    }

    @Override
    public void setTargetLocation(@NotNull Location location) {
        this.target = location;
    }

    @Nullable
    @Override
    public Location getTargetLocation() {
        return target;
    }
}
