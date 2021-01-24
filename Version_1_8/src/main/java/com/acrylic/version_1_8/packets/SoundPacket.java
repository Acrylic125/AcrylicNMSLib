package com.acrylic.version_1_8.packets;

import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class SoundPacket
        extends SinglePacketSender
        implements com.acrylic.universal.packets.SoundPacket {

    private PacketPlayOutNamedSoundEffect packet;

    @Override
    public void apply(@NotNull String soundString, @NotNull Location location, float volume, float pitch) {
        packet = new PacketPlayOutNamedSoundEffect(soundString, location.getX(), location.getY(), location.getZ(), volume, pitch);
    }

    @Override
    public PacketPlayOutNamedSoundEffect getPacket() {
        return packet;
    }

    @Override
    public boolean hasInitialized() {
        return packet != null;
    }
}
