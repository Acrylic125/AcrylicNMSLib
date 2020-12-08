package com.acrylic.universal.loaders;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvalidEntityRegistry extends RuntimeException {

    public InvalidEntityRegistry(@NotNull Class<?> clazz, @Nullable String reason) {
        super("The entity class, " + clazz.getName() + " cannot be loaded. " + ((reason != null) ? reason : ""));
    }


}
