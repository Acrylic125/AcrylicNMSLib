package com.acrylic.universal.npc;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.emtityanimator.instances.PlayerNPC;
import com.acrylic.universal.entityanimations.EntityAnimator;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface NPCHandler {

    NPCSkinMap<?> getSkinMap();

    List<NPCTabRemoverEntry> getNPCTabRemoverEntries();

    default void removeNPC(int id) {
        UniversalNMS.getGlobalEntityMap().removeEntityAnimator(id);
    }

    default void removeNPC(@NotNull PlayerNPC npc) {
        removeNPC(npc.getBukkitEntity().getEntityId());
    }

    default void addNPC(@NotNull PlayerNPC npc) {
        UniversalNMS.getGlobalEntityMap().addEntityAnimator(npc);
    }

    default PlayerNPC getNPC(int id) {
        EntityAnimator entityAnimator = UniversalNMS.getGlobalEntityMap().getEntityAnimator(id);
        return (entityAnimator instanceof PlayerNPC) ? (PlayerNPC) entityAnimator : null;
    }

    default PlayerNPC getNPC(@NotNull Entity entity) {
        return getNPC(entity.getEntityId());
    }

}
