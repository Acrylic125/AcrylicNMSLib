package com.acrylic.universal.entityai.quitterquirk;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class SimpleEntityPathQuitter<T extends LivingEntityAnimator>
        implements EntityQuitterQuirk<T> {

    private long giveUpDuration = 10_000;
    private transient long giveUpTime = 0;

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        if (isReadyToGiveUp()) {
            resetGiveUpTime();
            EntityPathfinder<T> pathfinder = entityAI.getPathfinder();
            if (pathfinder != null) {
                Location target = pathfinder.getTargetLocation();
                if (target != null) {
                    entityAnimator.teleport(target);
                    pathfinder.resetResting();
                }
            }
        }
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
}