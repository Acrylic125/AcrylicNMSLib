package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.EntityEquipmentPackets;
import net.minecraft.server.v1_8_R3.EntityLiving;
import org.bukkit.Location;
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

}
