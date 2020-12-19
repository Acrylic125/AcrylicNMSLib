package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.renderer.EntityRenderer;
import net.minecraft.server.v1_8_R3.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityInstance
        extends com.acrylic.universal.emtityanimator.EntityInstance {

    default Entity getNMSEntity() {
        return getAnimatior().getNMSEntity();
    }

    int getTicksLived();

    @NotNull
    @Override
    NMSEntityAnimator getAnimatior();

    default void render() {
        if (getTicksLived() % 20 == 0) {
            NMSEntityAnimator animator = getAnimatior();
            if (animator instanceof NMSLivingEntityAnimator)
                ((NMSLivingEntityAnimator) animator).setupShowPackets();
            EntityRenderer entityRenderer = animator.getRenderer();
            entityRenderer.setLocation(getAnimatior().getLocation());
            entityRenderer.check();
        }
    }
}
