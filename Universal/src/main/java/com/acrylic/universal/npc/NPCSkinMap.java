package com.acrylic.universal.npc;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface NPCSkinMap<T extends NPCSkin> {

    Map<String, T> getMap();

    default T getSkin(@NotNull String name) {
        return getMap().get(name);
    }

    /**
     * This method retrieves the data from https://sessionserver.mojang.com/session/minecraft/profile/%uuid%?unsigned=false
     *
     * @param name The player name.
     * @return The skin.
     */
    T getAndAddIfNotExist(@NotNull String name);

    default void addSkin(@NotNull String name, @NotNull T skin) {
        getMap().put(name, skin);
    }

}
