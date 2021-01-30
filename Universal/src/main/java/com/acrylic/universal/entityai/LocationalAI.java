package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocationalAI<T extends LivingEntityAnimator>
        extends EntityAI<T> {

    void setTargetLocation(@Nullable Location location);

    default void setTargetLocation(@NotNull Entity entity) {
        setTargetLocation(entity.getLocation());
    }

    @Nullable
    Location getTargetLocation();

}
