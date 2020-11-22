package com.acrylic.universal.exceptions;

public class IncompatibleVersion extends RuntimeException {

    private final Class<?> targetClazz;
    private final Class<?> mainClazz;

    public IncompatibleVersion(Class<?> targetClazz, Class<?> mainClazz) {
        this.targetClazz = targetClazz;
        this.mainClazz = mainClazz;
    }

    @Override
    public String toString() {
        return "IncompatibleVersion: " + targetClazz.getName() + " cannot be used in " + mainClazz.getName() + ".";
    }
}
