package com.acrylic.universal.packets;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Predicate;

public interface Sender {

    default void sendAll() {
        send(Bukkit.getOnlinePlayers());
    }

    void send(@NotNull Player player);

    void send(Player... players);

    void send(@NotNull Predicate<Player> condition);

    void send(@NotNull Location location, float radius);

    void send(@NotNull Collection<? extends Player> players);


}
