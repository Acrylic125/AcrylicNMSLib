package com.acrylic.version_1_8.packets;

import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityDisplayPackets extends PacketSender implements com.acrylic.universal.packets.EntityDisplayPackets  {

    private Packet<?>[] packets;

    @Override
    public void show(@NotNull Entity entity, @Nullable EntityEquipmentPackets equipmentPackets) {

    }

    @Override
    public void show(@NotNull Entity entity) {

    }

    @Override
    public void show(@NotNull NMSEntityAnimator nmsEntityAnimator) {

    }

    @Override
    public Packet<?>[] getPackets() {
        return packets;
    }
}
