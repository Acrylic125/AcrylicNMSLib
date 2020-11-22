package com.acrylic.universal.nbt;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractNBTItem implements AbstractNBT {

    private final ItemStack originalItem;

    public AbstractNBTItem(@NotNull ItemStack item) {
        this.originalItem = item;
    }

    public ItemStack getOriginalItem() {
        return originalItem;
    }

    public abstract ItemStack getItem();

    @Override
    public String toString() {
        return "AbstractNBTItem{" +
                "originalItem=" + originalItem +
                "compound=" + getCompound() +
                '}';
    }
}
