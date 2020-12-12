package com.acrylic.universal.entityai.quitterquirk;

import com.acrylic.universal.entityai.EntityQuirk;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.interfaces.Timed;

public interface EntityQuitterQuirk<T extends LivingEntityAnimator>
        extends EntityQuirk<T>, Timed {

    void setGiveUpTimeDuration(long time);

    /**
     * Forcefully teleports the NPC if it is stuck.
     *
     * @return Return -1 if it does not give up.
     */
    long getGiveUpTimeDuration();

    default void resetGiveUpTime() {
        setLastTimed(System.currentTimeMillis() + getGiveUpTimeDuration());
    }

    default boolean isGoingToGiveUp() {
        return getGiveUpTimeDuration() >= 0;
    }

    default boolean isReadyToGiveUp() {
        return isGoingToGiveUp() && System.currentTimeMillis() >= getLastTimed();
    }

}
