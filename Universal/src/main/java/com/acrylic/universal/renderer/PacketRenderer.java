package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface PacketRenderer {

    void send(PacketSender packetSender);

    default void send(PacketSender... packetSender) {
        for (PacketSender sender : packetSender)
            send(sender);
    }

    void sendWithAction(PacketSender packetSender, @NotNull Consumer<Player> sendWithAction);

    default void sendWithAction(@NotNull Consumer<Player> sendWithAction, PacketSender... packetSender) {
        for (PacketSender sender : packetSender)
            sendWithAction(sender, sendWithAction);
    }

}
