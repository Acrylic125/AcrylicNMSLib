package com.acrylic.version_1_8.entityanimator;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.loaders.CustomEntity;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.packets.EntityAnimationPackets;
import com.acrylic.version_1_8.packets.EntityDestroyPacket;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.TeleportPacket;
import net.minecraft.server.v1_8_R3.EntityGiantZombie;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CustomEntity(name = "GiantInstance",
        entityType = EntityType.GIANT,
        entityTypeNMSClass = EntityGiantZombie.class)
public class GiantEntityInstance extends EntityGiantZombie implements LivingEntityInstance_1_8 {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets = new LivingEntityDisplayPackets();
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();
    private EntityEquipmentPackets equipmentPackets;

    private EntityAI<NMSGiantAnimator> entityAI;
    private final NMSGiantAnimator giantAnimator;

    public GiantEntityInstance(@NotNull NMSGiantAnimator giantAnimator, @NotNull org.bukkit.World world) {
        this(giantAnimator, NMSBukkitConverter.convertToNMSWorld(world));
    }

    public GiantEntityInstance(@NotNull NMSGiantAnimator giantAnimator, @NotNull World world) {
        super(world);
        this.giantAnimator = giantAnimator;
    }

    @Override
    public void tickingEntity() {
        if (this.entityAI != null && giantAnimator != null)
            this.entityAI.update();
    }

    public void setAi(@NotNull EntityAI<NMSGiantAnimator> ai) {
        this.entityAI = ai;
    }

    @Override
    public void setEntityEquipmentPackets(@Nullable EntityEquipmentPackets entityEquipmentPackets) {
        this.equipmentPackets = entityEquipmentPackets;
    }

    @Override
    public EntityEquipmentPackets getEquipmentPackets() {
        return equipmentPackets;
    }

    @Override
    public EntityAnimationPackets getAnimationPackets() {
        return entityAnimationPackets;
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

    @Override
    public EntityGiantZombie getNMSEntity() {
        return this;
    }

    @NotNull
    @Override
    public NMSGiantAnimator getAnimatior() {
        return giantAnimator;
    }

    @NotNull
    @Override
    public TeleportPacket getTeleportPacket() {
        return teleportPacket;
    }

    @NotNull
    @Override
    public EntityDestroyPacket getDestroyPacket() {
        return entityDestroyPacket;
    }

    @NotNull
    @Override
    public LivingEntityDisplayPackets getDisplayPackets() {
        return displayPackets;
    }

    @Override
    public void t_() {
        super.t_();
        tickingEntity();
    }

}
