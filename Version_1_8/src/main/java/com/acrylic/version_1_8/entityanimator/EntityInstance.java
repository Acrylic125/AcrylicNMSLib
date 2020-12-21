package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import net.minecraft.server.v1_8_R3.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityInstance
        extends com.acrylic.universal.emtityanimator.EntityInstance {

    default Entity getNMSEntity() {
        return getAnimatior().getNMSEntity();
    }

    @NotNull
    @Override
    NMSEntityAnimator getAnimatior();

}
