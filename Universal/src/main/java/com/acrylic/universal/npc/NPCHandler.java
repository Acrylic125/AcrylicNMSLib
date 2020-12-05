package com.acrylic.universal.npc;

import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.protocollib.packetlistener.PacketListenerBuilder;
import com.comphenix.protocol.PacketType;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.nms.v1_8_R3.util.NMSImpl;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
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

    default AbstractPlayerNPCEntity getNPC(@NotNull Entity entity) {
        return getNPC(entity.getEntityId());
    }

}
