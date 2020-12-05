package com.acrylic.universal.packets;

import com.acrylic.universal.enums.EntityAnimationEnum;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * THESE ARE JUST THE BASE PACKETS! THEY DO NOT OFFER
 * ANY ADVANCE ANIMATIONS LIKE SITTING.
 *
 * @see EntityAnimationEnum
 */
public interface EntityAnimationPackets extends PacketSender {

    void apply(@NotNull Entity entity, @NotNull EntityAnimationEnum... animation);

    Object generateArmSwingPacket(@NotNull Entity entity);

    Object generateCritPacket(@NotNull Entity entity);

    Object generateMagicCritPacket(@NotNull Entity entity);

    Object generateHurtPacket(@NotNull Entity entity);

    Object generateSleepPacket(@NotNull Entity entity, @NotNull Location location);

    Object generateSleepPacket(@NotNull Entity entity);

    Object generateStopSleepPacket(@NotNull Entity entity);

    Object generateSneakPacket(@NotNull Entity entity);

    Object generateStopSneakingPacket(@NotNull Entity entity);

}
