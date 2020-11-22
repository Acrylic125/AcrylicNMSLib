package com.acrylic.version_1_8.nbt;

import com.acrylic.universal.nbt.AbstractNBTEntity;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class NBTEntity extends AbstractNBTEntity {

    private final net.minecraft.server.v1_8_R3.Entity nmsEntity;
    private NBTCompound compound;

    public NBTEntity(@NotNull Entity entity, @NotNull NBTCompound compound) {
        super(entity);
        this.nmsEntity = NMSBukkitConverter.convertToNMSEntity(entity);
        this.compound = compound;
    }

    public NBTEntity(@NotNull Entity entity) {
        super(entity);
        this.nmsEntity = NMSBukkitConverter.convertToNMSEntity(entity);
        NBTTagCompound compound = new NBTTagCompound();
        nmsEntity.e(compound);
        this.compound = new NBTCompound(compound);
    }

    public void setCompound(@NotNull NBTCompound compound) {
        this.compound = compound;
    }

    @NotNull
    @Override
    public NBTCompound getCompound() {
        return compound;
    }

    @Override
    public void update() {
        if (nmsEntity instanceof EntityLiving) {
            ((EntityLiving) nmsEntity).a(getCompound().getTagCompound());
        }
    }

}
