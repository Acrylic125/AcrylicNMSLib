package com.acrylic.universal.npc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleNPCHandler implements NPCHandler {

    private final SimpleNPCSkinMap npcSkinMap = new SimpleNPCSkinMap();
    private final Map<UUID, AbstractNPCEntity> NPCs = new HashMap<>();

    @Override
    public SimpleNPCSkinMap getSkinMap() {
        return npcSkinMap;
    }

    @Override
    public Map<UUID, AbstractNPCEntity> getNPCs() {
        return NPCs;
    }

}
