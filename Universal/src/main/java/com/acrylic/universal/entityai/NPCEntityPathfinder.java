package com.acrylic.universal.entityai;

import com.acrylic.universal.npc.PlayerNPCEntity;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class NPCEntityPathfinder<T extends PlayerNPCEntity>
        extends AbstractSimpleEntityPathfinder<T> {

    @Override
    public void moveEntity(@NotNull T entityAnimator, @NotNull Location currentLoc, @NotNull Location toLocation, double x, double y, double z) {
        entityAnimator.setYawAndPitch((float) getYawAngle(x, z), (float) (y * -1f * 90f));
        entityAnimator.updateHeadPose();
        entityAnimator.setVelocity(x, y, z);
    }

    private double getYawAngle(double x, double z) {
        return Math.toDegrees(Math.atan2(z, x) - 90f);
    }

}
