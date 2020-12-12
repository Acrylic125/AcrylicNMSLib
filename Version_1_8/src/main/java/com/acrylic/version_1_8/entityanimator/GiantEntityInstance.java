package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.loaders.CustomEntity;
import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityGiantZombie;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CustomEntity(name = "GiantInstance",
        entityType = EntityType.GIANT,
        entityTypeNMSClass = EntityGiantZombie.class)
public class GiantEntityInstance extends EntityGiantZombie implements LivingEntityInstance {

    private EntityAI<NMSGiantAnimator> entityAI;
    private NMSGiantAnimator giantAnimator;

    public GiantEntityInstance(org.bukkit.World world) {
        this(NMSBukkitConverter.convertToNMSWorld(world));
    }

    public GiantEntityInstance(World world) {
        super(world);
    }

    @Override
    public void tickingEntity() {
        if (this.entityAI != null && giantAnimator != null)
            this.entityAI.update();
    }

    public void setAi(@NotNull EntityAI<NMSGiantAnimator> ai) {
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
            this.entityAI = (EntityAI<NMSGiantAnimator>) ai;
        } catch (ClassCastException ex) {
            throw new IllegalStateException("THe AI specified must be of " + NMSArmorStandAnimator.class.getName() + ".");
        }
    }

    @Nullable
    @Override
    public EntityAI<?> getAI() {
        return this.entityAI;
    }

    public void setAnimator(@Nullable NMSGiantAnimator animator) {
        this.giantAnimator = animator;
    }

    @Override
    public void setAnimator(@Nullable NMSLivingEntityAnimator animator) {
        if (animator == null) {
            this.giantAnimator = null;
            return;
        }
        if (animator instanceof NMSGiantAnimator)
           this.giantAnimator = (NMSGiantAnimator) animator;
        else
            throw new IllegalStateException("THe Animator specified must be of " + NMSGiantAnimator.class.getName() + ".");
    }


    @Override
    public EntityGiantZombie getNMSEntity() {
        return this;
    }

    @NotNull
    @Override
    public NMSGiantAnimator getAnimatior() {
        return giantAnimator;
    }

    @Override
    public void t_() {
        super.t_();
        tickingEntity();
    }
}
