package com.acrylic.universal.text;

import com.acrylic.universal.packets.PacketSender;

public interface DurationTextSender extends PacketSender {

    DurationTextSender text(String text);

    String getText();

    DurationTextSender build();

}
