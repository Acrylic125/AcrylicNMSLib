package com.acrylic.universal.npc;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public interface NPCHandler {

    NPCSkinMap<?> getSkinMap();

    Map<UUID, AbstractNPCEntity> getNPCs();



    default void addNPC(AbstractNPCEntity npc) {
        getNPCs().put(npc.getBukkitEntity().getUniqueId(), npc);
    }

    default AbstractNPCEntity getNPC(@NotNull UUID id) {
        return getNPCs().get(id);
    }

}
