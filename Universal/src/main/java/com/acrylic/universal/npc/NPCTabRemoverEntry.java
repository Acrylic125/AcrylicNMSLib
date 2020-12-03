package com.acrylic.universal.npc;

import com.acrylic.universal.misc.PlayerEntry;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NPCTabRemoverEntry extends PlayerEntry {

    private final NPCPlayerInfoPacket removePlayerFromTabPacket;

    public NPCTabRemoverEntry(@NotNull Player player, @NotNull NPCPlayerInfoPacket removePacket) {
        super(player);
        this.removePlayerFromTabPacket = removePacket;
    }

    @Override
    public void execute() {
        removePlayerFromTabPacket.send(getPlayer());
    }
}
