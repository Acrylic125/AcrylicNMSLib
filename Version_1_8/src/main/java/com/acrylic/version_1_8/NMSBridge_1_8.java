package com.acrylic.version_1_8;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.NMSBridgeUtils;
import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.json.AbstractJSON;
import com.acrylic.universal.json.AbstractJSONComponent;
import com.acrylic.universal.json.JSON;
import com.acrylic.universal.json.JSONComponent;
import com.acrylic.universal.loaders.EntityRegistry;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.universal.nbt.AbstractNBTEntity;
import com.acrylic.universal.nbt.AbstractNBTItem;
import com.acrylic.universal.nbt.AbstractNBTTileEntity;
import com.acrylic.version_1_8.nbt.NBTEntity;
import com.acrylic.version_1_8.nbt.NBTItem;
import com.acrylic.version_1_8.nbt.NBTTileEntity;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NMSBridge_1_8 extends NMSBridge {

    private final EntityRegistry entityRegistry = new com.acrylic.version_1_8.EntityRegistry();

    public NMSBridge_1_8() {
        super();
    }

    @Override
    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    @NotNull
    @Override
    public EntityFactory getEntityFactory() {
        return null;
    }

    @NotNull
    @Override
    public BoundingBoxExaminer getBlockExaminer() {
        return new com.acrylic.version_1_8.misc.BoundingBoxExaminer();
    }

    @Nullable
    @Override
    public AbstractNBTItem getNBTItem(@Nullable ItemStack item) {
        return (item == null) ? null : new NBTItem(item);
    }

    @Nullable
    @Override
    public AbstractNBTEntity getNBTEntity(@Nullable Entity entity) {
        return (entity == null) ? null : new NBTEntity(entity);
    }

    @Nullable
    @Override
    public AbstractNBTTileEntity getNBTTileEntity(@Nullable Block block) {
        return (block == null) ? null : new NBTTileEntity(block);
    }

    @NotNull
    @Override
    public AbstractJSON getJSON() {
        return new JSON();
    }

    @NotNull
    @Override
    public AbstractJSONComponent getJSONComponent(@Nullable String string) {
        return JSONComponent.of(string);
    }
}
