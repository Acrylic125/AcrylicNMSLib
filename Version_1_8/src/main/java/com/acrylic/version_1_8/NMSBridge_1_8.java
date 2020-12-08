package com.acrylic.version_1_8;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.loaders.EntityRegistry;

public final class NMSBridge_1_8 extends NMSBridge {

    private final EntityRegistry entityRegistry;

    public NMSBridge_1_8() {
        this.entityRegistry = new com.acrylic.version_1_8.EntityRegistry();
    }

    @Override
    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }
}
