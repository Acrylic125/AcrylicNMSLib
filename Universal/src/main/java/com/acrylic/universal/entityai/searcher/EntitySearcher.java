package com.acrylic.universal.entityai.searcher;

import com.acrylic.universal.entityai.TargetableAI;
import com.acrylic.universal.entityai.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface EntitySearcher<T extends LivingEntityAnimator>
        extends EntityStrategy<T> {

    boolean shouldClearFollower();

    boolean canFollow(@NotNull LivingEntity entity);

    List<LivingEntity> getPossibleTargets(@NotNull Location location);

    void setSearchForNewTargetTime(long time);

    long getSearchForNewTargetTime();

    default boolean shouldSearchForTarget() {
        return System.currentTimeMillis() > getSearchForNewTargetTime();
    }

    void setSearchForNewTargetTimeCooldown(long cooldown);

    long getSearchForNewTargetTimeCooldown();

    void setNewTargetDistance(float targetDistance);

    /**'
     * @return Specify -1 if you do not want the entity to
     * switch targets based on surroundings.
     */
    float getNewTargetDistance();

    void setDistanceFromTargetToSwitch(float distanceFromTargetToSwitch);

    float getDistanceFromTargetToSwitch();

    @NotNull
    @Override
    TargetableAI<T> getAI();
}
