package com.acrylic.universal.factory;

import com.acrylic.universal.packets.*;
import org.jetbrains.annotations.NotNull;

public interface PacketFactory {

    @NotNull
    BlockCrackPacket getNewBlockCrackpacket();

    @NotNull
    EntityAnimationPackets getNewEntityAnimationPackets();

    @NotNull
    EntityDestroyPacket getNewEntityDestroyPacket();

    @NotNull
    EntityEquipmentPackets getNewEquipmentPackets();

    @NotNull
    EntityHeadRotationPacket getNewEntityHeadRotationPacket();

    @NotNull
    EntityMetaDataPacket getNewEntityMetadataPacket();

    @NotNull
    LivingEntityDisplayPackets getNewLivingEntityDisplayPackets();

    @NotNull
    TeleportPacket getNewTeleportPacket();

}
