package com.acrylic.universal.npc;

import com.acrylic.universal.NMSUtils;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.entityanimations.EntityAnimator;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface NPCHandler {

    NPCSkinMap<?> getSkinMap();

    List<NPCTabRemoverEntry> getNPCTabRemoverEntries();

    default void removeNPC(int id) {
        UniversalNMS.getGlobalEntityMap().removeEntityAnimator(id);
    }

    default void removeNPC(@NotNull PlayerNPCEntity npc) {
        removeNPC(npc.getBukkitEntity().getEntityId());
    }

    default void addNPC(@NotNull PlayerNPCEntity npc) {
        UniversalNMS.getGlobalEntityMap().addEntityAnimator(npc);
    }

    default PlayerNPCEntity getNPC(int id) {
        EntityAnimator entityAnimator = UniversalNMS.getGlobalEntityMap().getEntityAnimator(id);
        return (entityAnimator instanceof PlayerNPCEntity) ? (PlayerNPCEntity) entityAnimator : null;
    }

    default PlayerNPCEntity getNPC(@NotNull Entity entity) {
        return getNPC(entity.getEntityId());
    }

}
