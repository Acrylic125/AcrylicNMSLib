package com.acrylic.universal.npc;

import com.acrylic.universal.threads.Scheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class SimpleNPCHandler implements NPCHandler {

    private final SimpleNPCSkinMap npcSkinMap = new SimpleNPCSkinMap();
    private final Map<Integer, PlayerNPCEntity> NPCs = new WeakHashMap<>();
    private final List<NPCTabRemoverEntry> entries = new LinkedList<>();

    public SimpleNPCHandler() {
        runTabRemover();
    }

    public void runTabRemover() {
        Scheduler.sync()
                .runRepeatingTask(100, 100)
                .handle(task -> {
                    synchronized (entries) {
                        for (NPCTabRemoverEntry entry : entries)
                            entry.execute();
                        entries.clear();
                    }
                })
                .build();
    }

    @Override
    public SimpleNPCSkinMap getSkinMap() {
        return npcSkinMap;
    }

    @Override
    public Map<Integer, PlayerNPCEntity> getNPCs() {
        return NPCs;
    }

    @Override
    public List<NPCTabRemoverEntry> getNPCTabRemoverEntries() {
        return entries;
    }

}
