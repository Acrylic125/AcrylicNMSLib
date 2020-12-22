package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface EntityAI<T extends LivingEntityAnimator>
        extends Cloneable {

    T getAnimator();

    void update();

    /**
     * This method cleans up the AI, to remove any
     * unused instances.
     */
    void cleanAI();

    /**
     *
     * @see com.acrylic.universal.emtityanimator.GlobalNMSEntityMap
     *
     * @param player The player that should be unloaded for.
     */
    void aiUnloadCheck(@NotNull Player player);

}
