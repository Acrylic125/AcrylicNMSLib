package com.acrylic.universal.npc;

import com.acrylic.universal.packets.SinglePacketSender;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface NPCPlayerInfoPacket extends SinglePacketSender {

    @Getter
    enum EnumPlayerInfoAction {
        ADD_PLAYER("ADD_PLAYER"),
        UPDATE_GAME_MODE("UPDATE_GAME_MODE"),
        UPDATE_LATENCY("UPDATE_LATENCY"),
        UPDATE_DISPLAY_NAME("UPDATE_DISPLAY_NAME"),
        REMOVE_PLAYER("REMOVE_PLAYER");

        private final String identifier;

        EnumPlayerInfoAction(String identifier) {
            this.identifier = identifier;
        }
    }

    void apply(@NotNull Player player, @NotNull EnumPlayerInfoAction action);

}
