package com.acrylic.universal.nmsentityanimator;

import com.acrylic.universal.renderer.EntityRenderer;
import org.jetbrains.annotations.NotNull;

public interface EntityRenderable {

    @NotNull
    EntityRenderer getRenderer();

    void setRenderer(EntityRenderer packetRenderer);

}
