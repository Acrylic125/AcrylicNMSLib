package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.PacketSender;
import com.acrylic.universal.packets.TeleportPacket;
import com.acrylic.universal.renderer.EntityRenderer;
import com.acrylic.universal.renderer.InitializerPacketRenderer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface NMSEntityAnimator extends EntityAnimator {

    Object getNMSEntity();

    EntityInstance getEntityInstance();

    @Override
    default void setVelocity(@NotNull Vector velocity) {
        setVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
    }

    @Override
    void setVelocity(double x, double y, double z);

    @NotNull
    EntityRenderer getRenderer();

    void setRenderer(EntityRenderer packetRenderer);

    void addToWorld(@NotNull World world);

    void removeFromWorld(@NotNull World world);

    default void addToWorld() {
        addToWorld(getBukkitEntity().getWorld());
    }

    @NotNull
    TeleportPacket getTeleportPacket();

    @NotNull
    EntityDestroyPacket getDestroyPacket();

    @NotNull
    LivingEntityDisplayPackets getDisplayPackets();

    default Location getLocation() {
        return getBukkitEntity().getLocation();
    }

    default void sendPacketsViaRenderer(@NotNull PacketSender packetSender) {
        getRenderer().send(packetSender);
    }

    default void sendPacketsViaRendererWithAction(@NotNull PacketSender packetSender, @NotNull Consumer<Player> sendWithAction) {
        getRenderer().sendWithAction(packetSender, sendWithAction);
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
        sendPacketsViaRenderer(destroyPacket);
        removeFromWorld(getBukkitEntity().getWorld());
    }

}
