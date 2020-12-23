package com.acrylic.universal.entityinstances;

import com.acrylic.universal.threads.Scheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface RespawnableInstance {

    default boolean isUsingRespawnStrategy() {
        return getRespawnStrategy() != null;
    }

    default void checkRespawn(@NotNull LivingEntityInstance livingEntityInstance) {
        if (livingEntityInstance.isDead() && isUsingRespawnStrategy() && Objects.requireNonNull(getRespawnStrategy()).canRespawn())
            getRespawnStrategy().respawn(this);
    }

    default void handleDeath(@NotNull EntityInstance entityInstance) {
        if (!isUsingRespawnStrategy())
            Scheduler.sync().runDelayedTask(50).handle(task -> entityInstance.delete()).build();
        else
            Objects.requireNonNull(getRespawnStrategy()).resetRespawnTime();
    }

    void setRespawnStrategy(@NotNull RespawnStrategy respawnStrategy);

    @Nullable
    RespawnStrategy getRespawnStrategy();

    void respawn();

}
