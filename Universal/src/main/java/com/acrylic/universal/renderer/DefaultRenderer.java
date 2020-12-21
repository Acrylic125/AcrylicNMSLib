package com.acrylic.universal.renderer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class DefaultRenderer implements Renderer {

    @Override
    public void handle(@NotNull Consumer<Player> action) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            action.accept(onlinePlayer);
        }
    }
}
