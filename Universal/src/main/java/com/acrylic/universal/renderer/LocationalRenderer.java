package com.acrylic.universal.renderer;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface LocationalRenderer {

    void setLocation(@NotNull Location location);

    Location getLocation();

}
