package com.acrylic.universal.util;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.items.ItemUtils;
import com.acrylic.universal.nbt.AbstractNBTItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ItemContainer {

    private final ItemStack item;
    private final AbstractNBTItem nbtItem;

    public ItemContainer(@Nullable ItemStack item) {
        this(item, (ItemUtils.isAir(item)) ? null : NMSBridge.getBridge().getNewNBTItem(item));
    }

    public ItemContainer(@Nullable ItemStack item, @Nullable AbstractNBTItem nbtItem) {
        this.item = item;
        this.nbtItem = nbtItem;
    }

    @Nullable
    public ItemStack getItem() {
        return item;
    }

    @Nullable
    public AbstractNBTItem getNbtItem() {
        return nbtItem;
    }
}
