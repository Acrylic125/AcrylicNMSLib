package com.acrylic.universal.exceptions;

public class IncompatibleVersion extends RuntimeException {

    public IncompatibleVersion(String msg) {
        super(msg);
    }

    public IncompatibleVersion(Class<?> targetClazz, Class<?> mainClazz) {
        super(targetClazz.getName() + " cannot be used in " + mainClazz.getName() + ".");
    }
}
