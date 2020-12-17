package com.acrylic.universal;

import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.loaders.EntityRegistry;

public abstract class NMSBridge {

    private static NMSBridge BRIDGE;

    public static NMSBridge getBridge() {
        return BRIDGE;
    }

    //Instantiate bridge.
    public NMSBridge() {
        BRIDGE = this;
    }

    public abstract EntityRegistry getEntityRegistry();

    public abstract EntityFactory getEntityFactory();

    public abstract NMSBridgeUtils getUtils();

}
