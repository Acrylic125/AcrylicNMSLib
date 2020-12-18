package com.acrylic.universal.entityai.pathfinder;

import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.quitterstrategy.EntityQuitterStrategy;
import com.acrylic.universal.entityai.quitterstrategy.NoClipEntityPathQuitter;
import com.acrylic.universal.npc.PlayerNPCEntity;
import com.acrylic.universal.pathfinder.BlockExaminer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class NPCEntityPathfinder<T extends PlayerNPCEntity>
        extends AbstractSimpleEntityPathfinder<T> {

    public NPCEntityPathfinder(@NotNull FollowerAI<T> ai) {
        super(ai);
    }

    private void moveTo(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z) {
        BlockExaminer blockExaminer = getPathGenerator().getBlockExaminer();
        BlockExaminer.NavigationStyle navigationStyle = blockExaminer.getNavigationStyle(currentLoc);
        if (navigationStyle == BlockExaminer.NavigationStyle.CLIMB)
            y = 0.6f;
        else if (entityAnimator.isUsingGravity() && y > 0)
            y += 1.5f;
        Bukkit.broadcastMessage(toLocation.getX() + " " + toLocation.getY() + " " + toLocation.getZ());
        entityAnimator.setVelocity(x, y, z);
    }

    public void handleNoClip(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation) {
        EntityQuitterStrategy<T> quitterQuirk = getAI().getEntityQuitter();
        if (quitterQuirk instanceof NoClipEntityPathQuitter && ((NoClipEntityPathQuitter<T>) quitterQuirk).isNoClipActive())
            return;
        BlockExaminer blockExaminer = getPathGenerator().getBlockExaminer();
        entityAnimator.setNoClip(blockExaminer.shouldNoClip(currentLoc.getBlock()) || blockExaminer.shouldNoClip(toLocation.getBlock()));
    }

    @Override
    public void updateHeadPose(@NotNull T entityAnimator, double x, double y, double z) {
        entityAnimator.setYawAndPitch((float) getYawAngle(x, z), (float) ((1f / Math.sqrt(x * x + y * y + z * z)) * y * -90f));
        entityAnimator.updateHeadPose();
    }

    @Override
    public void moveEntity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z) {
        updateHeadPose(entityAnimator, x, y, z);
        handleNoClip(entityAnimator, currentLoc, toLocation);
        moveTo(entityAnimator, currentLoc, toLocation, x, y, z);
    }

    private double getYawAngle(double x, double z) {
        return Math.toDegrees(Math.atan2(z, x) - 90f);
    }

}
