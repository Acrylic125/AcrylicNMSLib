package com.acrylic.universal.nmsentityanimator;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public interface WorldEntity {

    void addToWorld(@NotNull World world);

    void removeFromWorld(@NotNull World world);

    void addToWorld();

}
