package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.entities.AbstractGiantAnimator;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityGiantZombie;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.bukkit.Location;
import org.bukkit.entity.Giant;
import org.jetbrains.annotations.NotNull;

public class NMSGiantAnimator extends NMSLivingEntityAnimator implements AbstractGiantAnimator {

    private final EntityGiantZombie nmsEntity;

    public NMSGiantAnimator(@NotNull Location location) {
        super(location);
        nmsEntity = new EntityGiantZombie(NMSBukkitConverter.convertToNMSWorld(location.getWorld()));
        nmsEntity.setLocation(location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
    }

    @Override
    public AbstractGiantAnimator ai(boolean b) {
        nmsEntity.ai = b;
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
}
