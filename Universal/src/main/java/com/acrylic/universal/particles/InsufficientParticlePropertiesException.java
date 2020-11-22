package com.acrylic.universal.particles;

public class InsufficientParticlePropertiesException extends RuntimeException {

    @Override
    public String toString() {
        return "InsufficientParticlePropertiesException: Particles must have a location and particle effect defined.";
    }
}
