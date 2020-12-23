package com.acrylic.version_1_8.packets;

import com.acrylic.universal.emtityanimator.instances.NMSLivingEntityAnimator;
import com.acrylic.universal.exceptions.IncompatibleVersion;
import com.acrylic.universal.npc.NPCDisplayPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NPCPlayerDisplayPackets extends LivingEntityDisplayPackets implements NPCDisplayPackets {

    private final EntityHeadRotationPacket headRotationPacket = new EntityHeadRotationPacket();

    public void setupDisplayPackets(@NotNull EntityPlayer entity, @Nullable EntityEquipmentPackets equipmentPackets) {
        PacketPlayOutPlayerInfo playerInfoAdd = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entity);
        PacketPlayOutNamedEntitySpawn namedEntitySpawn = new PacketPlayOutNamedEntitySpawn(entity);
        DataWatcher dataWatcher = entity.getDataWatcher();
        dataWatcher.watch(10, (byte) 127); //Displays the skin second layer.
        EntityMetaDataPacket entityMetaDataPacket = getEntityMetaDataPacket();
        entityMetaDataPacket.apply(entity, true);
        headRotationPacket.apply(entity);
        if (equipmentPackets instanceof com.acrylic.version_1_8.packets.EntityEquipmentPackets) {
            com.acrylic.version_1_8.packets.EntityEquipmentPackets entityEquipmentPackets = (com.acrylic.version_1_8.packets.EntityEquipmentPackets) equipmentPackets;
            entityEquipmentPackets.apply(entity);
            PacketPlayOutEntityEquipment[] equipment = entityEquipmentPackets.getPackets();
            packets = new Packet[equipment.length + 4];
            packets[0] = playerInfoAdd;
            packets[1] = namedEntitySpawn;
            packets[2] = entityMetaDataPacket.getPacket();
            packets[3] = headRotationPacket.getPacket();
            short i = 3;
            for (PacketPlayOutEntityEquipment packetPlayOutEntityEquipment : equipment) {
                i++;
                packets[i] = packetPlayOutEntityEquipment;
            }
        } else if (equipmentPackets == null) {
            packets = new Packet[] {
                    playerInfoAdd, namedEntitySpawn, entityMetaDataPacket.getPacket()
            };
        } else
            throw new IncompatibleVersion(equipmentPackets.getClass(), getClass());
    }

    @Override
    public void setupDisplayPackets(@NotNull LivingEntity entity, @Nullable EntityEquipmentPackets equipmentPackets) {
        Entity livingEntity = NMSBukkitConverter.convertToNMSEntity(entity);
        if (livingEntity instanceof EntityPlayer)
            setupDisplayPackets((EntityPlayer) livingEntity, equipmentPackets);
    }

    @Override
    public void setupDisplayPackets(@NotNull NMSLivingEntityAnimator nmsEntityAnimator) {
        EntityLiving entity = (EntityLiving) nmsEntityAnimator.getNMSEntity();
        if (entity instanceof EntityPlayer)
            setupDisplayPackets((EntityPlayer) entity, nmsEntityAnimator.getEntityInstance().getEquipmentPackets());
    }

    @Override
    public com.acrylic.universal.packets.EntityHeadRotationPacket getHeadRotationPacket() {
        return headRotationPacket;
    }

}
