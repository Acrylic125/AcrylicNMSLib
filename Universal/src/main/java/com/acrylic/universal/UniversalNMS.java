package com.acrylic.universal;

import com.acrylic.universal.loaders.EntityRegistry;
import com.acrylic.universal.npc.SimpleNPCHandler;
import com.acrylic.universal.npc.SimpleNPCSkinMap;
import org.jetbrains.annotations.NotNull;

public final class UniversalNMS {

    private static final SimpleNPCHandler npcHandler = new SimpleNPCHandler();
    private static EntityRegistry entityRegistry;

    public static SimpleNPCHandler getNpcHandler() {
        return npcHandler;
    }

    public static SimpleNPCSkinMap getSkinMap() {
        return npcHandler.getSkinMap();
    }

    public static void setEntityRegistry(@NotNull EntityRegistry versionLoader) {
        UniversalNMS.entityRegistry = versionLoader;
    }

    public static EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

}
