package com.acrylic.universal.entityai;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class SimpleEntityPathfinder<T extends LivingEntityAnimator>
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

    private void phaseSwitcher(@NotNull PathingPhase switchTo, long timeToCompare, long switchToTimeDuration) {
        if (System.currentTimeMillis() < timeToCompare) {
            setPathingPhase(switchTo);
            setLastTimed(System.currentTimeMillis() + switchToTimeDuration);
        }
    }

    private boolean shouldSwitchPathing() {
        return System.currentTimeMillis() > getLastTimed();
    }

    private synchronized void resetTraverser(@NotNull T entityAnimator) {
        generateLocations(entityAnimator);
        setGiveUpTimeDuration(System.currentTimeMillis() + getGiveUpTimeDuration());
        setPathingPhase(PathingPhase.TRAVERSING);
    }

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        PathingPhase phase = getPathingPhase();
        switch (phase) {
            case RESTING:
                if (shouldSwitchPathing()) {
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
                break;
            case TRAVERSING:

        }
    }

    @Override
    public void updateGiveUp(@NotNull T entity) {
        if (isReadyToGiveUp()) {

        }
    }
}
