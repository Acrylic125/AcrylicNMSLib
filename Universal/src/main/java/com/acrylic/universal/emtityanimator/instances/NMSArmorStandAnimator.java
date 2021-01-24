package com.acrylic.universal.emtityanimator.instances;

import com.acrylic.universal.NMSAbstractFactory;
import com.acrylic.universal.emtityanimator.instances.NMSLivingEntityAnimator;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.entityinstances.LivingEntityInstance;
import com.acrylic.universal.entityinstances.instances.ArmorStandEntityInstance;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class NMSArmorStandAnimator
        extends NMSLivingEntityAnimator
        implements AbstractArmorStandAnimator {

    private final ArmorStandEntityInstance nmsEntity;

    public NMSArmorStandAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull Location location) {
        super(initializerLocationalRenderer);
        this.nmsEntity = NMSAbstractFactory.getAbstractFactory().getEntityFactory().getNewArmorStandEntityInstance(this, location);
    }

    @Override
    public Object getNMSEntity() {
        return nmsEntity;
    }

    @Override
    public AbstractArmorStandAnimator marker(boolean b) {
        getBukkitEntity().setMarker(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator small(boolean b) {
        nmsEntity.setSmall(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator basePlate(boolean b) {
        nmsEntity.setBasePlate(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator arms(boolean b) {
        nmsEntity.setArms(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator gravity(boolean b) {
        nmsEntity.setGravity(b);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator rightArmPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setRightArmRotation(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator leftArmPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setLeftArmRotation(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator rightLegPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setRightLegRotation(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator leftLegPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setLeftLegRotation(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator headPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setHeadRotation(eulerAngle);
        return this;
    }

    @Override
    public AbstractArmorStandAnimator visible(boolean b) {
        nmsEntity.setVisible(b);
        return this;
    }

    @Override
    public ArmorStand getBukkitEntity() {
        return (ArmorStand) nmsEntity.getBukkitEntity();
    }

    @Override
    public LivingEntityInstance getEntityInstance() {
        return nmsEntity;
    }
}
