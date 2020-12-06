package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.pathfinder.PathGenerator;
import org.jetbrains.annotations.NotNull;

public interface EntityPathfinder<T extends LivingEntityAnimator> {

    EntityPathfinder<T> setPathGenerator(@NotNull PathGenerator pathGenerator);

    @NotNull
    PathGenerator getPathGenerator();

    EntityPathfinder<T> setSpeed(float speed);

    float getSpeed();

    EntityPathfinder<T> setDistanceToPath(float distanceToPath);

    float getDistanceToPath();

    EntityPathfinder<T> setRestTimeAmount(long restTimeAmount);

    long getRestTimeAmount();

    void setRestTime(long time);

    long getRestTime();

    long getGiveUpTimeAmount();

    void setGiveUpTime(long time);

    /**
     * Forcefully teleports the NPC if it is stuck.
     *
     * @return Return -1 if it does not give up.
     */
    long getGiveUpTime();

    void update(@NotNull T entityAnimator, @NotNull EntityAI entityAI);

}
