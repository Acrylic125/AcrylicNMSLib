package com.acrylic.version_1_8.factory;

import com.acrylic.universal.factory.PacketFactory;
import com.acrylic.universal.packets.*;
import com.acrylic.universal.particles.ItemParticles;
import com.acrylic.universal.particles.Particles;
import com.acrylic.universal.particles.RGBParticles;
import org.jetbrains.annotations.NotNull;

public class PacketFactory_1_8 implements PacketFactory {

    @NotNull
    @Override
    public BlockCrackPacket getNewBlockCrackPacket() {
        return new com.acrylic.version_1_8.packets.BlockCrackPacket();
    }

    @NotNull
    @Override
    public EntityAnimationPackets getNewEntityAnimationPackets() {
        return new com.acrylic.version_1_8.packets.EntityAnimationPackets();
    }

    @NotNull
    @Override
    public EntityDestroyPacket getNewEntityDestroyPacket() {
        return new com.acrylic.version_1_8.packets.EntityDestroyPacket();
    }

    @NotNull
    @Override
    public EntityEquipmentPackets getNewEquipmentPackets() {
        return new com.acrylic.version_1_8.packets.EntityEquipmentPackets();
    }

    @NotNull
    @Override
    public EntityHeadRotationPacket getNewEntityHeadRotationPacket() {
        return new com.acrylic.version_1_8.packets.EntityHeadRotationPacket();
    }

    @NotNull
    @Override
    public EntityMetaDataPacket getNewEntityMetadataPacket() {
        return new com.acrylic.version_1_8.packets.EntityMetaDataPacket();
    }

    @NotNull
    @Override
    public LivingEntityDisplayPackets getNewLivingEntityDisplayPackets() {
        return new com.acrylic.version_1_8.packets.LivingEntityDisplayPackets();
    }

    @NotNull
    @Override
    public TeleportPacket getNewTeleportPacket() {
        return new com.acrylic.version_1_8.packets.TeleportPacket();
    }

    @NotNull
    @Override
    public Particles getNewParticles() {
        return new com.acrylic.version_1_8.particles.Particles();
    }

    @NotNull
    @Override
    public ItemParticles getNewItemParticles() {
        return new com.acrylic.version_1_8.particles.ItemParticles();
    }

    @NotNull
    @Override
    public RGBParticles getNewRGBParticles() {
        return new com.acrylic.version_1_8.particles.RGBParticles();
    }

    @NotNull
    @Override
    public SoundPacket getSoundPacket() {
        return new com.acrylic.version_1_8.packets.SoundPacket();
    }
}
