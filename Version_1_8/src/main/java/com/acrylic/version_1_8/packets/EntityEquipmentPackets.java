package com.acrylic.version_1_8.packets;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EntityEquipmentPackets extends PacketSender implements com.acrylic.universal.packets.EntityEquipmentPackets {

    private PacketPlayOutEntityEquipment[] packets;
    private final HashMap<Integer, net.minecraft.server.v1_8_R3.ItemStack> itemsMap = new HashMap<>();

    private void addNewEntry(int i, @Nullable ItemStack item) {
        if (item == null)
            itemsMap.remove(i);
        else
            itemsMap.put(i, NMSBukkitConverter.convertToNMSItem(item));
    }

    public void apply(@NotNull net.minecraft.server.v1_8_R3.Entity entity) {
        final int entityId = entity.getId();
        packets = new PacketPlayOutEntityEquipment[itemsMap.size()];
        AtomicInteger index = new AtomicInteger(0);
        itemsMap.forEach((integer, itemStack) -> {
            packets[index.addAndGet(1) - 1] = new PacketPlayOutEntityEquipment(entityId, integer, itemStack);
        });
    }

    @Override
    public void apply(@NotNull Entity entity) {
        apply(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    @Override
    public EntityEquipmentPackets setHelmet(@Nullable ItemStack item) {
        addNewEntry(4, item);
        return this;
    }

    @Override
    public EntityEquipmentPackets setChestplate(@Nullable ItemStack item) {
        addNewEntry(3, item);
        return this;
    }

    @Override
    public EntityEquipmentPackets setLeggings(@Nullable ItemStack item) {
        addNewEntry(2, item);
        return this;
    }

    @Override
    public EntityEquipmentPackets setBoots(@Nullable ItemStack item) {
        addNewEntry(1, item);
        return this;
    }

    @Override
    public EntityEquipmentPackets setItemInHand(@Nullable ItemStack item) {
        addNewEntry(0, item);
        return this;
    }

    @Override
    public EntityEquipmentPackets setItemInOffhand(@Nullable ItemStack item) {
        return this;
    }

    @Override
    public PacketPlayOutEntityEquipment[] getPackets() {
        return packets;
    }
}
