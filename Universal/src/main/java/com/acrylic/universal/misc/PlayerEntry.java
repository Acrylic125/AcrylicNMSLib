package com.acrylic.universal.misc;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class PlayerEntry {

    private final Player player;

    public PlayerEntry(@NotNull Player player) {
        this.player = player;
    }

    public abstract void execute();

}
