package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface EntityQuitter<T extends LivingEntityAnimator> {

    void setGiveUpTime(long time);

    long getGiveUpTime();

    void setGiveUpTimeDuration(long time);

    /**
     * Forcefully teleports the NPC if it is stuck.
     *
     * @return Return -1 if it does not give up.
     */
    long getGiveUpTimeDuration();

    default boolean isGoingToGiveUp() {
        return getGiveUpTimeDuration() < 0;
    }

    default boolean isReadyToGiveUp() {
        return isGoingToGiveUp() && System.currentTimeMillis() >= getGiveUpTime();
    }

    void updateGiveUp(@NotNull T entity);


}
