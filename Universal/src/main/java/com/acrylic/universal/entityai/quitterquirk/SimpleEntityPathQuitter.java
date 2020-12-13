package com.acrylic.universal.entityai.quitterquirk;

import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class SimpleEntityPathQuitter<T extends LivingEntityAnimator>
        implements EntityQuitterStrategy<T> {

    private long giveUpDuration = 10_000;
    private long giveUpTime;
    private final FollowerAI<T> ai;

    public SimpleEntityPathQuitter(FollowerAI<T> ai) {
        this.ai = ai;
        this.giveUpTime = System.currentTimeMillis() + giveUpDuration;
        ai.setEntityQuitter(this);
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
        SimpleEntityPathQuitter<T> entityPathQuitter = new SimpleEntityPathQuitter<T>(ai);
        entityPathQuitter.setGiveUpTimeDuration(giveUpDuration);
        return entityPathQuitter;
    }

    @NotNull
    @Override
    public FollowerAI<T> getAI() {
        return ai;
    }

    @Override
    public void update() {
        if (isReadyToGiveUp() && ai != null) {
            resetGiveUpTime();
            EntityPathfinder<T> pathfinder = ai.getPathfinder();
            Location target = pathfinder.getTargetLocation();
            if (target != null) {
                ai.getAnimator().teleport(target);
                pathfinder.resetResting();
            }
        }
    }
}
