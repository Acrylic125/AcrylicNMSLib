package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.EntityInstance;
import net.minecraft.server.v1_8_R3.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityInstance_1_8
        extends EntityInstance {

    default Entity getNMSEntity() {
        return getAnimatior().getNMSEntity();
    }

    @NotNull
    @Override
    NMSEntityAnimator getAnimatior();

    @Override
    default void removeFromWorld() {
        Entity entity = getNMSEntity();
        entity.getWorld().removeEntity(entity);
    }

    @Override
    default void addToWorld() {
        Entity entity = getNMSEntity();
        entity.getWorld().addEntity(entity);
    }
}
