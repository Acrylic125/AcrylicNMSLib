package com.acrylic.version_1_8.packets;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.exceptions.IncompatibleVersion;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LivingEntityDisplayPackets
        extends PacketSender
        implements com.acrylic.universal.packets.LivingEntityDisplayPackets {

    private final EntityMetaDataPacket entityMetaDataPacket = new EntityMetaDataPacket();
    protected Packet<?>[] packets;

    public void setupDisplayPackets(@NotNull EntityLiving entity, @Nullable EntityEquipmentPackets equipmentPackets) {
        PacketPlayOutSpawnEntityLiving packetPlayOutSpawnEntityLiving = new PacketPlayOutSpawnEntityLiving(entity);
        entityMetaDataPacket.apply(entity);
        if (equipmentPackets instanceof com.acrylic.version_1_8.packets.EntityEquipmentPackets) {
            com.acrylic.version_1_8.packets.EntityEquipmentPackets entityEquipmentPackets = (com.acrylic.version_1_8.packets.EntityEquipmentPackets) equipmentPackets;
            entityEquipmentPackets.apply(entity);
            PacketPlayOutEntityEquipment[] equipment = entityEquipmentPackets.getPackets();
            packets = new Packet[equipment.length + 2];
            packets[0] = packetPlayOutSpawnEntityLiving;
            packets[1] = entityMetaDataPacket.getPacket();
            short i = 1;
            for (PacketPlayOutEntityEquipment packetPlayOutEntityEquipment : equipment) {
                i++;
                packets[i] = packetPlayOutEntityEquipment;
            }
        } else if (equipmentPackets == null) {
            packets = new Packet[] {
                    packetPlayOutSpawnEntityLiving,
                    entityMetaDataPacket.getPacket(),
            };
        } else
            throw new IncompatibleVersion(equipmentPackets.getClass(), getClass());
    }

    @NotNull
    @Override
    public EntityMetaDataPacket getEntityMetaDataPacket() {
        return entityMetaDataPacket;
    }

    @Override
    public void setupDisplayPackets(@NotNull LivingEntity entity, @Nullable EntityEquipmentPackets equipmentPackets) {
        setupDisplayPackets(NMSBukkitConverter.convertToNMSEntity(entity), equipmentPackets);
    }

    @Override
    public void setupDisplayPackets(@NotNull NMSLivingEntityAnimator nmsEntityAnimator) {
        if (nmsEntityAnimator instanceof com.acrylic.version_1_8.entityanimator.NMSLivingEntityAnimator) {
            EntityLiving entity = ((com.acrylic.version_1_8.entityanimator.NMSLivingEntityAnimator) nmsEntityAnimator).getNMSEntity();
            setupDisplayPackets(entity, nmsEntityAnimator.getEntityInstance().getEquipmentPackets());
        } else
            throw new IncompatibleVersion(nmsEntityAnimator.getClass(), getClass());
    }

    @Override
    public Packet<?>[] getPackets() {
        return packets;
    }
}
