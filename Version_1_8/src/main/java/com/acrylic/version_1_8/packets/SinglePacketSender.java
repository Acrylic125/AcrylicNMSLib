package com.acrylic.version_1_8.packets;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class SinglePacketSender extends PacketSender implements com.acrylic.universal.packets.SinglePacketSender {

    @Override
    public abstract Packet<?> getPacket();

    @Override
    public Packet<?>[] getPackets() {
        return new Packet<?>[] { getPacket() };
    }

    @Override
    public void sendWithAction(@NotNull Consumer<Player> sendWithAction, Player... players) {
        Packet<?> packets = getPacket();
        for (Player player : players) {
            sendPacket(player, packets);
            sendWithAction.accept(player);
        }
    }

    @Override
    public void sendWithAction(@NotNull Predicate<Player> condition, @NotNull Consumer<Player> sendWithAction) {
        Packet<?> packets = getPacket();
        for (Player player : Bukkit.getOnlinePlayers())
            if (condition.test(player)) {
                sendPacket(player, packets);
                sendWithAction.accept(player);
            }
    }

    @Override
    public void sendWithAction(@NotNull Collection<? extends Player> players, @NotNull Consumer<Player> sendWithAction) {
        Packet<?> packets = getPacket();
        for (Player player : players) {
            sendPacket(player, packets);
            sendWithAction.accept(player);
        }
    }

    @Override
    public void send(@NotNull Player player) {
        PacketSender.sendPacket(player, getPacket());
    }

    @Override
    public void send(Player... players) {
        Packet<?> packet = getPacket();
        for (Player player : Bukkit.getOnlinePlayers())
            PacketSender.sendPacket(player, packet);
    }

    @Override
    public void send(@NotNull Predicate<Player> condition) {
        Packet<?> packet = getPacket();
        for (Player player : Bukkit.getOnlinePlayers())
            if (condition.test(player))
                sendPacket(player, packet);
    }

    @Override
    public void send(@NotNull Collection<? extends Player> players) {
        Packet<?> packet = getPacket();
        for (Player player : players)
            sendPacket(player, packet);
    }

}
