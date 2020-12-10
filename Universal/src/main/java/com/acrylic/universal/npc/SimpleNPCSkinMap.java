package com.acrylic.universal.npc;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SimpleNPCSkinMap implements NPCSkinMap<SimpleNPCSkin> {

    private final Map<String, SimpleNPCSkin> map = new HashMap<>();

    @Override
    public Map<String, SimpleNPCSkin> getMap() {
        return map;
    }

    @Override
    public SimpleNPCSkin getAndAddIfNotExist(@NotNull String name) {
        SimpleNPCSkin simpleNPCSkin = getSkin(name);
        if (simpleNPCSkin == null) {
            simpleNPCSkin = new SimpleNPCSkin(name);
            addSkin(name, simpleNPCSkin);
        }
        return simpleNPCSkin;
    }
}
