package com.acrylic.universal.renderer;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface Renderer {

    void handle(@NotNull Consumer<Player> action);

}
