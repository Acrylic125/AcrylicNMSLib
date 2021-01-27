package com.acrylic.version_1_8.particles;

import com.acrylic.universal.particles.RGB;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.jetbrains.annotations.NotNull;

public class RGBParticles
        extends Particles
        implements com.acrylic.universal.particles.RGBParticles {

    private RGB rgb = new RGB(0, 0, 0);

    @NotNull
    @Override
    public Particles build() {
        checkConditions();
        this.packet = new PacketPlayOutWorldParticles(particleType,
                        false, this.location[0], this.location[1], this.location[2],
                        this.rgb.getRed(), this.rgb.getGreen(), rgb.getBlue(),
                        1, 0
                );
        return this;
    }

    @Override
    public RGB getRGB() {
        return rgb;
    }

    @Override
    public void rgb(RGB rgb) {
        this.rgb = rgb;
    }

    @Override
    public boolean hasInitialized() {
        return packet != null;
    }
}
