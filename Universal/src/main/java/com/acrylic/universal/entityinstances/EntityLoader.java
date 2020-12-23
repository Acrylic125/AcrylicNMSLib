package com.acrylic.universal.entityinstances;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.events.EventBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

public interface EntityLoader {

    default void setup() {
        getJoinEventLoader().register();
        getQuitEventUnloader().register();
        getRespawnEventLoader().register();
    }

    @NotNull
    AbstractEventBuilder<PlayerJoinEvent> getJoinEventLoader();

    @NotNull
    AbstractEventBuilder<PlayerQuitEvent> getQuitEventUnloader();

    @NotNull
    AbstractEventBuilder<PlayerRespawnEvent> getRespawnEventLoader();

    void loadEntities(@NotNull Player player);

    void reloadEntities(@NotNull Player player);

    void clearEntities(@NotNull Player player);

}
