package com.acrylic.universal;

public final class NMSUtils {

    public static byte getByteAngle(float angle) {
        return (byte) (int) (angle * 256.0F / 360.0F);
    }

    public static byte getByteAngle(double angle) {
        return getByteAngle((float) angle);
    }

}
