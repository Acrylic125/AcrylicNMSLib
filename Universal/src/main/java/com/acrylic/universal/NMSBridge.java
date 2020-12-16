package com.acrylic.universal;

import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.loaders.EntityRegistry;
import lombok.Getter;

@Getter
public abstract class NMSBridge {

    public abstract EntityRegistry getEntityRegistry();

    public abstract EntityFactory getEntityFactory();

}
