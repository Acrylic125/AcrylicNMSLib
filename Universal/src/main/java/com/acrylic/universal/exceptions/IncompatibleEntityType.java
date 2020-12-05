package com.acrylic.universal.exceptions;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class IncompatibleEntityType extends RuntimeException {

    private final EntityType found;
    private final EntityType expected;

    public IncompatibleEntityType(@NotNull EntityType found, @NotNull EntityType expected) {
        this.found = found;
        this.expected = expected;
    }

    @Override
    public String toString() {
        return "IncompatibleEntityType: Expected " + expected + " but found " + found + " instead.";
    }
}
