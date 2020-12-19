package com.acrylic.universal.packets;

public interface SinglePacketSender extends PacketSender {

    Object getPacket();

    default boolean hasProcessedPacket() {
        return getPacket() != null;
    }

}
