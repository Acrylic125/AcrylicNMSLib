package com.acrylic.universal.misc;

import com.acrylic.universal.loaders.CustomEntity;
import lombok.Getter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class PlayerEntry {

    private final Player player;

    public PlayerEntry(@NotNull Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void execute();

}
