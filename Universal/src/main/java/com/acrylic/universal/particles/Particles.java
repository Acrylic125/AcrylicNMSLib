package com.acrylic.universal.particles;

import com.acrylic.universal.packets.PacketSender;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Particles extends PacketSender {

    @NotNull
    Particles build();

    float[] offset();

    Object getParticleType();

    boolean isLongDistance();

    float getSpeed();

    int getAmount();

    float[] getLocation();

    @NotNull
    Particles particleType(@NotNull EnumWrappers.Particle particle);

    @NotNull
    Particles speed(float speed);

    @NotNull
    Particles amount(int amount);

    @NotNull
    Particles longDistance(boolean longDistance);

    @NotNull
    Particles location(@NotNull Location location);

    @NotNull
    Particles offset(float x, float y, float z);

    default void checkConditions() {
        if (getLocation() == null || getParticleType() == null)
            throw new IllegalArgumentException("Particles must have a location and particle effect defined.");
    }

}
