package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityinstances.EntityInstance;
import com.acrylic.universal.text.ChatUtils;
import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface EntityInstance_1_8 extends EntityInstance {

    @Override
    default void initialize(@NotNull Location location) {
        setupShowPackets();
        setupTermination();
        getNMSEntity().setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    Entity getNMSEntity();

    @Override
    default float getYaw() {
        return getNMSEntity().yaw;
    }

    @Override
    default void setYaw(float yaw) {
        getNMSEntity().yaw = yaw;
    }

    @Override
    default float getPitch() {
        return getNMSEntity().pitch;
    }

    @Override
    default void setPitch(float pitch) {
        getNMSEntity().pitch = pitch;
    }

    @Override
    default void setVelocity(double x, double y, double z) {
        getNMSEntity().move(x, y, z);
    }

    @Override
    default void setMCAI(boolean ai) {
        getNMSEntity().ai = ai;
    }

    @Override
    default boolean isUsingMCAI() {
        return getNMSEntity().ai;
    }

    @Override
    default void setNameVisible(boolean b) {
        getNMSEntity().setCustomNameVisible(b);
    }

    @Override
    default void setName(@NotNull String name) {
        getNMSEntity().setCustomName(ChatUtils.get(name));
    }

    @Override
    default void setVisible(boolean b) {
        getNMSEntity().setInvisible(!b);
    }

    default void removeFromWorld() {
        Entity entity = getNMSEntity();
        entity.getWorld().removeEntity(entity);
    }

    default void addToWorld() {
        Entity entity = getNMSEntity();
        entity.getWorld().addEntity(entity);
    }
}
