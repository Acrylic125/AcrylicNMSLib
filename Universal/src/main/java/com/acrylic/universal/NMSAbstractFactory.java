package com.acrylic.universal;

import com.acrylic.universal.factory.AnalyzerFactory;
import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.factory.PacketFactory;
import com.acrylic.universal.json.AbstractJSON;
import com.acrylic.universal.json.AbstractJSONComponent;
import com.acrylic.universal.loaders.EntityRegistry;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.universal.nbt.AbstractNBTEntity;
import com.acrylic.universal.nbt.AbstractNBTItem;
import com.acrylic.universal.nbt.AbstractNBTTileEntity;
import com.acrylic.universal.text.ActionBarSender;
import com.acrylic.universal.text.TitleSender;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NMSAbstractFactory {

    private static NMSAbstractFactory ABSTRACT_FACTORY;

    @Deprecated
    public static NMSAbstractFactory getBridge() {
        return ABSTRACT_FACTORY;
    }

    public static NMSAbstractFactory getAbstractFactory() {
        return ABSTRACT_FACTORY;
    }

    //Instantiate bridge.
    public NMSAbstractFactory() {
        ABSTRACT_FACTORY = this;
    }

    @NotNull
    public abstract EntityRegistry getEntityRegistry();

    @NotNull
    public abstract EntityFactory getEntityFactory();

    @NotNull
    public abstract PacketFactory getPacketFactory();

    @NotNull
    public abstract AnalyzerFactory getAnalyzerFactory();

    @NotNull
    @Deprecated
    public abstract BoundingBoxExaminer getNewBlockExaminer();

    @NotNull
    @Deprecated
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

    @NotNull
    public abstract TitleSender getNewTitle();

    @NotNull
    public abstract ActionBarSender getNewActionBar();

}
