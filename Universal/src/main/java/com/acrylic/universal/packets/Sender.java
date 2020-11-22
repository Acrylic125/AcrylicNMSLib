package com.acrylic.universal.packets;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public interface Sender {

    void sendAll();

    void send(@NotNull Player player);

    void send(Player... players);

    void send(@NotNull Predicate<Player> condition);

    void send(@NotNull Location location, float radius);


}
