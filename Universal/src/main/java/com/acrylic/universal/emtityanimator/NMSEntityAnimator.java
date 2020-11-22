package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.PacketSender;
import com.acrylic.universal.packets.TeleportPacket;
import com.acrylic.universal.renderer.PacketRenderer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface NMSEntityAnimator extends EntityAnimator {

    Object getNMSEntity();

    PacketRenderer getRenderer();

    void setRenderer(PacketRenderer packetRenderer);

    @NotNull
    TeleportPacket getTeleportPacket();

    @NotNull
    EntityDestroyPacket getDestroyPacket();

    @NotNull
    LivingEntityDisplayPackets getDisplayPackets();

    default void sendPacketsViaRenderer(PacketSender packetSender) {
        PacketRenderer packetRenderer = getRenderer();
        if (packetRenderer == null) packetSender.sendAll();
        else getRenderer().send(packetSender);
    }

    default void show() {
        LivingEntityDisplayPackets showPackets = getDisplayPackets();
        showPackets.show(this);
        sendPacketsViaRenderer(showPackets);
    }

    @Override
    default void teleport(@NotNull Location location) {
        TeleportPacket teleportPacket = getTeleportPacket();
        teleportPacket.teleport(getBukkitEntity(), location);
        sendPacketsViaRenderer(teleportPacket);
    }

    @Override
    default void delete() {
        EntityDestroyPacket destroyPacket = getDestroyPacket();
        destroyPacket.delete(getBukkitEntity());
        sendPacketsViaRenderer(destroyPacket);
    }
}
