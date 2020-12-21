package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.loaders.CustomEntity;
import com.acrylic.universal.packets.*;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CustomEntity(name = "ArmorStandInstance",
        entityType = EntityType.ARMOR_STAND,
        entityTypeNMSClass = EntityArmorStand.class)
public class ArmorStandEntityInstance extends EntityArmorStand implements LivingEntityInstance {

    private EntityAI<NMSArmorStandAnimator> entityAI;
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
        if (this.entityAI != null && armorStandAnimator != null)
            this.entityAI.update();
    }

    public void setAi(@NotNull EntityAI<NMSArmorStandAnimator> ai) {
        this.entityAI = ai;
    }

    @Override
    public int getMaxDamageCooldown() {
        return 0;
    }

    @Override
    public void setMaxDamageCooldown(int ticks) {

    }

    @Override
    public boolean isNoClip() {
        return false;
    }

    @Override
    public void setNoClip(boolean b) {

    }

    @Override
    public void setEntityEquipmentPackets(@Nullable EntityEquipmentPackets entityEquipmentPackets) {

    }

    @Override
    public EntityEquipmentPackets getEquipmentPackets() {
        return null;
    }

    @Override
    public EntityAnimationPackets getAnimationPackets() {
        return null;
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
    public void setFireTicks(int ticks) {
        fireTicks = ticks;
    }

    @Override
    public int getFireTicks() {
        return fireTicks;
    }

    @Override
    public int getTicksLived() {
        return ticksLived;
    }

    @Override
    public void t_() {
        super.t_();
    }

    @Override
    public void removeFromWorld() {

    }

    @Override
    public void addToWorld() {

    }

    @Override
    public void delete() {

    }
}
