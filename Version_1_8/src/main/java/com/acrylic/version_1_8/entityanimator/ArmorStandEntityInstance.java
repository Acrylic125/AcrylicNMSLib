package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArmorStandEntityInstance extends EntityArmorStand implements LivingEntityInstance {

    private EntityAI<NMSArmorStandAnimator> ao;
    private NMSArmorStandAnimator armorStandAnimator;

    public ArmorStandEntityInstance(org.bukkit.World world) {
        this(NMSBukkitConverter.convertToNMSWorld(world));
    }

    public ArmorStandEntityInstance(World world) {
        super(world);
    }

    public ArmorStandEntityInstance(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    @Override
    public void tickingEntity() {

    }

    @Nullable
    @Override
    public EntityAI<?> getAI() {
        return null;
    }


    @Override
    public EntityArmorStand getNMSEntity() {
        return this;
    }

    @NotNull
    @Override
    public NMSArmorStandAnimator getAnimatior() {
        return armorStandAnimator;
    }

    @Override
    public void t_() {
        super.t_();
        tickingEntity();
    }
}
