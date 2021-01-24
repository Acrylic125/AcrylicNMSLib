package com.acrylic.version_1_8.packets;

import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.exceptions.IncompatibleEntityType;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EntityAnimationPackets
        extends PacketSender
        implements com.acrylic.universal.packets.EntityAnimationPackets {

    private Packet<?>[] packets;

    public void apply(@NotNull net.minecraft.server.v1_8_R3.Entity entity, @NotNull EntityAnimationEnum... animation) {
        packets = new Packet[animation.length];
        int i = 0;
        for (EntityAnimationEnum animationType : animation) {
            switch (animationType) {
                case CRIT:
                    packets[i] = generateCritPacket(entity);
                    break;
                case MAGIC_CRIT:
                    packets[i] = generateMagicCritPacket(entity);
                    break;
                case SLEEP:
                    packets[i] = generateSleepPacket(entity);
                    break;
                case ARM_SWING:
                    packets[i] = generateArmSwingPacket(entity);
                    break;
                case HURT:
                    packets[i] = generateHurtPacket(entity);
                    break;
                case STOP_SLEEPING:
                    packets[i] = generateStopSleepPacket(entity);
                    break;
                case SNEAK:
                    packets[i] = generateSneakPacket(entity);
                    break;
                case STOP_SNEAKING:
                    packets[i] = generateStopSneakingPacket(entity);
                    break;
            }
            i++;
        }
    }

    @Override
    public void apply(@NotNull Entity entity, @NotNull EntityAnimationEnum... animation) {
        apply(NMSBukkitConverter.convertToNMSEntity(entity), animation);
    }

    public PacketPlayOutAnimation generateArmSwingPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return new PacketPlayOutAnimation(entity, 0);
    }

    @Override
    public PacketPlayOutAnimation generateArmSwingPacket(@NotNull Entity entity) {
        return generateArmSwingPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public PacketPlayOutAnimation generateCritPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return new PacketPlayOutAnimation(entity, 4);
    }

    @Override
    public PacketPlayOutAnimation generateCritPacket(@NotNull Entity entity) {
        return generateCritPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public PacketPlayOutAnimation generateMagicCritPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return new PacketPlayOutAnimation(entity, 5);
    }

    @Override
    public PacketPlayOutAnimation generateMagicCritPacket(@NotNull Entity entity) {
        return generateMagicCritPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public PacketPlayOutAnimation generateHurtPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return new PacketPlayOutAnimation(entity, 1);
    }

    @Override
    public PacketPlayOutAnimation generateHurtPacket(@NotNull Entity entity) {
        return generateHurtPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public PacketPlayOutBed generateSleepPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity, @NotNull Location location) {
        if (!(entity instanceof EntityHuman))
            throw new IncompatibleEntityType(entity.getBukkitEntity().getType(), EntityType.PLAYER);
        return new PacketPlayOutBed((EntityHuman) entity, new BlockPosition(location.getX(), location.getY(), location.getZ()));
    }

    public PacketPlayOutBed generateSleepPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return generateSleepPacket(entity, entity.getBukkitEntity().getLocation());
    }

    @Override
    public PacketPlayOutBed generateSleepPacket(@NotNull Entity entity, @NotNull Location location) {
        return generateSleepPacket(NMSBukkitConverter.convertToNMSEntity(entity), location);
    }

    @Override
    public PacketPlayOutBed generateSleepPacket(@NotNull Entity entity) {
        return generateSleepPacket(entity, entity.getLocation());
    }

    public PacketPlayOutAnimation generateStopSleepPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return new PacketPlayOutAnimation(entity, 2);
    }

    @Override
    public PacketPlayOutAnimation generateStopSleepPacket(@NotNull Entity entity) {
         return generateStopSleepPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public PacketPlayOutEntityMetadata generateSneakPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity, boolean sneaking) {
        Entity bukkitEntity = entity.getBukkitEntity();
        if (!(bukkitEntity instanceof Player))
            throw new IncompatibleEntityType(entity.getBukkitEntity().getType(), EntityType.PLAYER);
        ((Player) bukkitEntity).setSneaking(sneaking);
        EntityMetaDataPacket entityMetaDataPacket = new EntityMetaDataPacket();
        entityMetaDataPacket.apply(entity);
        return entityMetaDataPacket.getPacket();
    }

    public PacketPlayOutEntityMetadata generateSneakPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return generateSneakPacket(entity, true);
    }

    @Override
    public PacketPlayOutEntityMetadata generateSneakPacket(@NotNull Entity entity) {
        return generateSneakPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    public PacketPlayOutEntityMetadata generateStopSneakingPacket(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        return generateSneakPacket(entity, false);
    }

    @Override
    public PacketPlayOutEntityMetadata generateStopSneakingPacket(@NotNull Entity entity) {
        return generateStopSneakingPacket(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    @Override
    public Packet<?>[] getPackets() {
        return packets;
    }

    @Override
    public boolean hasInitialized() {
        return packets != null;
    }
}
