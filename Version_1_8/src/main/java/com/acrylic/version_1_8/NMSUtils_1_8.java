package com.acrylic.version_1_8;

import com.acrylic.universal.NMSUtils;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NMSUtils_1_8 extends NMSUtils {

    @Override
    public void iterateEntities(@NotNull Location location, double x, double y, double z, @NotNull Consumer<Entity> action, @Nullable Predicate<Entity> filter) {
        CraftWorld craftWorld = NMSBukkitConverter.convertToCraftWorld(location.getWorld());
        AxisAlignedBB bb = new AxisAlignedBB(location.getX() - x, location.getY() - y, location.getZ() - z, location.getX() + x, location.getY() + y, location.getZ() + z);
        List<net.minecraft.server.v1_8_R3.Entity> entityList = craftWorld.getHandle().a((net.minecraft.server.v1_8_R3.Entity) null, bb, null);
        if (filter == null) {
            for (net.minecraft.server.v1_8_R3.Entity entity : entityList)
                action.accept(entity.getBukkitEntity());
        } else {
            for (net.minecraft.server.v1_8_R3.Entity entity : entityList) {
                Entity bEntity = entity.getBukkitEntity();
                if (filter.test(bEntity))
                    action.accept(bEntity);
            }
        }
    }
}
