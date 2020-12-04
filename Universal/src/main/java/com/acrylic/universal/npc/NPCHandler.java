package com.acrylic.universal.npc;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface NPCHandler {

    NPCSkinMap<?> getSkinMap();

    Map<Integer, AbstractPlayerNPCEntity> getNPCs();

    List<NPCTabRemoverEntry> getNPCTabRemoverEntries();

    default void addNPC(AbstractPlayerNPCEntity npc) {
        getNPCs().put(npc.getBukkitEntity().getEntityId(), npc);
    }

    default AbstractPlayerNPCEntity getNPC(@NotNull int id) {
        return getNPCs().get(id);
    }

}
