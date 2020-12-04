package com.acrylic.universal.npc;

import com.acrylic.universal.Universal;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SimpleNPCHandler implements NPCHandler {

    private final SimpleNPCSkinMap npcSkinMap = new SimpleNPCSkinMap();
    private final Map<Integer, AbstractPlayerNPCEntity> NPCs = new HashMap<>();
    private final List<NPCTabRemoverEntry> entries = new LinkedList<>();

    public SimpleNPCHandler() {
        runRemover();
    }

    public void runRemover() {
        new BukkitRunnable() {
            @Override
            public synchronized void run() {
                for (NPCTabRemoverEntry entry : entries)
                    entry.execute();
                entries.clear();
            }
        }.runTaskTimer(Universal.getPlugin(), 100, 100);
    }

    @Override
    public SimpleNPCSkinMap getSkinMap() {
        return npcSkinMap;
    }

    @Override
    public Map<Integer, AbstractPlayerNPCEntity> getNPCs() {
        return NPCs;
    }

    @Override
    public List<NPCTabRemoverEntry> getNPCTabRemoverEntries() {
        return entries;
    }

}
