package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.exceptions.NoRendererException;
import com.acrylic.universal.packets.EntityAnimationPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.TeleportPacket;
import com.acrylic.universal.renderer.InitializerPacketRenderer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface NMSLivingEntityAnimator extends LivingEntityAnimator, NMSEntityAnimator {

    int getMaxDamageCooldown();

    void setMaxDamageCooldown(int ticks);

    boolean isNoClip();

    void setNoClip(boolean b);

    void setYaw(float yaw);

    void setPitch(float pitch);

    default void setYawAndPitch(float yaw, float pitch) {
        setYaw(yaw);
        setPitch(pitch);
    }

    @Override
    LivingEntityInstance getEntityInstance();

    EntityAnimationPackets getAnimationPackets();

    EntityEquipmentPackets getEquipmentPackets();

    default void animate(EntityAnimationEnum... animation) {
        EntityAnimationPackets entityAnimationPackets = getAnimationPackets();
        entityAnimationPackets.apply(getBukkitEntity(), animation);
        sendPacketsViaRenderer(entityAnimationPackets);
    }

    default void setupShowPackets() throws NoRendererException {
        setupShowPackets(null);
    }

    default void setupShowPackets(@Nullable Consumer<Player> sendWithAction) throws NoRendererException {
        LivingEntityDisplayPackets showPackets = getDisplayPackets();
        showPackets.setupDisplayPackets(this);
        InitializerPacketRenderer renderer = getRenderer();
        if (sendWithAction != null)
            renderer.setInitializationAction(
                    player -> showPackets.sendWithAction(player, receiver -> {
                        getFixedPositionAction().accept(receiver);
                        sendWithAction.accept(receiver);
                    }));
        renderer.setTerminationAction(player -> getDestroyPacket().send(player));
    }

    @NotNull
    default Consumer<Player> getFixedPositionAction() {
        return player -> {
            TeleportPacket teleportPacket = getTeleportPacket();
            teleportPacket.teleport(getBukkitEntity());
            teleportPacket.send(player);
        };
    }

}
