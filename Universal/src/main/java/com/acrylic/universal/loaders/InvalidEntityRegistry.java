package com.acrylic.universal.loaders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvalidEntityRegistry extends RuntimeException {

    private final Class<?> clazz;
    private final String reason;

    public InvalidEntityRegistry(@NotNull Class<?> clazz, @Nullable String reason) {
        this.clazz = clazz;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "InvalidEntityRegistry: The entity class, " + clazz.getName() + " cannot be loaded. " + ((reason != null) ? reason : "");
    }
}
