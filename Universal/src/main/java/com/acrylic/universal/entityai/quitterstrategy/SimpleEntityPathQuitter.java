package com.acrylic.universal.entityai.quitterstrategy;

import com.acrylic.universal.entityai.LocationalAI;
import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class SimpleEntityPathQuitter<T extends LivingEntityAnimator>
        implements EntityQuitterStrategy<T> {

    private long giveUpDuration = 10_000;
    private long giveUpTime;
    private final EntityPathfinder<T> pathfinder;

    public SimpleEntityPathQuitter(EntityPathfinder<T> pathfinder) {
        this.pathfinder = pathfinder;
        this.giveUpTime = System.currentTimeMillis() + giveUpDuration;
    }

    @NotNull
    @Override
    public EntityPathfinder<T> getPathfinder() {
        return pathfinder;
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
    public long getTime() {
        return giveUpTime;
    }

    @Override
    public void setTime(long l) {
        this.giveUpTime = l;
    }

    @Override
    public SimpleEntityPathQuitter<T> clone() {
        SimpleEntityPathQuitter<T> entityPathQuitter = new SimpleEntityPathQuitter<T>(pathfinder);
        entityPathQuitter.setGiveUpTimeDuration(giveUpDuration);
        return entityPathQuitter;
    }

    @NotNull
    @Override
    public LocationalAI<T> getAI() {
        return pathfinder.getAI();
    }

    @Override
    public void update() {
        if (isReadyToGiveUp()) {
            resetGiveUpTime();
            Location target = pathfinder.getTargetLocation();
            if (target != null) {
                pathfinder.getAnimator().teleport(target);
                pathfinder.resetResting();
            }
        }
    }
}
