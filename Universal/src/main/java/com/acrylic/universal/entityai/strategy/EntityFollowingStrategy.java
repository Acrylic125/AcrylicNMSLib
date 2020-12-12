package com.acrylic.universal.entityai.strategy;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.entityai.quitterquirk.EntityQuitterQuirk;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface EntityFollowingStrategy<T extends LivingEntityAnimator>
        extends EntityStrategy<T> {

    boolean canFollow(@NotNull LivingEntity entity);

    List<LivingEntity> getPossibleTargets(@NotNull Location location);

    void setSearchForNewTargetTime(long time);

    long getSearchForNewTargetTime();

    default boolean shouldSearchForTarget() {
        return System.currentTimeMillis() > getSearchForNewTargetTime();
    }

    void setSearchForNewTargetTimeCooldown(long cooldown);

    long getSearchForNewTargetTimeCooldown();

    EntityFollowingStrategy<T> setNewTargetDistance(float targetDistance);

    /**'
     * @return Specify -1 if you do not want the entity to
     * switch targets based on surroundings.
     */
    float getNewTargetDistance();

    EntityFollowingStrategy<T> setDistanceFromTargetToSwitch(float distanceFromTargetToSwitch);

    float getDistanceFromTargetToSwitch();

    void setTarget(@Nullable LivingEntity entity);

    @Nullable
    LivingEntity getTarget();

}
