package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.EntityAnimationPackets;
import com.acrylic.version_1_8.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public abstract class NMSLivingEntityAnimator
        extends NMSEntityAnimator
        implements com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator {

    private EntityEquipmentPackets equipmentPackets;
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();

    public NMSLivingEntityAnimator(@NotNull Location location) {
        super(location);
    }

    public NMSLivingEntityAnimator(@NotNull LivingEntityDisplayPackets livingEntityDisplayPackets) {
        super(livingEntityDisplayPackets);
    }

    @Override
    public abstract EntityLiving getNMSEntity();

    @Override
    public void setEquipment(AbstractEntityEquipmentBuilder entityEquipment) {
        if (equipmentPackets == null)
            this.equipmentPackets = new EntityEquipmentPackets();
        this.equipmentPackets.adapt(entityEquipment);
        entityEquipment.apply(getBukkitEntity());
    }

    @Override
    public EntityEquipmentPackets getEquipmentPackets() {
        return equipmentPackets;
    }

    @Override
    public EntityAnimationPackets getAnimationPackets() {
        return entityAnimationPackets;
    }

    public void knockback(EntityLiving nmsLivingAttacker) {
        int knockback = 1;
        knockback += EnchantmentManager.a(nmsLivingAttacker);
        if (knockback > 0) {
            float x = (float) (-Math.sin(Math.toRadians(nmsLivingAttacker.yaw)) * knockback * 0.5f * 0.6f);
            float y = 0.1f;
            float z = (float) (Math.cos(Math.toRadians(nmsLivingAttacker.yaw)) * knockback * 0.5f * 0.6f);
            setVelocity(x, y, z);
        }
    }

    @Override
    public void knockback(@NotNull LivingEntity entity) {
        knockback(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    @Override
    public void damageEffect(@NotNull LivingEntity attacker) {
        animate(EntityAnimationEnum.HURT);
    }

    private void damage(EntityLiving victim, EntityLiving nmsLivingAttacker, float baseDamage) {
        boolean flag = victim.damageEntity(DamageSource.mobAttack(nmsLivingAttacker), baseDamage);
        if (flag) {
            int fireAspectLevel = EnchantmentManager.getFireAspectEnchantmentLevel(nmsLivingAttacker);
            if (fireAspectLevel > 0)
                victim.setOnFire(fireAspectLevel * 4);
        }
    }


    @Override
    public void damage(@NotNull LivingEntity attacker, float amount) {
        damage(getNMSEntity(), NMSBukkitConverter.convertToNMSEntity(attacker), amount);
    }

    @Override
    public void damage(@NotNull LivingEntity attacker) {
        EntityLiving nmsAttacker = NMSBukkitConverter.convertToNMSEntity(attacker);
        EntityLiving victim = getNMSEntity();
        if (nmsAttacker instanceof EntityPlayer) {
            EntityPlayer entityLiving = (EntityPlayer) nmsAttacker;
            entityLiving.attack(victim);
        } else {
            AttributeInstance attackDamage = nmsAttacker.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE);
            float baseDamage = (float) (attackDamage == null ? 1.0f : attackDamage.getValue());
            baseDamage += EnchantmentManager.a(nmsAttacker.bA(), victim.getMonsterType());
            damage(victim, nmsAttacker, baseDamage);
        }
    }

    @Override
    public void damage(float damage) {
        getNMSEntity().damageEntity(DamageSource.GENERIC, damage);
    }

    @Override
    public void setVelocity(double x, double y, double z) {
        getNMSEntity().move(x, y, z);
    }
}
