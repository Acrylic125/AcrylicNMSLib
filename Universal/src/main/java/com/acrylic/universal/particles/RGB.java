package com.acrylic.universal.particles;

public final class RGB {

    private float red = 0;
    private float green = 0;
    private float blue = 0;

    public RGB() {}

    public RGB(float red, float green, float blue) {
        set(red, green, blue);
    }

    public void set(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red / 255;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green / 255;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue / 255;
    }
}
