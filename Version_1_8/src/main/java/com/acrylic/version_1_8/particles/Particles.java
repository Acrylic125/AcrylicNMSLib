package com.acrylic.version_1_8.particles;

import com.acrylic.version_1_8.packets.SinglePacketSender;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Particles
        extends SinglePacketSender
        implements com.acrylic.universal.particles.Particles {

    protected EnumParticle particleType;
    protected boolean longDistance = true;
    protected float speed = 0;
    protected int amount = 1;
    protected float[] location;
    protected float[] offset;
    protected PacketPlayOutWorldParticles packet;

    @NotNull
    @Override
    public Particles build() {
        checkConditions();
        this.packet = (offset == null) ?
                new PacketPlayOutWorldParticles(particleType,
                        this.longDistance, this.location[0], this.location[1], this.location[2],
                        0, 0, 0,
                        this.speed, this.amount
                )
                :
                new PacketPlayOutWorldParticles(particleType,
                        this.longDistance, this.location[0], this.location[1], this.location[2],
                        offset[0], offset[1], offset[2],
                        this.speed, this.amount
                );
        return this;
    }

    @Override
    public float[] offset() {
        return new float[0];
    }

    @Override
    public EnumParticle getParticleType() {
        return particleType;
    }

    @Override
    public boolean isLongDistance() {
        return longDistance;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public float[] getLocation() {
        return location;
    }

    @NotNull
    @Override
    public Particles particleType(@NotNull EnumWrappers.Particle particle) {
        return particleType(EnumParticle.a(particle.getId()));
    }

    public Particles particleType(EnumParticle particleType) {
        this.particleType = particleType;
        return this;
    }

    @NotNull
    @Override
    public Particles speed(float speed) {
        this.speed = speed;
        return this;
    }

    @NotNull
    @Override
    public Particles amount(int amount) {
        this.amount = amount;
        return this;
    }

    @NotNull
    @Override
    public Particles longDistance(boolean longDistance) {
        this.longDistance = longDistance;
        return this;
    }

    @NotNull
    @Override
    public Particles location(@NotNull Location location) {
        this.location = new float[]{(float) location.getX(), (float) location.getY(), (float) location.getZ()};
        return this;
    }

    @NotNull
    @Override
    public Particles offset(float x, float y, float z) {
        this.offset = new float[]{x, y, z};
        return this;
    }

    @Override
    public boolean hasInitialized() {
        return packet != null;
    }

    @Override
    public Packet<?> getPacket() {
        return packet;
    }
}
