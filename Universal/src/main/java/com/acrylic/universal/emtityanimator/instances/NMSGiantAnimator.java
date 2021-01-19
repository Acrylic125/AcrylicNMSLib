package com.acrylic.universal.emtityanimator.instances;

import com.acrylic.universal.NMSAbstractFactory;
import com.acrylic.universal.entityanimations.entities.AbstractGiantAnimator;
import com.acrylic.universal.entityinstances.instances.GiantEntityInstance;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.bukkit.Location;
import org.bukkit.entity.Giant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NMSGiantAnimator extends NMSLivingEntityAnimator implements AbstractGiantAnimator {

    private final GiantEntityInstance nmsEntity;

    public NMSGiantAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull Location location) {
        super(initializerLocationalRenderer);
        this.nmsEntity = NMSAbstractFactory.getAbstractFactory().getEntityFactory().getNewGiantEntityInstance(this, location);
    }

    @Override
    public NMSGiantAnimator ai(boolean b) {
        nmsEntity.setMCAI(b);
        return this;
    }

    @Override
    public NMSGiantAnimator visible(boolean b) {
        nmsEntity.setVisible(b);
        return this;
    }

    @Override
    public NMSGiantAnimator name(@Nullable String name) {
        if (name != null)
            nmsEntity.setName(name);
        return this;
    }

    @Override
    public NMSGiantAnimator nameVisible(boolean b) {
        nmsEntity.setNameVisible(b);
        return this;
    }

    @Override
    public Object getNMSEntity() {
        return nmsEntity;
    }

    @Override
    public Giant getBukkitEntity() {
        return (Giant) nmsEntity.getBukkitEntity();
    }

    @Override
    public GiantEntityInstance getEntityInstance() {
        return nmsEntity;
    }
}
