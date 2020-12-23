package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityinstances.EntityInstance;
import com.acrylic.universal.entityinstances.WorldEntity;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.jetbrains.annotations.NotNull;

public interface NMSEntity extends WorldEntity {

    Object getNMSEntity();

    EntityInstance getEntityInstance();

    @NotNull
    InitializerLocationalRenderer getRenderer();

    void setRenderer(InitializerLocationalRenderer packetRenderer);

}
