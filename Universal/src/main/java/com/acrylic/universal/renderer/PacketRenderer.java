package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public interface PacketRenderer {

    void send(PacketSender packetSender);

    void sendWithAction(PacketSender packetSender, @NotNull Consumer<Player> sendWithAction);

}
