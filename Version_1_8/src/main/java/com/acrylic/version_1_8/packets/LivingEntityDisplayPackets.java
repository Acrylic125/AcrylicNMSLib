package com.acrylic.version_1_8.packets;

import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LivingEntityDisplayPackets extends PacketSender implements com.acrylic.universal.packets.LivingEntityDisplayPackets {

    private final EntityMetaDataPacket entityMetaDataPacket = new EntityMetaDataPacket();
    private Packet<?>[] packets;

    public void show(@NotNull EntityLiving entity, @Nullable EntityEquipmentPackets equipmentPackets) {
        entityMetaDataPacket.apply(entity);
        PacketPlayOutSpawnEntityLiving packetPlayOutSpawnEntityLiving = new PacketPlayOutSpawnEntityLiving(entity);
        if (equipmentPackets instanceof com.acrylic.version_1_8.packets.EntityEquipmentPackets) {
            com.acrylic.version_1_8.packets.EntityEquipmentPackets entityEquipmentPackets = (com.acrylic.version_1_8.packets.EntityEquipmentPackets) equipmentPackets;
            entityEquipmentPackets.apply(entity);
            PacketPlayOutEntityEquipment[] equipment = entityEquipmentPackets.getPackets();
            packets = new Packet[equipment.length + 2];
            packets[0] = entityMetaDataPacket.getPacket();
            packets[1] = packetPlayOutSpawnEntityLiving;
            short i = 1;
            for (PacketPlayOutEntityEquipment packetPlayOutEntityEquipment : equipment) {
                i++;
                packets[i] = packetPlayOutEntityEquipment;
            }
        } else {
            packets = new Packet[] {
                    entityMetaDataPacket.getPacket(),
                    packetPlayOutSpawnEntityLiving
            };
        }
    }

    @Override
    public void show(@NotNull LivingEntity entity, @Nullable EntityEquipmentPackets equipmentPackets) {
         show((EntityLiving) NMSBukkitConverter.convertToNMSEntity(entity), equipmentPackets);
    }

    @Override
    public void show(@NotNull NMSEntityAnimator nmsEntityAnimator) {
        show((EntityLiving) nmsEntityAnimator.getNMSEntity(), null);
    }

    @Override
    public Packet<?>[] getPackets() {
        return packets;
    }
}
