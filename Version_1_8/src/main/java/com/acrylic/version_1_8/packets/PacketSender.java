package com.acrylic.version_1_8.packets;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class PacketSender implements com.acrylic.universal.packets.PacketSender {

    @Override
    public abstract Packet<?>[] getPackets();

    @Override
    public void send(@NotNull Player player) {
        Packet<?>[] packets = getPackets();
        sendPacket(player, packets);
    }

    @Override
    public void send(Player... players) {
        Packet<?>[] packets = getPackets();
        for (Player player : players)
            sendPacket(player, packets);
    }

    @Override
    public void send(@NotNull Location location, float radius) {
        float d = radius * radius;
        send(player -> player.getLocation().distanceSquared(location) <= d);
    }

    @Override
    public void send(@NotNull Predicate<Player> condition) {
        Packet<?>[] packets = getPackets();
        for (Player player : Bukkit.getOnlinePlayers())
            if (condition.test(player))
                sendPacket(player, packets);
    }

    @Override
    public void send(@NotNull Collection<? extends Player> players) {
        Packet<?>[] packets = getPackets();
        for (Player player : players)
            sendPacket(player, packets);
    }

    public static PlayerConnection getPlayerConnection(@NotNull Player player) {
        return NMSBukkitConverter.convertToNMSPlayer(player).playerConnection;
    }

    public static void sendPacket(@NotNull PlayerConnection playerConnection, @NotNull Packet<?> packet) {
        playerConnection.sendPacket(packet);
    }

    public static void sendPacket(@NotNull Player player, @NotNull Packet<?> packet) {
        getPlayerConnection(player).sendPacket(packet);
    }

    public static void sendPacket(@NotNull Player player, @NotNull Packet<?>... packets) {
        PlayerConnection playerConnection = getPlayerConnection(player);
        for (Packet<?> packet : packets)
            sendPacket(playerConnection, packet);
    }

}
