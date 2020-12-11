package com.acrylic.universal.entityai;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSimpleEntityPathfinder<T extends LivingEntityAnimator>
        extends AbstractEntityPathfinder<T> {

    private float traversingIndex = 0;
    private Location[] locations;

    public void generateLocations(@NotNull T entityAnimator) {
        Location location = getTargetLocation();
        if (location != null)
           generateLocations(entityAnimator, location);
    }

    public void generateLocations(@NotNull T entityAnimator, @NotNull Location location) {
        locations = getPathGenerator().traverseAndCompute(entityAnimator.getBukkitEntity().getLocation(), location);
    }

    private boolean shouldSwitchPathing() {
        return System.currentTimeMillis() > getLastTimed();
    }

    public boolean isWithinRangeOfTarget(@NotNull T entityAnimator) {
        Location target = getTargetLocation();
        return target != null && target.distanceSquared(entityAnimator.getBukkitEntity().getLocation()) >= 9;
    }

    private synchronized void resetTraverser(@NotNull T entityAnimator) {
        generateLocations(entityAnimator);
        setLastTimed(System.currentTimeMillis() + getMaximumTraverseTime());
        traversingIndex = 0;
        setPathingPhase(PathingPhase.TRAVERSING);
    }

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        PathingPhase phase = getPathingPhase();
        switch (phase) {
            case RESTING:
                handleResting(entityAnimator);
                break;
            case TRAVERSING:
                handleTraversing(entityAnimator);
        }
    }

    @Override
    public void updateGiveUp(@NotNull T entity) {
        if (isReadyToGiveUp()) {
            Location location = getTargetLocation();
            if (location != null)
               entity.teleport(location);
        }
    }

    public void handleResting(@NotNull T entityAnimator) {
        if (shouldSwitchPathing() && isWithinRangeOfTarget(entityAnimator)) {
            setPathingPhase(PathingPhase.LOOKING_FOR_PATH);

            /**
             * Traversing is performance heavy therefore it is done in async
             * to preserve performance.
             */
            new BukkitRunnable() {
                @Override
                public void run() {
                    resetTraverser(entityAnimator);
                }
            }.runTaskAsynchronously(Universal.getPlugin());
        }
    }

    public void handleTraversing(@NotNull T entityAnimator) {
        final float speed = getSpeed();
        traversingIndex += speed;
        int index = (int) Math.floor(traversingIndex);
        if (index >= locations.length || shouldSwitchPathing()) {
            setPathingPhase(PathingPhase.RESTING);
            setLastTimed(getRestTimeDuration() + System.currentTimeMillis());
        } else {
            Location location = locations[index];
            Location current = entityAnimator.getBukkitEntity().getLocation();
            moveEntity(entityAnimator, current, location,
                    (location.getX() - current.getX()) * speed,
                    (location.getY() - current.getY()) * speed,
                    (location.getZ() - current.getZ()) * speed);
        }
    }

    public abstract void moveEntity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z);

}
