package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.packets.EntityAnimationPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LivingEntityInstance
        extends EntityInstance, DamageableEntityInstance {

    int getMaxDamageCooldown();

    void setMaxDamageCooldown(int ticks);

    boolean isNoClip();

    void setNoClip(boolean b);

    void setEntityEquipmentPackets(@Nullable EntityEquipmentPackets entityEquipmentPackets);

    EntityEquipmentPackets getEquipmentPackets();

    EntityAnimationPackets getAnimationPackets();

    default void healEntity(double amount) {
        LivingEntity entity = getAnimatior().getBukkitEntity();
        entity.setHealth(Math.max(amount + entity.getHealth(), entity.getMaxHealth()));
    }

    default void healEntity() {
        healEntity(getAnimatior().getBukkitEntity().getMaxHealth());
    }

    void setAI(@Nullable EntityAI<?> ai);

    @Nullable
    EntityAI<?> getAI();

    @NotNull
    @Override
    NMSLivingEntityAnimator getAnimatior();

    default void setupTermination() {
        getAnimatior().getRenderer().setTerminationAction(player -> {
            getDestroyPacket().send(player);
        });
    }

    @Override
    default void setupShowPackets() {
        getAnimatior().getRenderer().setInitializationAction(player -> {
            LivingEntityDisplayPackets displayPackets = getDisplayPackets();
            displayPackets.setupDisplayPackets(getAnimatior());
            displayPackets.send(player);
        });
    }

    @Override
    default void render() {
        if (getTicksLived() % 20 == 0) {
            InitializerLocationalRenderer initializerLocationalRenderer = getAnimatior().getRenderer();
            initializerLocationalRenderer.setLocation(getAnimatior().getLocation());
            initializerLocationalRenderer.check();
        }
    }

}
