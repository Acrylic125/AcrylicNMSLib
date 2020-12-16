package com.acrylic.universal.factory;

import com.acrylic.universal.npc.PlayerNPCEntity;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface EntityFactory {

    PlayerNPCEntity createNPC(@NotNull Location location, @NotNull String name);

}
