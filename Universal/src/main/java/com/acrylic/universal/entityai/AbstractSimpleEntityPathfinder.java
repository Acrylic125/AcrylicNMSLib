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

    public boolean hasTarget(@NotNull T entityAnimator) {
        return getTargetLocation() != null;
    }

    public boolean shouldTraverseTarget(@NotNull T entityAnimator) {
        Location target = getTargetLocation();
        return target != null && target.distanceSquared(entityAnimator.getBukkitEntity().getLocation()) >= 9;
    }

    public void updateQuitter(@NotNull T entityAnimator, @NotNull EntityAI<T> ai) {
        Location target = getTargetLocation();
        if (target == null || target.distanceSquared(entityAnimator.getBukkitEntity().getLocation()) <= 9) {
            EntityQuitterQuirk<T> quitterQuirk = ai.getEntityQuitter();
            if (quitterQuirk != null)
               quitterQuirk.resetGiveUpTime();
        }
    }

    @Override
    public synchronized void resetTraversing(@NotNull T entityAnimator) {
        generateLocations(entityAnimator);
        setLastTimed(System.currentTimeMillis() + getMaximumTraverseTime());
        traversingIndex = 0;
        setPathingPhase(PathingPhase.TRAVERSING);
    }

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        updateQuitter(entityAnimator, entityAI);
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
            new BukkitRunnable() {
                @Override
                public void run() {
                    resetTraversing(entityAnimator);
                }
            }.runTaskAsynchronously(Universal.getPlugin());
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

    public abstract void moveEntity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z);

}
