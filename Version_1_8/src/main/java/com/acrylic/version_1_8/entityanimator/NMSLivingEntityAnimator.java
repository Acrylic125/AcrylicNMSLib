package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.version_1_8.packets.EntityAnimationPackets;
import com.acrylic.version_1_8.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public abstract class NMSLivingEntityAnimator
        extends NMSEntityAnimator
        implements com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator {

    private EntityEquipmentPackets equipmentPackets;
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();

    public NMSLivingEntityAnimator() {
        super();
    }

    public NMSLivingEntityAnimator(@NotNull LivingEntityDisplayPackets livingEntityDisplayPackets) {
        super(livingEntityDisplayPackets);
    }

    @Override
    public void setYaw(float yaw) {
        getNMSEntity().yaw = yaw;
    }

    @Override
    public void setPitch(float pitch) {
        getNMSEntity().pitch = pitch;
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

    @Override
    public void damageEffect(@NotNull LivingEntity attacker) {
        animate(EntityAnimationEnum.HURT);
    }

    @Override
    public void delete() {
        super.delete();
        getNMSEntity().die();
    }
}
