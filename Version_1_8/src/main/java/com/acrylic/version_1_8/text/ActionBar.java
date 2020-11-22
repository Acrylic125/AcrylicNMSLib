package com.acrylic.version_1_8.text;

import com.acrylic.universal.text.ActionBarSender;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.text.TitleSender;
import com.acrylic.version_1_8.packets.PacketSender;
import com.acrylic.version_1_8.packets.SinglePacketSender;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.jetbrains.annotations.NotNull;

public class ActionBar extends SinglePacketSender implements ActionBarSender {

    private String text;
    private PacketPlayOutChat packet;

    public ActionBar build() {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\"}");
        packet = new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
        return this;
    }

    @Override
    public ActionBar text(@NotNull String text) {
        this.text = ChatUtils.get(text);
        return this;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public PacketPlayOutChat getPacket() {
        return packet;
    }
}
