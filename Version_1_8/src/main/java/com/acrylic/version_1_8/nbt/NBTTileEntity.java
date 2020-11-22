package com.acrylic.version_1_8.nbt;

import com.acrylic.universal.nbt.AbstractNBTEntity;
import com.acrylic.universal.nbt.AbstractNBTTileEntity;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.TileEntity;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class NBTTileEntity extends AbstractNBTTileEntity {

    private final TileEntity tileEntity;
    private NBTCompound compound;

    public NBTTileEntity(@NotNull Block block, @NotNull NBTCompound compound) {
        super(block);
        this.tileEntity = NMSBukkitConverter.convertToNMSTileEntity(block);
        this.compound = compound;
    }

    public NBTTileEntity(@NotNull Block block) {
        super(block);
        this.tileEntity = NMSBukkitConverter.convertToNMSTileEntity(block);
        NBTTagCompound compound = new NBTTagCompound();
        tileEntity.b(compound);
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
        if (tileEntity == null)
            return;
        tileEntity.a(getCompound().getTagCompound());
        tileEntity.update();
    }

}
