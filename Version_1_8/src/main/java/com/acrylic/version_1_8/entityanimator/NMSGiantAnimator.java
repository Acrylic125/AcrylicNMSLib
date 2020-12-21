package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.entities.AbstractGiantAnimator;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.bukkit.Location;
import org.bukkit.entity.Giant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NMSGiantAnimator extends NMSLivingEntityAnimator implements AbstractGiantAnimator {

    private final GiantEntityInstance nmsEntity;

    public NMSGiantAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull GiantEntityInstance giantZombie) {
        super(initializerLocationalRenderer);
        this.nmsEntity = giantZombie;
    }

    public NMSGiantAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull GiantEntityInstance giantZombie, @NotNull Location location) {
        this(initializerLocationalRenderer, giantZombie);
        nmsEntity.setLocation(location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
    }

    public NMSGiantAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull Location location) {
        this(initializerLocationalRenderer, new GiantEntityInstance(NMSBukkitConverter.convertToNMSWorld(location.getWorld())), location);
    }

    @Override
    public NMSGiantAnimator ai(boolean b) {
        nmsEntity.ai = b;
        return this;
    }

    @Override
    public NMSGiantAnimator visible(boolean b) {
        nmsEntity.setInvisible(!b);
        return this;
    }

    @Override
    public NMSGiantAnimator name(@Nullable String name) {
        if (name != null)
            nmsEntity.setCustomName(ChatUtils.get(name));
        return this;
    }

    @Override
    public NMSGiantAnimator nameVisible(boolean b) {
        nmsEntity.setCustomNameVisible(b);
        return this;
    }

    @Override
    public EntityLiving getNMSEntity() {
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
