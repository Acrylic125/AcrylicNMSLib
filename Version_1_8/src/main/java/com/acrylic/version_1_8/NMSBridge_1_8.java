package com.acrylic.version_1_8;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.NMSBridgeUtils;
import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.loaders.EntityRegistry;

public final class NMSBridge_1_8 extends NMSBridge {

    private final EntityRegistry entityRegistry = new com.acrylic.version_1_8.EntityRegistry();
    private final NMSBridgeUtils nmsBridgeUtils = new NMSBridgeUtils_1_8();

    public NMSBridge_1_8() {
        super();
    }

    @Override
    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    @Override
    public EntityFactory getEntityFactory() {
        return null;
    }

    @Override
    public NMSBridgeUtils getUtils() {
        return nmsBridgeUtils;
    }
}
