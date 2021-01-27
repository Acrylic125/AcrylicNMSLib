package com.acrylic.version_1_8.packets;

import com.acrylic.universal.text.ChatUtils;
import io.netty.buffer.ByteBufAllocator;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class TablistHeaderFooterPacket
        extends SinglePacketSender
        implements com.acrylic.universal.packets.TablistHeaderFooterPacket {

    private PacketPlayOutPlayerListHeaderFooter packet;
    private String header;
    private String footer;

    @Override
    public boolean hasInitialized() {
        return packet != null;
    }

    @Nullable
    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public TablistHeaderFooterPacket removeHeader() {
        header = null;
        return this;
    }

    @Override
    public TablistHeaderFooterPacket setHeader(@NotNull String header) {
        this.header = ChatUtils.get(header);
        return this;
    }

    @Nullable
    @Override
    public String getFooter() {
        return footer;
    }

    @NotNull
    @Override
    public TablistHeaderFooterPacket removeFooter() {
        footer = null;
        return this;
    }

    @NotNull
    @Override
    public TablistHeaderFooterPacket setFooter(@NotNull String footer) {
        this.footer = ChatUtils.get(footer);
        return this;
    }

    @NotNull
    @Override
    public TablistHeaderFooterPacket build() {
        PacketDataSerializer data = new PacketDataSerializer(ByteBufAllocator.DEFAULT.buffer());
        packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            boolean h = header != null, f = footer != null;
            if (h || f) {
                data.a((h) ? ComponentSerializer.toString(new TextComponent(header)) : "\"\"");
                data.a((f) ? ComponentSerializer.toString(new TextComponent(footer)) : "\"\"");
                packet.a(data);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    @Override
    public PacketPlayOutPlayerListHeaderFooter getPacket() {
        return packet;
    }
}
