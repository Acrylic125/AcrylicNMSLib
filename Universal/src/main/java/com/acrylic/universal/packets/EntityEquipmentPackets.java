package com.acrylic.universal.packets;

import com.acrylic.universal.Universal;
import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityEquipmentPackets extends PacketSender {

    void apply(@NotNull Entity entity);

    EntityEquipmentPackets setHelmet(@Nullable ItemStack item);

    EntityEquipmentPackets setChestplate(@Nullable ItemStack item);

    EntityEquipmentPackets setLeggings(@Nullable ItemStack item);

    EntityEquipmentPackets setBoots(@Nullable ItemStack item);

    EntityEquipmentPackets setItemInHand(@Nullable ItemStack item);

    EntityEquipmentPackets setItemInOffhand(@Nullable ItemStack item);

    default void adapt(@NotNull AbstractEntityEquipmentBuilder abstractEntityEquipmentBuilder) {
        setHelmet(abstractEntityEquipmentBuilder.getHelmet())
                .setChestplate(abstractEntityEquipmentBuilder.getChestplate())
                .setLeggings(abstractEntityEquipmentBuilder.getLeggings())
                .setBoots(abstractEntityEquipmentBuilder.getBoots())
                .setItemInHand(abstractEntityEquipmentBuilder.getItemInHand());
        if (!Universal.getVersionStore().isLegacyVersion()) {
            setItemInOffhand(abstractEntityEquipmentBuilder.getItemInOffHand());
        }
    }


}
