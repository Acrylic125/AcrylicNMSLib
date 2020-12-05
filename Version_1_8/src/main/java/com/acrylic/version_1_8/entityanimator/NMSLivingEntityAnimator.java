package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import net.citizensnpcs.nms.v1_8_R3.util.NMSImpl;
import net.citizensnpcs.util.PlayerAnimation;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public abstract class NMSLivingEntityAnimator extends NMSEntityAnimator implements com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator {

    private EntityEquipmentPackets equipmentPackets;

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
    }

    @Override
    public EntityEquipmentPackets getEquipmentPackets() {
        return equipmentPackets;
    }

    @Override
    public void damageEffect(@NotNull LivingEntity attacker) {

    }

    @Override
    public void damage(@NotNull LivingEntity attacker, double amount) {

    }

    @Override
    public void damage(@NotNull LivingEntity attacker) {
        Entity nmsAttacker = NMSBukkitConverter.convertToNMSEntity(attacker);
        EntityLiving target = getNMSEntity();
        if (nmsAttacker instanceof EntityPlayer) {
            EntityPlayer humanHandle = (EntityPlayer)nmsAttacker;
            humanHandle.attack(target);
            PlayerAnimation.ARM_SWING.play(humanHandle.getBukkitEntity());
        } else {
            EntityLiving nmsLivingAttacker = (EntityLiving) nmsAttacker;
            AttributeInstance attackDamage = nmsLivingAttacker.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE);
            float f = (float)(attackDamage == null ? 1.0D : attackDamage.getValue());
            int i = 0;
            f += EnchantmentManager.a(nmsLivingAttacker.bA(), target.getMonsterType());
            i += EnchantmentManager.a(nmsLivingAttacker);

            boolean flag = target.damageEntity(DamageSource.mobAttack(nmsLivingAttacker), f);
            if (flag) {
                if (i > 0) {
                    target.g(-Math.sin((double)nmsLivingAttacker.yaw * 3.141592653589793D / 180.0D) * (double)i * 0.5D, 0.1D, Math.cos((double)nmsLivingAttacker.yaw * 3.141592653589793D / 180.0D) * (double)i * 0.5D);
                    nmsLivingAttacker.motX *= 0.6D;
                    nmsLivingAttacker.motZ *= 0.6D;
                }

                int fireAspectLevel = EnchantmentManager.getFireAspectEnchantmentLevel(nmsLivingAttacker);
                if (fireAspectLevel > 0) {
                    target.setOnFire(fireAspectLevel * 4);
                }

            }
        }
    }

    @Override
    public void damage(double damage) {

    }
}
