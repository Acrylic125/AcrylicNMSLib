package com.acrylic.universal.npc;

import org.jetbrains.annotations.NotNull;

public class SimpleNPCSkin implements NPCSkin {

    private String texture;
    private String signature;

    public SimpleNPCSkin(String name) {
        queryFromName(name);
    }

    public SimpleNPCSkin(String texture, String signature) {
        this.texture = texture;
        this.signature = signature;
    }

    @Override
    public void setTexture(@NotNull String texture) {
        this.texture = texture;
    }

    @NotNull
    @Override
    public String getTexture() {
        return texture;
    }

    @Override
    public void setSignature(@NotNull String signature) {
        this.signature = signature;
    }

    @NotNull
    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "SimpleNPCSkin{" +
                "texture='" + texture + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
