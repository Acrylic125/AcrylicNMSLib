package com.acrylic.universal.particles;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface RGBParticles extends Particles {

    RGB getRGB();

    void rgb(RGB rgb);

    default void rgb(float red, float green, float blue) {
        RGB rgb = getRGB();
        if (rgb == null)
            rgb = new RGB(red, green, blue);
        else
            rgb.set(red, green, blue);
        rgb(rgb);
    }

    default void rainbow() {
        RGB rgb = getRGB();
        if (rgb == null)
            rgb = new RGB();
        rgb.setRaw(255, 255, 255);
    }

}
