package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class DefaultRenderer implements PacketRenderer {

    @Override
    public void send(PacketSender packetSender) {
        packetSender.sendAll();
    }

    @Override
    public void sendWithAction(PacketSender packetSender, @NotNull Consumer<Player> sendWithAction) {
        packetSender.sendAll(sendWithAction);
    }

}
