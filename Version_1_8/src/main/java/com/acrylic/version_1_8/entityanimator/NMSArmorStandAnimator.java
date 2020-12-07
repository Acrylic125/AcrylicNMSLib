package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.LivingEntityInstance;
import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.Vector3f;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class NMSArmorStandAnimator extends NMSLivingEntityAnimator implements AbstractArmorStandAnimator {

    private final ArmorStandEntityInstance nmsEntity;

    public NMSArmorStandAnimator(@NotNull ArmorStandEntityInstance armorStand) {
        super();
        this.nmsEntity = armorStand;
    }

    public NMSArmorStandAnimator(@NotNull ArmorStandEntityInstance armorStand, @NotNull Location location) {
        this(armorStand);
        nmsEntity.setLocation(location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
    }

    public NMSArmorStandAnimator(@NotNull Location location) {
        this(new ArmorStandEntityInstance(NMSBukkitConverter.convertToNMSWorld(location.getWorld())), location);
    }

    private Vector3f toVector3f(@NotNull EulerAngle eulerAngle) {
        return new Vector3f((float) Math.toDegrees(eulerAngle.getX()), (float) Math.toDegrees(eulerAngle.getY()), (float) Math.toDegrees(eulerAngle.getZ()));
    }

    @Override
    public EntityArmorStand getNMSEntity() {
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
        nmsEntity.setRightArmPose(toVector3f(eulerAngle));
        return this;
    }

    @Override
    public AbstractArmorStandAnimator leftArmPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setLeftArmPose(toVector3f(eulerAngle));
        return this;
    }

    @Override
    public AbstractArmorStandAnimator rightLegPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setRightLegPose(toVector3f(eulerAngle));
        return this;
    }

    @Override
    public AbstractArmorStandAnimator leftLegPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setLeftLegPose(toVector3f(eulerAngle));
        return this;
    }

    @Override
    public AbstractArmorStandAnimator headPose(@NotNull EulerAngle eulerAngle) {
        nmsEntity.setHeadPose(toVector3f(eulerAngle));
        return this;
    }

    @Override
    public AbstractArmorStandAnimator visible(boolean b) {
        nmsEntity.setInvisible(!b);
        return this;
    }

    @Override
    public ArmorStand getBukkitEntity() {
        return (ArmorStand) getNMSEntity().getBukkitEntity();
    }

    @Override
    public LivingEntityInstance getEntityInstance() {
        return nmsEntity;
    }
}
