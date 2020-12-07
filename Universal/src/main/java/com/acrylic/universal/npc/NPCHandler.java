package com.acrylic.universal.npc;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface NPCHandler {

    NPCSkinMap<?> getSkinMap();

    Map<Integer, PlayerNPCEntity> getNPCs();

    List<NPCTabRemoverEntry> getNPCTabRemoverEntries();

    default void addNPC(PlayerNPCEntity npc) {
        getNPCs().put(npc.getBukkitEntity().getEntityId(), npc);
    }

    default PlayerNPCEntity getNPC(@NotNull int id) {
        return getNPCs().get(id);
    }

    default PlayerNPCEntity getNPC(@NotNull Entity entity) {
        return getNPC(entity.getEntityId());
    }

}
