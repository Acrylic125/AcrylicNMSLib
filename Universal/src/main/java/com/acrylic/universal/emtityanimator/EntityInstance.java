package com.acrylic.universal.emtityanimator;

import org.jetbrains.annotations.NotNull;

/**
 * EntityInstance are to be used for custom NMS entities
 * for custom behaviour.
 *
 * @see com.acrylic.universal.entityai.EntityAI
 * @see com.acrylic.universal.entityai.pathfinder.EntityPathfinder
 * @see com.acrylic.universal.entityai.strategy.EntityStrategy
 */
public interface EntityInstance {

    void tickingEntity();

    @NotNull
    NMSEntityAnimator getAnimatior();

}
