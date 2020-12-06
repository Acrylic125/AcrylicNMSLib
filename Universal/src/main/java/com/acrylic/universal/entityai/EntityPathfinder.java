package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface EntityPathfinder<T extends LivingEntityAnimator> {

    float getSpeed();

    float getDistanceToPath();

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

    void toNextNode();

    BlockExaminer getBlockExaminer();

    void update(@NotNull T entityAnimator, @NotNull EntityAI entityAI);

}
