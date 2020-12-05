package com.acrylic.version_1_8.particles;

import com.acrylic.universal.particles.RGB;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class RGBParticles
        extends Particles
        implements com.acrylic.universal.particles.RGBParticles {

    private RGB rgb = new RGB(0, 0, 0);

    @Override
    public void build() {
        checkConditions();


        //
        //                (particletype.equals(EnumParticle.REDSTONE)) ? ((usingRGB) ? new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) this.location.getX(), (float) this.location.getY(), (float) this.location.getZ(), this.red, this.green, this.blue, 1,  0) :
        //                        new PacketPlayOutWorldParticles(EnumParticle.REDSTONE, true, (float) this.location.getX(), (float) this.location.getY(), (float) this.location.getZ(), this.offsetX, this.offsetY, this.offsetZ, 0,  this.amount, 0)) :
        //
        this.packet = new PacketPlayOutWorldParticles(particleType,
                        false, this.location[0], this.location[1], this.location[2],
                        this.rgb.getRed(), this.rgb.getGreen(), rgb.getBlue(),
                        1, 0
                );
    }

    @Override
    public RGB getRGB() {
        return rgb;
    }

    @Override
    public void rgb(RGB rgb) {
        this.rgb = rgb;
    }
}
