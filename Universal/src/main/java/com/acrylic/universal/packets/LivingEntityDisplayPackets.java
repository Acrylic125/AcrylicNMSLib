package com.acrylic.universal.packets;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LivingEntityDisplayPackets extends PacketSender {

    @NotNull
    EntityMetaDataPacket getEntityMetaDataPacket();

    void setupDisplayPackets(@NotNull LivingEntity entity, @Nullable EntityEquipmentPackets equipmentPackets);

    default void setupDisplayPackets(@NotNull LivingEntity entity) {
        setupDisplayPackets(entity, null);
    }

    void setupDisplayPackets(@NotNull NMSLivingEntityAnimator nmsEntityAnimator);

}
