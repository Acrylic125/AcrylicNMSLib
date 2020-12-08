package com.acrylic.universal.exceptions;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class IncompatibleEntityType extends RuntimeException {

    public IncompatibleEntityType(@NotNull EntityType found, @NotNull EntityType expected) {
        super("Expected " + expected + " but found " + found + " instead.");
    }

}
