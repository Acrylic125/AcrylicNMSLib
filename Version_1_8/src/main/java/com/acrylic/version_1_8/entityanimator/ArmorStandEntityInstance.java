package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.loaders.CustomEntity;
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

    public void setAnimator(@Nullable NMSArmorStandAnimator animator) {
        this.armorStandAnimator = animator;
    }

    @Override
    public void setAnimator(@Nullable NMSLivingEntityAnimator animator) {
        if (animator == null) {
            this.armorStandAnimator = null;
            return;
        }
        if (animator instanceof NMSArmorStandAnimator)
           this.armorStandAnimator = (NMSArmorStandAnimator) animator;
        else
            throw new IllegalStateException("THe Animator specified must be of " + NMSArmorStandAnimator.class.getName() + ".");
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

    @Override
    public int getTicksLived() {
        return ticksLived;
    }

    @Override
    public void t_() {
        super.t_();
    }

}
