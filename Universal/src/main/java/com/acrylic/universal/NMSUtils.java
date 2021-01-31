package com.acrylic.universal;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class NMSUtils {

    private static NMSUtils NMS_UTILS;

    public static NMSUtils getUtils() {
        return NMS_UTILS;
    }

    public NMSUtils() {
        NMS_UTILS = this;
    }

    public void iterateEntities(@NotNull Location location, double radius, @NotNull Consumer<Entity> action) {
        iterateEntities(location, radius, radius, radius, action, null);
    }

    public void iterateEntities(@NotNull Location location, double radius, @NotNull Consumer<Entity> action, @Nullable Predicate<Entity> filter) {
        iterateEntities(location, radius, radius, radius, action, filter);
    }

    public void iterateEntities(@NotNull Location location, double xz, double y, @NotNull Consumer<Entity> action) {
        iterateEntities(location, xz, y, xz, action, null);
    }

    public void iterateEntities(@NotNull Location location, double xz, double y, @NotNull Consumer<Entity> action, @Nullable Predicate<Entity> filter) {
        iterateEntities(location, xz, y, xz, action, filter);
    }

    public void iterateEntities(@NotNull Location location, double x, double y, double z, @NotNull Consumer<Entity> action) {
        iterateEntities(location, x, y, z, action, null);
    }

    public abstract void iterateEntities(@NotNull Location location, double x, double y, double z, @NotNull Consumer<Entity> action, @Nullable Predicate<Entity> filter);

    public static byte getByteAngle(float angle) {
        return (byte) (int) (angle * 256.0F / 360.0F);
    }

    public static byte getByteAngle(double angle) {
        return getByteAngle((float) angle);
    }

}
