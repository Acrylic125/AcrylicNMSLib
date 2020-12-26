package com.acrylic.universal.factory;

import com.acrylic.universal.packets.*;
import com.acrylic.universal.particles.ItemParticles;
import com.acrylic.universal.particles.Particles;
import com.acrylic.universal.particles.RGBParticles;
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

    @NotNull
    Particles getNewParticles();

    @NotNull
    ItemParticles getNewItemParticles();

    @NotNull
    RGBParticles getNewRGBParticles();

}
