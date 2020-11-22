package com.acrylic.version_1_8.text;

import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.text.TitleSender;
import com.acrylic.version_1_8.packets.PacketSender;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.jetbrains.annotations.NotNull;

public class Title extends PacketSender implements TitleSender {

    private String text;
    private String subTitle;
    private int duration = 20;
    private int fadeIn = 5;
    private int fadeOut = 5;
    private PacketPlayOutTitle[] packets;

    public Title build() {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + text + "\"}");
        IChatBaseComponent iChatBaseComponent2 = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
        packets = new PacketPlayOutTitle[] {
                new PacketPlayOutTitle(fadeIn, duration, fadeOut),
                new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, iChatBaseComponent),
                new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, iChatBaseComponent2)
        };
        return this;
    }

    @Override
    public Title duration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public Title text(@NotNull String text) {
        this.text = ChatUtils.get(text);
        return this;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Title subTitle(@NotNull String text) {
        this.subTitle = text;
        return this;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public Title fadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    @Override
    public int getFadeIn() {
        return fadeIn;
    }

    @Override
    public Title fadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    @Override
    public int getFadeOut() {
        return fadeOut;
    }

    @Override
    public Packet<?>[] getPackets() {
        return packets;
    }
}
