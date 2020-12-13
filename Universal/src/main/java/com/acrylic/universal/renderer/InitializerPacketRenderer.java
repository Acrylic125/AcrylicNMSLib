package com.acrylic.universal.renderer;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This packet renderer allows for packet handling upon
 * the initial packet call for the player.
 *
 * This is used for {@link com.acrylic.universal.emtityanimator.EntityInstance}
 * to first send the spawning packets then any subsequent packets like teleporting
 * to those who have already received these packets.
 */
public interface InitializerPacketRenderer extends PacketRenderer {

    boolean hasInitialized(@NotNull UUID uuid);

    void checkTermination();

    /**
     * When the player is no longer permitted to
     * view the packets, this method is called.
     */
    void terminate(@NotNull Player player);

    void checkInitialization();

    /**
     * The initial packets.
     */
    void initialize(@NotNull Player player);

    default void check() {
        checkTermination();
        checkInitialization();
    }

}
