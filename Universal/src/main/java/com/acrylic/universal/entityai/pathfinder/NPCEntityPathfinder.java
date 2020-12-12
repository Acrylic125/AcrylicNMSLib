package com.acrylic.universal.entityai.pathfinder;

import com.acrylic.universal.entityai.quitterquirk.EntityQuitterQuirk;
import com.acrylic.universal.entityai.quitterquirk.NoClipEntityPathQuitter;
import com.acrylic.universal.npc.PlayerNPCEntity;
import com.acrylic.universal.pathfinder.BlockExaminer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class NPCEntityPathfinder<T extends PlayerNPCEntity>
        extends AbstractSimpleEntityPathfinder<T> {

    private double getAdditiveYVelocity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z) {
        BlockExaminer blockExaminer = getPathGenerator().getBlockExaminer();
        BlockExaminer.NavigationStyle navigationStyle = blockExaminer.getNavigationStyle(currentLoc.getBlock());
        switch (navigationStyle) {
            case SWIM:
                y = 0.05f + 0.5f;
                break;
            case CASUAL_SWIM:
                y = 0.02f + 0.5f;
                break;
            default:
                if (entityAnimator.isUsingGravity() && y > 0)
                    y += 1.5f;
        }
        return y;
    }

    public void handleNoClip(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation) {
        EntityQuitterQuirk<T> quitterQuirk = getEntityQuitter();
        if (quitterQuirk instanceof NoClipEntityPathQuitter && ((NoClipEntityPathQuitter<T>) quitterQuirk).isNoClipActive())
            return;
        BlockExaminer blockExaminer = getPathGenerator().getBlockExaminer();
        entityAnimator.setNoClip(blockExaminer.shouldNoClip(currentLoc.getBlock()) || blockExaminer.shouldNoClip(toLocation.getBlock()));
    }

    @Override
    public void updateHeadPose(@NotNull T entityAnimator, double x, double y, double z) {
        entityAnimator.setYawAndPitch((float) getYawAngle(x, z), (float) (y * -1f * 90f));
        entityAnimator.updateHeadPose();
    }

    @Override
    public void moveEntity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z) {
        updateHeadPose(entityAnimator, x, y, z);
        handleNoClip(entityAnimator, currentLoc, toLocation);
        entityAnimator.setVelocity(x, getAdditiveYVelocity(entityAnimator, currentLoc, toLocation, x, y, z), z);
    }

    private double getYawAngle(double x, double z) {
        return Math.toDegrees(Math.atan2(z, x) - 90f);
    }

    @Override
    public NPCEntityPathfinder<T> clone() {
        NPCEntityPathfinder<T> pathfinder = new NPCEntityPathfinder<>();
        pathfinder.setDistanceToPath(getDistanceToPath());
        pathfinder.setPathGenerator(getPathGenerator());
        pathfinder.setSpeed(getSpeed());
        pathfinder.setDistanceToPath(getDistanceToPath());
        pathfinder.setMaximumTraverseTime(getMaximumTraverseTime());
        pathfinder.setRestTimeDuration(getRestTimeDuration());
        return pathfinder;
    }

}
