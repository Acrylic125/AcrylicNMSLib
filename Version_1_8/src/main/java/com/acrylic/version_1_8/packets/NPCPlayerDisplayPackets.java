package com.acrylic.version_1_8.packets;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
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

    public void show(@NotNull EntityPlayer entity, @Nullable EntityEquipmentPackets equipmentPackets) {
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
    public void show(@NotNull LivingEntity entity, @Nullable EntityEquipmentPackets equipmentPackets) {
        Entity livingEntity = NMSBukkitConverter.convertToNMSEntity(entity);
        if (livingEntity instanceof EntityPlayer)
            show((EntityPlayer) livingEntity, equipmentPackets);
    }

    @Override
    public void show(@NotNull NMSLivingEntityAnimator nmsEntityAnimator) {
        if (nmsEntityAnimator instanceof com.acrylic.version_1_8.entityanimator.NMSLivingEntityAnimator) {
            EntityLiving entity = ((com.acrylic.version_1_8.entityanimator.NMSLivingEntityAnimator) nmsEntityAnimator).getNMSEntity();
            if (entity instanceof EntityPlayer)
                show((EntityPlayer) entity, nmsEntityAnimator.getEquipmentPackets());
        } else
            throw new IncompatibleVersion(nmsEntityAnimator.getClass(), getClass());
    }

    @Override
    public EntityHeadRotationPacket getHeadRotationPacket() {
        return headRotationPacket;
    }
}
