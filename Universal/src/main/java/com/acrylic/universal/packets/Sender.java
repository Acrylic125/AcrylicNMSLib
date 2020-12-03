package com.acrylic.universal.packets;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Sender {

    default void sendAll() {
        send(Bukkit.getOnlinePlayers());
    }

    default void sendAll(@NotNull Consumer<Player> sendWithAction) {
        sendWithAction(Bukkit.getOnlinePlayers(), sendWithAction);
    }

    void sendWithAction(@NotNull Player player, @NotNull Consumer<Player> sendWithAction);

    void sendWithAction(@NotNull Consumer<Player> sendWithAction, Player... players);

    void sendWithAction(@NotNull Predicate<Player> condition, @NotNull Consumer<Player> sendWithAction);

    void sendWithAction(@NotNull Location location, float radius, @NotNull Consumer<Player> sendWithAction);

    void sendWithAction(@NotNull Collection<? extends Player> players, @NotNull Consumer<Player> sendWithAction);

    void send(@NotNull Player player);

    void send(Player... players);

    void send(@NotNull Predicate<Player> condition);

    void send(@NotNull Location location, float radius);

    void send(@NotNull Collection<? extends Player> players);


}
