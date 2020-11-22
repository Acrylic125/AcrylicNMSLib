package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;

public final class DefaultRenderer implements PacketRenderer {

    @Override
    public void send(PacketSender packetSender) {
        packetSender.sendAll();
    }
}
