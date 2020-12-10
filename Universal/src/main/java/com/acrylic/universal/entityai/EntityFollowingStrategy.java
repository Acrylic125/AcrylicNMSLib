package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface EntityFollowingStrategy<T extends LivingEntityAnimator> extends EntityStrategy<T> {

    EntityFollowingStrategy<T> setNewTargetDistance(float targetDistance);

    /**'
     * @return Specify -1 if you do not want the entity to
     * switch targets based on surroundings.
     */
    float getNewTargetDistance();

    void setTarget(@NotNull LivingEntity entity);

    LivingEntity getTarget();

}
