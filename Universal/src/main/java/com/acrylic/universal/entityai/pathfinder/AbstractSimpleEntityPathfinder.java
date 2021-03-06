package com.acrylic.universal.entityai.pathfinder;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.entityai.LocationalAI;
import com.acrylic.universal.entityai.quitterstrategy.EntityQuitterStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.threads.Scheduler;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractSimpleEntityPathfinder<T extends LivingEntityAnimator>
        extends AbstractEntityPathfinder<T> {

    private float traversingIndex = 0;
    private Location[] locations;
    private final LocationalAI<T> ai;
    private EntityQuitterStrategy<T> quitterStrategy;

    public AbstractSimpleEntityPathfinder(@NotNull LocationalAI<T> ai) {
        this.ai = ai;
    }

    @Nullable
    @Override
    public EntityQuitterStrategy<T> getQuitterStrategy() {
        return quitterStrategy;
    }

    @Override
    public void setQuitterStrategy(@Nullable EntityQuitterStrategy<T> strategy) {
        this.quitterStrategy = strategy;
    }

    public void generateLocations(@NotNull T entityAnimator) {
        Location location = getTargetLocation();
        if (location != null)
           generateLocations(entityAnimator, location);
    }

    public void generateLocations(@NotNull T entityAnimator, @NotNull Location location) {
        locations = getPathGenerator().traverseAndCompute(entityAnimator.getBukkitEntity().getLocation(), location);
    }

    private boolean shouldSwitchPathing() {
        return System.currentTimeMillis() > getTime();
    }

    public boolean hasTarget(@NotNull T entityAnimator) {
        return getTargetLocation() != null;
    }

    public boolean shouldTraverseTarget(@NotNull T entityAnimator) {
        Location target = getTargetLocation();
        return target != null && target.distanceSquared(entityAnimator.getBukkitEntity().getLocation()) >= 9;
    }

    public void updateQuitter(@NotNull T entityAnimator, @NotNull EntityAI<T> ai) {
        Location target = getTargetLocation();
        EntityQuitterStrategy<T> quitterQuirk = quitterStrategy;
        if (quitterQuirk != null) {
            if (target == null || target.distanceSquared(entityAnimator.getBukkitEntity().getLocation()) <= 9)
                quitterQuirk.resetGiveUpTime();
        }
    }

    @Override
    public synchronized void resetTraversing(@NotNull T entityAnimator) {
        generateLocations(entityAnimator);
        addTimeToNow(getMaximumTraverseTime());
        traversingIndex = 0;
        setPathingPhase(PathingPhase.TRAVERSING);
    }

    @Override
    public void update() {
        if (quitterStrategy != null)
            quitterStrategy.update();
        T entityAnimator = ai.getAnimator();
        updateQuitter(entityAnimator, ai);
        PathingPhase phase = getPathingPhase();
        switch (phase) {
            case RESTING:
                handleResting(entityAnimator);
                break;
            case TRAVERSING:
                handleTraversing(entityAnimator);
        }
    }

    public void handleResting(@NotNull T entityAnimator) {
        if (shouldSwitchPathing() && shouldTraverseTarget(entityAnimator)) {
            setPathingPhase(PathingPhase.LOOKING_FOR_PATH);

            /**
             * Traversing is performance heavy therefore it is done in async
             * to preserve performance.
             */
            Scheduler.async().runTask().handle(task -> {
                resetTraversing(entityAnimator);
            }).build();
        } else {
            Location target = getTargetLocation();
            if (target != null) {
                Location current = entityAnimator.getBukkitEntity().getLocation();
                updateHeadPose(entityAnimator, target.getX() - current.getX(), target.getY() - current.getY(), target.getZ() - current.getZ());
            }
        }
    }

    public void handleTraversing(@NotNull T entityAnimator) {
        final float speed = getSpeed();
        traversingIndex += speed;
        int index = (int) Math.floor(traversingIndex);
        if (index >= locations.length || shouldSwitchPathing()) {
            resetResting();
        } else {
            Location location = locations[index];
            Location current = entityAnimator.getBukkitEntity().getLocation();
            moveEntity(entityAnimator, current, location,
                    (location.getX() - current.getX()) * speed,
                    (location.getY() - current.getY()) * speed,
                    (location.getZ() - current.getZ()) * speed);
        }
    }

    @NotNull
    @Override
    public LocationalAI<T> getAI() {
        return ai;
    }

    public abstract void updateHeadPose(@NotNull T entityAnimator, double x, double y, double z);

    public abstract void moveEntity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z);

}
