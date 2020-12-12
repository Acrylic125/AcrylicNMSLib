package com.acrylic.universal.entityai.pathfinder;

import com.acrylic.universal.entityai.quitterquirk.EntityQuitterQuirk;
import com.acrylic.universal.entityai.strategy.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.interfaces.Timed;
import com.acrylic.universal.pathfinder.PathGenerator;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityPathfinder<T extends LivingEntityAnimator>
        extends Timed, EntityStrategy<T> {

    enum PathingPhase {
        RESTING,
        TRAVERSING,
        LOOKING_FOR_PATH
    }

    void setEntityQuitter(@Nullable EntityQuitterQuirk<T> entityQuitterQuirk);

    @Nullable
    EntityQuitterQuirk<T> getEntityQuitter();

    void resetTraversing(@NotNull T entityAnimator);

    default void resetResting() {
        setPathingPhase(PathingPhase.RESTING);
        setLastTimed(System.currentTimeMillis() + getRestTimeDuration());
    }

    void setPathingPhase(@NotNull PathingPhase phase);

    PathingPhase getPathingPhase();

    EntityPathfinder<T> setPathGenerator(@NotNull PathGenerator pathGenerator);

    @NotNull
    PathGenerator getPathGenerator();

    EntityPathfinder<T> setMaximumTraverseTime(long maximumTraverseTime);

    long getMaximumTraverseTime();

    /**
     * The walking speed.
     *
     * @param speed Should* be between 0-1. You can specify higher but it will
     *              probably look finicky.
     */
    EntityPathfinder<T> setSpeed(float speed);

    float getSpeed();

    EntityPathfinder<T> setDistanceToPath(float distanceToPath);

    float getDistanceToPath();

    EntityPathfinder<T> setRestTimeAmount(long restTimeAmount);

    void setRestTimeDuration(long time);

    long getRestTimeDuration();

    default void setTargetLocation(@NotNull Entity entity) {
        setTargetLocation(entity.getLocation());
    }

    void setTargetLocation(@NotNull Location location);

    @Nullable
    Location getTargetLocation();

    //void update(@NotNull T entityAnimator, @NotNull FollowerAI<T> entityAI);

}
