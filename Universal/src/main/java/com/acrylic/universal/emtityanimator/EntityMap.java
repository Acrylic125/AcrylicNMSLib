package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.events.EventBuilder;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface EntityMap<T extends NMSEntityAnimator>
        extends EntityLoader {

    Map<Integer, T> getMap();

    @Nullable
    default T getEntityAnimator(int id) {
        return getMap().get(id);
    }

    @Nullable
    default T getEntityAnimator(@NotNull Entity entity) {
        return getEntityAnimator(entity.getEntityId());
    }

    default void addEntityAnimator(@NotNull T entityAnimator) {
        getMap().put(entityAnimator.getBukkitEntity().getEntityId(), entityAnimator);
    }

    default void removeEntityAnimator(int id) {
        getMap().remove(id);
    }

    default void removeEntityAnimator(@NotNull Entity entity) {
        removeEntityAnimator(entity.getEntityId());
    }

    default void removeEntityAnimator(@NotNull T entityAnimator) {
        removeEntityAnimator(entityAnimator.getBukkitEntity());
    }

}
