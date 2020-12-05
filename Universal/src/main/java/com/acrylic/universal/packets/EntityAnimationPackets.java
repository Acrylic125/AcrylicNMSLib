package com.acrylic.universal.packets;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityAnimationPackets extends PacketSender {

    void apply(@NotNull Entity entity, @NotNull Animation... animation);

    Object generateArmSwingPacket(@NotNull Entity entity);

    Object generateCritPacket(@NotNull Entity entity);

    Object generateMagicCritPacket(@NotNull Entity entity);

    Object generateEatFoodPacket(@NotNull Entity entity);

    Object generateHurtPacket(@NotNull Entity entity);

    Object generateSleepPacket(@NotNull Entity entity, @NotNull Location location);

    Object generateSleepPacket(@NotNull Entity entity);

    Object generateStopSleepPacket(@NotNull Entity entity);

    Object generateSneakPacket(@NotNull Entity entity);

    Object generateStopSneakingPacket(@NotNull Entity entity);

    Object generateStartUseMainHandItemPacket(@NotNull Entity entity);

    Object generateStopUseMainHandItemPacket(@NotNull Entity entity);

    enum Animation {
        ARM_SWING,
        //ARM_SWING_OFFHAND,
        CRIT,
        EAT_FOOD,
        HURT,
        MAGIC_CRIT,
        SIT,
        SLEEP,
        SNEAK,
        //START_ELYTRA,
        START_USE_MAINHAND_ITEM,
        //START_USE_OFFHAND_ITEM,
        STOP_SITTING,
        STOP_SLEEPING,
        STOP_SNEAKING,
        STOP_USE_ITEM;
    }

}
