package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import com.acrylic.version_1_8.packets.EntityEquipmentPackets;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.jetbrains.annotations.NotNull;

public abstract class NMSLivingEntityAnimator
        extends NMSEntityAnimator
        implements com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator {

    public NMSLivingEntityAnimator(@NotNull InitializerLocationalRenderer initializerLocationalRenderer) {
        super(initializerLocationalRenderer);
    }

    @Override
    public boolean isNoClip() {
        return getNMSEntity().noclip;
    }

    @Override
    public void setNoClip(boolean b) {
        getNMSEntity().noclip = b;
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
        EntityEquipmentPackets entityEquipmentPackets = (EntityEquipmentPackets) getEntityInstance().getEquipmentPackets();
        if (entityEquipmentPackets == null)
            entityEquipmentPackets = new EntityEquipmentPackets();
        entityEquipmentPackets.adapt(entityEquipment);
        getEntityInstance().setEntityEquipmentPackets(entityEquipmentPackets);
        entityEquipment.apply(getBukkitEntity());
        getEntityInstance().getEquipmentPackets().send(getRenderer());
    }

    @Override
    public void delete() {
        super.delete();
        getNMSEntity().die();
    }
}
