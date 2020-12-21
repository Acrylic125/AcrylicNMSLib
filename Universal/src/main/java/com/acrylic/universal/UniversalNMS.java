package com.acrylic.universal;

import com.acrylic.universal.emtityanimator.GlobalNMSEntityMap;
import com.acrylic.universal.npc.SimpleNPCHandler;
import com.acrylic.universal.npc.SimpleNPCSkinMap;

public final class UniversalNMS {

    private static final SimpleNPCHandler npcHandler = new SimpleNPCHandler();
    private static final GlobalNMSEntityMap globalEntityMap = new GlobalNMSEntityMap();

    public static SimpleNPCHandler getNpcHandler() {
        return npcHandler;
    }

    public static SimpleNPCSkinMap getSkinMap() {
        return npcHandler.getSkinMap();
    }

    public static GlobalNMSEntityMap getGlobalEntityMap() {
        return globalEntityMap;
    }
}
