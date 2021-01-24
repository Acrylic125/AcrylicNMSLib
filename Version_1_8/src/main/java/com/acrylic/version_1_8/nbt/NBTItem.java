package com.acrylic.version_1_8.nbt;

import com.acrylic.universal.items.ItemUtils;
import com.acrylic.universal.nbt.AbstractNBTItem;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NBTItem extends AbstractNBTItem {

    private final net.minecraft.server.v1_8_R3.ItemStack nmsItem;
    private NBTCompound compound;

    public NBTItem(@NotNull ItemStack item, @NotNull NBTCompound compound) {
        super(item);
        this.compound = compound;
        this.nmsItem = (ItemUtils.isAir(item)) ? null : NMSBukkitConverter.convertToNMSItem(item);
    }

    public NBTItem(@NotNull ItemStack item) {
        super(item);
        if (ItemUtils.isAir(item)) {
            this.nmsItem = null;
            this.compound = new NBTCompound();
        } else {
            this.nmsItem = NMSBukkitConverter.convertToNMSItem(item);
            NBTTagCompound nbtTagCompound = nmsItem.getTag();
            if (nbtTagCompound == null) {
                nbtTagCompound = new NBTTagCompound();
                nmsItem.save(nbtTagCompound);
            }
            this.compound = new NBTCompound(nbtTagCompound);
        }
    }

    @Override
    public ItemStack getItem() {
        if (nmsItem == null)
            return getOriginalItem();
        nmsItem.setTag(compound.getTagCompound());
        return NMSBukkitConverter.convertToBukkitItem(nmsItem);
    }

    public void setCompound(NBTCompound compound) {
        this.compound = compound;
    }

    @NotNull
    @Override
    public NBTCompound getCompound() {
        return compound;
    }

}
