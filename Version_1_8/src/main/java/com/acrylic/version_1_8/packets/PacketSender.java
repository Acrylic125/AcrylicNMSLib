package com.acrylic.version_1_8.packets;

import com.acrylic.universal.renderer.Renderer;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class PacketSender implements com.acrylic.universal.packets.PacketSender {

    @Override
    public abstract Packet<?>[] getPackets();

    protected void validatePacket() {
        if (!hasInitialized())
            throw new IllegalStateException("The packet, " + getClass() + " has not been initialized.");
    }

    @Override
    public void sendWithAction(@NotNull Player player, @NotNull Consumer<Player> sendWithAction) {
        validatePacket();
        send(player);
        sendWithAction.accept(player);
    }

    @Override
    public void sendWithAction(@NotNull Consumer<Player> sendWithAction, Player... players) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        for (Player player : players) {
            sendPacket(player, packets);
            sendWithAction.accept(player);
        }
    }

    @Override
    public void sendWithAction(@NotNull Predicate<Player> condition, @NotNull Consumer<Player> sendWithAction) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        for (Player player : Bukkit.getOnlinePlayers())
            if (condition.test(player)) {
                sendPacket(player, packets);
                sendWithAction.accept(player);
            }
    }

    @Override
    public void sendWithAction(@NotNull Location location, float radius, @NotNull Consumer<Player> sendWithAction) {
        float d = radius * radius;
        sendWithAction(player -> player.getLocation().distanceSquared(location) <= d, sendWithAction);
    }

    @Override
    public void sendWithAction(@NotNull Collection<? extends Player> players, @NotNull Consumer<Player> sendWithAction) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        for (Player player : players) {
            sendPacket(player, packets);
            sendWithAction.accept(player);
        }
    }

    @Override
    public void send(@NotNull Player player) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        sendPacket(player, packets);
    }

    @Override
    public void send(Player... players) {
        validatePacket();
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
        validatePacket();
        Packet<?>[] packets = getPackets();
        for (Player player : Bukkit.getOnlinePlayers())
            if (condition.test(player))
                sendPacket(player, packets);
    }

    @Override
    public void send(@NotNull Collection<? extends Player> players) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        for (Player player : players)
            sendPacket(player, packets);
    }

    @Override
    public void sendWithAction(@NotNull Renderer renderer, @NotNull Consumer<Player> sendWithAction) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        renderer.handle(player -> {
            sendPacket(player, packets);
            sendWithAction.accept(player);
        });
    }

    @Override
    public void send(@NotNull Renderer renderer) {
        validatePacket();
        Packet<?>[] packets = getPackets();
        renderer.handle(player -> sendPacket(player, packets));
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
