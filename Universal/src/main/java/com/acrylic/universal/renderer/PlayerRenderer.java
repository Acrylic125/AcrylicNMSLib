package com.acrylic.universal.renderer;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class PlayerRenderer implements Renderer {

    private final Player player;

    public PlayerRenderer(@NotNull Player player) {
        this.player = player;
    }

    @Override
    public void handle(@NotNull Consumer<Player> action) {
        action.accept(player);
    }
}
