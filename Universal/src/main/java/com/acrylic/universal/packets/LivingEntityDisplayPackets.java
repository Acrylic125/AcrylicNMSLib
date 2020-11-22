package com.acrylic.universal.packets;

import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LivingEntityDisplayPackets extends PacketSender {

    void show(@NotNull LivingEntity entity, @Nullable EntityEquipmentPackets equipmentPackets);

    default void show(@NotNull LivingEntity entity) {
        show(entity, null);
    }

    void show(@NotNull NMSEntityAnimator nmsEntityAnimator);

}
