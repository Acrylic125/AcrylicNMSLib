package com.acrylic.version_1_8.npc;

import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.SinglePacketSender;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerNPCInfoPacket
        extends SinglePacketSender
        implements com.acrylic.universal.npc.NPCPlayerInfoPacket {

    private PacketPlayOutPlayerInfo packet;

    @Override
    public PacketPlayOutPlayerInfo getPacket() {
        return packet;
    }

    public void apply(@NotNull EntityPlayer entityPlayer, @NotNull EnumPlayerInfoAction action) {
        packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);
    }

    @Override
    public void apply(@NotNull Player player, @NotNull EnumPlayerInfoAction action) {
        apply(NMSBukkitConverter.convertToNMSPlayer(player), action);
    }
}
