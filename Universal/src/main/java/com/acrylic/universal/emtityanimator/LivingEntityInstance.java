package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityai.EntityAI;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LivingEntityInstance
        extends EntityInstance, DamageableEntityInstance {

    default void healEntity(double amount) {
        LivingEntity entity = getAnimatior().getBukkitEntity();
        entity.setHealth(Math.max(amount + entity.getHealth(), entity.getMaxHealth()));
    }

    default void healEntity() {
        healEntity(getAnimatior().getBukkitEntity().getMaxHealth());
    }

    void setAI(@Nullable EntityAI<?> ai);

    @Nullable
    EntityAI<?> getAI();

    void setAnimator(@Nullable NMSLivingEntityAnimator animator);

    @NotNull
    @Override
    NMSLivingEntityAnimator getAnimatior();

}
