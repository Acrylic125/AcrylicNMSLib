package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import net.minecraft.server.v1_8_R3.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class NMSEntityAnimator
        implements com.acrylic.universal.emtityanimator.NMSEntityAnimator {

    private InitializerLocationalRenderer initializerLocationalRenderer;

    public NMSEntityAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer) {
        this.initializerLocationalRenderer = initializerLocationalRenderer;
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        getNMSEntity().move(x, y, z);
    }

    @Override
    public abstract Entity getNMSEntity();

    @NotNull
    @Override
    public InitializerLocationalRenderer getRenderer() {
        return initializerLocationalRenderer;
    }

    @Override
    public void setRenderer(InitializerLocationalRenderer initializerLocationalRenderer) {
        this.initializerLocationalRenderer = initializerLocationalRenderer;
    }


}
