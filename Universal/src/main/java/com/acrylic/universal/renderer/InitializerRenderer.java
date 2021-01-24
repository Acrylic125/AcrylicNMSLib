package com.acrylic.universal.renderer;

import com.acrylic.universal.entityinstances.EntityInstance;
import com.comphenix.protocol.PacketType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * This packet renderer allows for packet handling upon
 * the initial packet call for the player.
 *
 * This is used for {@link EntityInstance}
 * to first send the spawning packets then any subsequent packets like teleporting
 * to those who have already received these packets.
 *
 * @see com.acrylic.universal.emtityanimator.NMSEntityAnimator
 */
public interface InitializerRenderer extends Renderer {

    boolean canInitialize(@NotNull Player player);

    boolean hasInitialized(@NotNull UUID uuid);

    void setTerminationAction(@NotNull Consumer<Player> terminationAction);

    Consumer<Player> getTerminationAction();

    void checkTermination();

    /**
     * When the player is no longer permitted to
     * view the packets, this method is called.
     */
    void terminate(@NotNull Player player);

    void terminate(@NotNull Collection<? extends Player> players);

    void terminateWithCache(@NotNull RendererCache rendererCache);

    void setInitializationAction(@NotNull Consumer<Player> initializationAction);

    Consumer<Player> getInitializationAction();

    void checkInitialization();

    /**
     * The initial packets.
     */
    void initialize(@NotNull Player player);

    void initialize(@NotNull Collection<? extends Player> players);

    void initialize(@NotNull RendererCache rendererCache);

    void render(@NotNull Player player);

    void render(@NotNull Collection<? extends Player> players);

    void renderWithRendererCache(@NotNull RendererCache rendererCache);

    void unrender(@NotNull Player player);

    void unrender(@NotNull Collection<? extends Player> players);

    void unrenderWithRendererCache(@NotNull RendererCache rendererCache);

    void unrenderAll();

    default void check() {
        checkTermination();
        checkInitialization();
    }

}
