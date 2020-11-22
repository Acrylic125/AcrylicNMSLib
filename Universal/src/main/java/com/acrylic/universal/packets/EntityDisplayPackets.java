package com.acrylic.universal.packets;

import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityDisplayPackets extends PacketSender {

    void show(@NotNull Entity entity, @Nullable EntityEquipmentPackets equipmentPackets);

    void show(@NotNull Entity entity);

    void show(@NotNull NMSEntityAnimator nmsEntityAnimator);

}
