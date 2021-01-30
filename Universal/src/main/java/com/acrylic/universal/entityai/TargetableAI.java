package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface TargetableAI<T extends LivingEntityAnimator>
        extends LocationalAI<T> {

    void setTarget(@Nullable Entity entity);

    @Nullable
    Entity getTarget();

}
