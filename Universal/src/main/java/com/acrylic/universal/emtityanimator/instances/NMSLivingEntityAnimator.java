package com.acrylic.universal.emtityanimator.instances;

import com.acrylic.universal.NMSAbstractFactory;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.universal.entityinstances.LivingEntityInstance;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.packets.EntityAnimationPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.jetbrains.annotations.NotNull;

public abstract class NMSLivingEntityAnimator
        extends NMSEntityAnimator
        implements LivingEntityAnimator {

    public NMSLivingEntityAnimator(@NotNull InitializerLocationalRenderer renderer) {
        super(renderer);
    }

    @Override
    public abstract LivingEntityInstance getEntityInstance();

    public void setEquipment(AbstractEntityEquipmentBuilder entityEquipment) {
        EntityEquipmentPackets entityEquipmentPackets = getEntityInstance().getEquipmentPackets();
        if (entityEquipmentPackets == null)
            entityEquipmentPackets = NMSAbstractFactory.getAbstractFactory().getPacketFactory().getNewEquipmentPackets();
        entityEquipmentPackets.adapt(entityEquipment);
        getEntityInstance().setEntityEquipmentPackets(entityEquipmentPackets);
        entityEquipment.apply(getBukkitEntity());
        getEntityInstance().getEquipmentPackets().send(getRenderer());
    }

    public void animate(EntityAnimationEnum... animation) {
        EntityAnimationPackets entityAnimationPackets = getEntityInstance().getAnimationPackets();
        entityAnimationPackets.apply(getBukkitEntity(), animation);
        entityAnimationPackets.send(getRenderer());
    }

    @Override
    public void delete() {
        super.delete();
        UniversalNMS.getGlobalEntityMap().removeEntityAnimator(this);
    }

}
