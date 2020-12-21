package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.events.AbstractEventBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.WeakHashMap;

public class GlobalNMSEntityMap implements EntityMap<NMSEntityAnimator> {

    private final Map<Integer, NMSEntityAnimator> map = new WeakHashMap<>();

    @Override
    public Map<Integer, NMSEntityAnimator> getMap() {
        return map;
    }

    @NotNull
    @Override
    public AbstractEventBuilder<PlayerJoinEvent> getJoinEventLoader() {
        return EventBuilder.listen(PlayerJoinEvent.class)
                .handle(event -> {
                    Player player = event.getPlayer();
                    loadEntities(player);
                })
                ;
    }

    @NotNull
    @Override
    public AbstractEventBuilder<PlayerQuitEvent> getQuitEventUnloader() {
        return EventBuilder.listen(PlayerQuitEvent.class)
                .handle(event -> {
                    Player player = event.getPlayer();
                    clearEntities(player);
                });
    }

    @NotNull
    @Override
    public AbstractEventBuilder<PlayerRespawnEvent> getRespawnEventLoader() {
        return EventBuilder.listen(PlayerRespawnEvent.class)
                .handle(event -> {
                    Player player = event.getPlayer();
                    reloadEntities(player);
                });
    }

    @Override
    public void loadEntities(@NotNull Player player) {
        for (NMSEntityAnimator entityAnimator : map.values()) {
            InitializerLocationalRenderer renderer = entityAnimator.getRenderer();
            if (renderer.canInitialize(player))
                renderer.render(player);
        }
    }

    @Override
    public void reloadEntities(@NotNull Player player) {
        for (NMSEntityAnimator entityAnimator : map.values()) {
            InitializerLocationalRenderer renderer = entityAnimator.getRenderer();
            renderer.unrender(player);
            if (renderer.canInitialize(player)) {
                renderer.render(player);
            }
        }
    }

    @Override
    public void clearEntities(@NotNull Player player) {
        for (NMSEntityAnimator entityAnimator : map.values())
            entityAnimator.getRenderer().unrender(player);
    }
}
