package com.acrylic.universal.particles;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Particles extends PacketSender {

    void build();

    float[] offset();

    Object getParticleType();

    boolean isLongDistance();

    float getSpeed();

    int getAmount();

    float[] getLocation();

    Particles speed(float speed);

    Particles amount(int amount);

    Particles longDistance(boolean longDistance);

    Particles location(@NotNull Location location);

    Particles offset(float x, float y, float z);

    default void checkConditions() {
        if (getLocation() == null || getParticleType() == null)
            throw new IllegalArgumentException("Particles must have a location and particle effect defined.");
    }

}
