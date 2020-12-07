package com.acrylic.version_1_8.entityanimator;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface LivingEntityInstance
        extends com.acrylic.universal.emtityanimator.LivingEntityInstance, EntityInstance {

    @Override
    EntityLiving getNMSEntity();

    @NotNull
    @Override
    NMSLivingEntityAnimator getAnimatior();

    default void knockbackEntity(@NotNull EntityLiving nmsLivingAttacker) {
        int knockback = 1;
        knockback += EnchantmentManager.a(nmsLivingAttacker);
        if (knockback > 0) {
            float x = (float) (-Math.sin(Math.toRadians(nmsLivingAttacker.yaw)) * knockback * 0.5f * 0.6f);
            float y = 0.1f;
            float z = (float) (Math.cos(Math.toRadians(nmsLivingAttacker.yaw)) * knockback * 0.5f * 0.6f);
            getAnimatior().setVelocity(x, y, z);
        }
    }

    @Override
    default void knockbackEntity(@NotNull LivingEntity entity) {
        knockbackEntity(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    default void damageEntity(EntityLiving victim, EntityLiving nmsLivingAttacker, float baseDamage) {
        boolean flag = victim.damageEntity(DamageSource.mobAttack(nmsLivingAttacker), baseDamage);
        if (flag) {
            int fireAspectLevel = EnchantmentManager.getFireAspectEnchantmentLevel(nmsLivingAttacker);
            if (fireAspectLevel > 0)
                victim.setOnFire(fireAspectLevel * 4);
        }
    }

    @Override
    default void damageEntity(@NotNull LivingEntity attacker) {
        EntityLiving nmsAttacker = NMSBukkitConverter.convertToNMSEntity(attacker);
        EntityLiving victim = getNMSEntity();
        if (nmsAttacker instanceof EntityPlayer) {
            EntityPlayer entityLiving = (EntityPlayer) nmsAttacker;
            entityLiving.attack(victim);
        } else {
            AttributeInstance attackDamage = nmsAttacker.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE);
            float baseDamage = (float) (attackDamage == null ? 1.0f : attackDamage.getValue());
            baseDamage += EnchantmentManager.a(nmsAttacker.bA(), victim.getMonsterType());
            damageEntity(victim, nmsAttacker, baseDamage);
        }
    }

    @Override
    default void damageEntity(@NotNull LivingEntity attacker, float amount) {
        damageEntity(getNMSEntity(), NMSBukkitConverter.convertToNMSEntity(attacker), amount);
    }

    @Override
    default void damageEntity(float damage) {
        getNMSEntity().damageEntity(DamageSource.GENERIC, damage);
    }
}
