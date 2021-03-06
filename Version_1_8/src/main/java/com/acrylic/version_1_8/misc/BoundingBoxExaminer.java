package com.acrylic.version_1_8.misc;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BoundingBoxExaminer
        implements com.acrylic.universal.misc.BoundingBoxExaminer {

    private AxisAlignedBB bb;

    @Override
    public double getMinX() {
        return bb.a;
    }

    @Override
    public double getMinY() {
        return bb.b;
    }

    @Override
    public double getMinZ() {
        return bb.c;
    }

    @Override
    public double getMaxX() {
        return bb.d;
    }

    @Override
    public double getMaxY() {
        return bb.e;
    }

    @Override
    public double getMaxZ() {
        return bb.f;
    }

    @Override
    public boolean examine(@NotNull Location location) {
        World world = NMSBukkitConverter.convertToNMSWorld(location.getWorld());
        net.minecraft.server.v1_8_R3.Block nmsBlock = NMSBukkitConverter.convertToNMSBlock(location);
        BlockPosition pos = NMSBukkitConverter.getBlockPosition(location);
        nmsBlock.updateShape(world, pos);
        bb = nmsBlock.a(world,
                pos,
                nmsBlock.getBlockData());
        return bb != null;
    }

    @Override
    public boolean examine(@NotNull Entity entity) {
        bb = NMSBukkitConverter.convertToNMSEntity(entity).getBoundingBox();
        return bb != null;
    }

    @Nullable
    @Override
    public Object getNMSBoundingBox() {
        return bb;
    }

    @Override
    public String toString() {
        return "BoundingBoxExaminer{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                " [min: " +
                "x=" + getMinX() +
                ", y=" + getMinY() +
                ", z=" + getMinZ() +
                "], [max: " +
                "x=" + getMaxX() +
                ", y=" + getMaxY() +
                ", z=" + getMaxZ() +
                "]}";
    }
}
