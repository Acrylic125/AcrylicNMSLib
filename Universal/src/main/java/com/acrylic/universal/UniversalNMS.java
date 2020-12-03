package com.acrylic.universal;

import com.acrylic.universal.npc.SimpleNPCSkinMap;

public final class UniversalNMS {

    private static final SimpleNPCSkinMap skinMap = new SimpleNPCSkinMap();

    public static SimpleNPCSkinMap getSkinMap() {
        return skinMap;
    }
}
