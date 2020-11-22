package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.entities.AbstractArmorStandAnimator;
import com.acrylic.universal.packets.EntityDestroyPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.packets.TeleportPacket;
import com.acrylic.universal.renderer.PacketRenderer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class NMSArmorStandAnimator implements com.acrylic.universal.emtityanimator.NMSArmorStandAnimator {
    @Override
    public Object getNMSEntity() {
        return null;
    }

    @Override
    public PacketRenderer getRenderer() {
        return null;
    }

    @Override
    public void setRenderer(PacketRenderer packetRenderer) {

    }

    @NotNull
    @Override
    public TeleportPacket getTeleportPacket() {
        return null;
    }

    @NotNull
    @Override
    public EntityDestroyPacket getDestroyPacket() {
        return null;
    }

    @NotNull
    @Override
    public LivingEntityDisplayPackets getDisplayPackets() {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator marker(boolean b) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator small(boolean b) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator basePlate(boolean b) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator arms(boolean b) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator gravity(boolean b) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator rightArmPose(@NotNull EulerAngle eulerAngle) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator leftArmPose(@NotNull EulerAngle eulerAngle) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator rightLegPose(@NotNull EulerAngle eulerAngle) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator leftLegPose(@NotNull EulerAngle eulerAngle) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator headPose(@NotNull EulerAngle eulerAngle) {
        return null;
    }

    @Override
    public AbstractArmorStandAnimator visible(boolean b) {
        return null;
    }

    @Override
    public ArmorStand getBukkitEntity() {
        return null;
    }
}
