package com.acrylic.universal.packets;

import com.acrylic.universal.renderer.Renderer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Consumer;

public interface PacketSender extends Sender {

    Object[] getPackets();

    void sendWithAction(@NotNull Renderer renderer, @NotNull Consumer<Player> sendWithAction);

    void send(@NotNull Renderer renderer);

    boolean hasInitialized();

}
