package com.acrylic.universal;

import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.json.AbstractJSON;
import com.acrylic.universal.json.AbstractJSONComponent;
import com.acrylic.universal.loaders.EntityRegistry;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.universal.nbt.AbstractNBTEntity;
import com.acrylic.universal.nbt.AbstractNBTItem;
import com.acrylic.universal.nbt.AbstractNBTTileEntity;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NMSBridge {

    private static NMSBridge BRIDGE;

    public static NMSBridge getBridge() {
        return BRIDGE;
    }

    //Instantiate bridge.
    public NMSBridge() {
        BRIDGE = this;
    }

    @NotNull
    public abstract EntityRegistry getEntityRegistry();

    @NotNull
    public abstract EntityFactory getEntityFactory();

    @NotNull
    public abstract BoundingBoxExaminer getBlockExaminer();

    @NotNull
    public BoundingBoxExaminer getBlockExaminer(@NotNull Location location) {
        BoundingBoxExaminer examiner = getBlockExaminer();
        examiner.examine(location);
        return examiner;
    }

    @Nullable
    public abstract AbstractNBTItem getNBTItem(@Nullable ItemStack item);

    @Nullable
    public abstract AbstractNBTEntity getNBTEntity(@Nullable Entity entity);

    @Nullable
    public abstract AbstractNBTTileEntity getNBTTileEntity(@Nullable Block block);

    @NotNull
    public abstract AbstractJSON getJSON();

    @NotNull
    public abstract AbstractJSONComponent getJSONComponent(@Nullable String string);


}
