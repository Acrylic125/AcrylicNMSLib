package com.acrylic.universal.entityai.quitterstrategy;

import com.acrylic.universal.entityai.strategy.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.interfaces.Timed;

public interface EntityQuitterStrategy<T extends LivingEntityAnimator>
        extends EntityStrategy<T>, Timed {

    void setGiveUpTimeDuration(long time);

    /**
     * Forcefully teleports the NPC if it is stuck.
     *
     * @return Return -1 if it does not give up.
     */
    long getGiveUpTimeDuration();

    default void resetGiveUpTime() {
        addTimeToNow(getGiveUpTimeDuration());
    }

    default boolean isGoingToGiveUp() {
        return getGiveUpTimeDuration() >= 0;
    }

    default boolean isReadyToGiveUp() {
        return isGoingToGiveUp() && System.currentTimeMillis() >= getTime();
    }

}
