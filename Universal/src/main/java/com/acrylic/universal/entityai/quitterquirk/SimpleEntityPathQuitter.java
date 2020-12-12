package com.acrylic.universal.entityai.quitterquirk;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class SimpleEntityPathQuitter<T extends LivingEntityAnimator>
        implements EntityQuitterQuirk<T> {

    private long giveUpDuration = 10_000;
    private long giveUpTime;

    public SimpleEntityPathQuitter() {
        this.giveUpTime = System.currentTimeMillis() + giveUpDuration;
    }

    @Override
    public void setGiveUpTimeDuration(long time) {
        this.giveUpDuration = time;
    }

    @Override
    public long getGiveUpTimeDuration() {
        return giveUpDuration;
    }

    @Override
    public long getLastTimed() {
        return giveUpTime;
    }

    @Override
    public void setLastTimed(long l) {
        this.giveUpTime = l;
    }

    @Override
    public SimpleEntityPathQuitter<T> clone() {
        SimpleEntityPathQuitter<T> entityPathQuitter = new SimpleEntityPathQuitter<T>();
        entityPathQuitter.setGiveUpTimeDuration(giveUpDuration);
        return entityPathQuitter;
    }

    @Override
    public void update(@NotNull EntityAI<T> ai) {
        if (isReadyToGiveUp() && ai instanceof FollowerAI) {
            resetGiveUpTime();
            EntityPathfinder<T> pathfinder = ((FollowerAI<T>) ai).getPathfinder();
            Location target = pathfinder.getTargetLocation();
            if (target != null) {
                ai.getAnimator().teleport(target);
                pathfinder.resetResting();
            }
        }
    }
}
