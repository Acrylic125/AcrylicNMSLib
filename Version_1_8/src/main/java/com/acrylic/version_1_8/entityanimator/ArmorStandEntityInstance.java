package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.loaders.CustomEntity;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.EntityAnimationPackets;
import com.acrylic.version_1_8.packets.EntityDestroyPacket;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.TeleportPacket;
import com.acrylic.universal.emtityanimator.NMSArmorStandAnimator;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.Vector3f;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CustomEntity(name = "ArmorStandInstance",
        entityType = EntityType.ARMOR_STAND,
        entityTypeNMSClass = EntityArmorStand.class)
public class ArmorStandEntityInstance
        extends EntityArmorStand
        implements LivingEntityInstance_1_8, com.acrylic.universal.entityinstances.instances.ArmorStandEntityInstance {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets = new LivingEntityDisplayPackets();
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();
    private EntityEquipmentPackets equipmentPackets;

    private final NMSArmorStandAnimator armorStandAnimator;
    private EntityAI<NMSArmorStandAnimator> entityAI;

    public ArmorStandEntityInstance(@NotNull NMSArmorStandAnimator armorStandAnimator, @NotNull Location location) {
        this(armorStandAnimator, NMSBukkitConverter.convertToNMSWorld(location.getWorld()));
        initialize(location);
    }

    public ArmorStandEntityInstance(@NotNull NMSArmorStandAnimator armorStandAnimator, @NotNull World world) {
        super(world);
        this.armorStandAnimator = armorStandAnimator;
        entityDestroyPacket.apply(getBukkitEntity());
    }

    public ArmorStandEntityInstance(@NotNull NMSArmorStandAnimator armorStandAnimator, @NotNull World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.armorStandAnimator = armorStandAnimator;
    }

    public void setAi(@NotNull EntityAI<NMSArmorStandAnimator> ai) {
        this.entityAI = ai;
    }

    @Override
    public void setEntityEquipmentPackets(@Nullable EntityEquipmentPackets entityEquipmentPackets) {
        this.equipmentPackets = entityEquipmentPackets;
    }

    @Override
    public EntityEquipmentPackets getEquipmentPackets() {
        return equipmentPackets;
    }

    @Override
    public EntityAnimationPackets getAnimationPackets() {
        return entityAnimationPackets;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setAI(@Nullable EntityAI<?> ai) {
        if (ai == null) {
            this.entityAI = null;
            return;
        }
        try {
            this.entityAI = (EntityAI<NMSArmorStandAnimator>) ai;
        } catch (ClassCastException ex) {
            throw new IllegalStateException("THe AI specified must be of " + NMSArmorStandAnimator.class.getName() + ".");
        }
    }

    @Nullable
    @Override
    public EntityAI<?> getAI() {
        return this.entityAI;
    }

    @Override
    public EntityArmorStand getNMSEntity() {
        return this;
    }

    @NotNull
    @Override
    public NMSArmorStandAnimator getAnimator() {
        return armorStandAnimator;
    }

    @NotNull
    @Override
    public TeleportPacket getTeleportPacket() {
        return teleportPacket;
    }

    @NotNull
    @Override
    public EntityDestroyPacket getDestroyPacket() {
        return entityDestroyPacket;
    }

    @NotNull
    @Override
    public LivingEntityDisplayPackets getDisplayPackets() {
        return displayPackets;
    }

    @Override
    public void t_() {
        super.t_();
        if (!isDead()) {
            tickingEntity();
            render();
        }
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    private Vector3f toVector3f(@NotNull EulerAngle eulerAngle) {
        return new Vector3f((float) Math.toDegrees(eulerAngle.getX()), (float) Math.toDegrees(eulerAngle.getY()), (float) Math.toDegrees(eulerAngle.getZ()));
    }

    @Override
    public void setMarker(boolean b) {
        ((ArmorStand) getBukkitEntity()).setMarker(b);
    }

    @Override
    public void setHeadRotation(@NotNull EulerAngle angle) {
        super.setHeadPose(toVector3f(angle));
    }

    @Override
    public void setLeftArmRotation(@NotNull EulerAngle angle) {
        super.setLeftArmPose(toVector3f(angle));
    }

    @Override
    public void setRightArmRotation(@NotNull EulerAngle angle) {
        super.setRightArmPose(toVector3f(angle));
    }

    @Override
    public void setLeftLegRotation(@NotNull EulerAngle angle) {
        super.setLeftLegPose(toVector3f(angle));
    }

    @Override
    public void setRightLegRotation(@NotNull EulerAngle angle) {
        super.setRightLegPose(toVector3f(angle));
    }

}
