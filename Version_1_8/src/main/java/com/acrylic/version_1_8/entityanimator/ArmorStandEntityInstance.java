package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.loaders.CustomEntity;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.EntityAnimationPackets;
import com.acrylic.version_1_8.packets.EntityDestroyPacket;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.TeleportPacket;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CustomEntity(name = "ArmorStandInstance",
        entityType = EntityType.ARMOR_STAND,
        entityTypeNMSClass = EntityArmorStand.class)
public class ArmorStandEntityInstance extends EntityArmorStand implements LivingEntityInstance_1_8 {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets = new LivingEntityDisplayPackets();
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();
    private EntityEquipmentPackets equipmentPackets;

    private final NMSArmorStandAnimator armorStandAnimator;
    private EntityAI<NMSArmorStandAnimator> entityAI;

    public ArmorStandEntityInstance(@NotNull NMSArmorStandAnimator armorStandAnimator, @NotNull org.bukkit.World world) {
        this(armorStandAnimator, NMSBukkitConverter.convertToNMSWorld(world));
    }

    public ArmorStandEntityInstance(@NotNull NMSArmorStandAnimator armorStandAnimator, @NotNull World world) {
        super(world);
        this.armorStandAnimator = armorStandAnimator;
    }

    public ArmorStandEntityInstance(@NotNull NMSArmorStandAnimator armorStandAnimator, @NotNull World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        this.armorStandAnimator = armorStandAnimator;
    }

    @Override
    public void tickingEntity() {
        if (this.entityAI != null && armorStandAnimator != null)
            this.entityAI.update();
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
    public NMSArmorStandAnimator getAnimatior() {
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
    }

    @Override
    public boolean isDead() {
        return dead;
    }

}
