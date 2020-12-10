package com.acrylic.universal.entityai;

import com.acrylic.universal.Universal;
import com.acrylic.universal.npc.PlayerNPCEntity;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class SimpleEntityPathfinder<T extends PlayerNPCEntity>
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

    private void handleResting(@NotNull T entityAnimator) {
        Location target = getTargetLocation();
        if (shouldSwitchPathing() && target != null && target.distanceSquared(entityAnimator.getLocation()) >= 9) {
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

    private void handleTraversing(@NotNull T entityAnimator) {
        final float speed = getSpeed();
        traversingIndex += speed;
        int index = (int) Math.floor(traversingIndex);
        if (index >= locations.length || shouldSwitchPathing()) {
            setPathingPhase(PathingPhase.RESTING);
            setLastTimed(getRestTimeDuration() + System.currentTimeMillis());
        } else {
            // Vector dV = now.toVector().add(loc.toVector().multiply(-1));
            //                                loc.setDirection(dV.clone().normalize());
            //                                npc.teleport(loc);
            //                                if (dV.getY() > 0 || (BlockExaminer.isLiquid(loc.getBlock().getType()))) {
            //                                    dV.setY(dV.getY() + ((BlockExaminer.isLiquid(loc.getBlock().getType())) ? 0.1f : 1));
            //                                } else if (!npc.getNMSEntity().onGround) {
            //                                    dV.setY(dV.getY() - 1f);
            //                                }
            //                                npc.attack(sender);
            //                                dV.multiply(1f / s);
            //                                npc.getNMSEntity().noclip = now.getBlock().getType().equals(Material.WOODEN_DOOR);
            //                                npc.setVelocity(dV);
            //                                i += 1f / s;
            Location location = locations[index];
            Vector dV = location.toVector().add(entityAnimator.getBukkitEntity().getLocation().toVector().multiply(-1));
            double y = dV.getY();
            if (y > 0)
                dV.setY(y + 12f);
            dV.multiply(speed);
            entityAnimator.setYawAndPitch((float) getYawAngle(dV),  (float) dV.getY() * -1f * 90f);
            entityAnimator.updateHeadPose();
            entityAnimator.setVelocity(dV.getX(), dV.getY(), dV.getZ());
        }
    }

    private double getYawAngle(Vector vector) {
        return Math.toDegrees(Math.atan2(vector.getZ(), vector.getX()) - 90f);
    }

}
