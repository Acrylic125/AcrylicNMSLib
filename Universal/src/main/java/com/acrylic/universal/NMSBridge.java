package com.acrylic.universal;

import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.factory.PacketFactory;
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
    public abstract PacketFactory getPacketFactory();

    @NotNull
    public abstract BoundingBoxExaminer getNewBlockExaminer();

    @NotNull
    public BoundingBoxExaminer getNewBlockExaminer(@NotNull Location location) {
        BoundingBoxExaminer examiner = getNewBlockExaminer();
        examiner.examine(location);
        return examiner;
    }

    @Nullable
    public abstract AbstractNBTItem getNewNBTItem(@Nullable ItemStack item);

    @Nullable
    public abstract AbstractNBTEntity getNewNBTEntity(@Nullable Entity entity);

    @Nullable
    public abstract AbstractNBTTileEntity getNewNBTTileEntity(@Nullable Block block);

    @NotNull
    public abstract AbstractJSON getNewJSON();

    @NotNull
    public abstract AbstractJSONComponent getNewJSONComponent(@Nullable String string);

}
