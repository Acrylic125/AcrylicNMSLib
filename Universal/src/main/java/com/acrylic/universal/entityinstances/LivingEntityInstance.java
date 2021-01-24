package com.acrylic.universal.entityinstances;

import com.acrylic.universal.emtityanimator.instances.NMSLivingEntityAnimator;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.packets.EntityAnimationPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LivingEntityInstance
        extends EntityInstance, DamageableEntityInstance {

    @Override
    default void tickingEntity() {
        EntityAI<?> entityAI = getAI();
        if (entityAI != null)
            entityAI.update();
    }

    void setEntityEquipmentPackets(@Nullable EntityEquipmentPackets entityEquipmentPackets);

    EntityEquipmentPackets getEquipmentPackets();

    EntityAnimationPackets getAnimationPackets();

    default void healEntity(double amount) {
        LivingEntity entity = getAnimator().getBukkitEntity();
        entity.setHealth(Math.max(amount + entity.getHealth(), entity.getMaxHealth()));
    }

    default void healEntity() {
        healEntity(getAnimator().getBukkitEntity().getMaxHealth());
    }

    void setAI(@Nullable EntityAI<?> ai);

    @Nullable
    EntityAI<?> getAI();

    @NotNull
    @Override
    NMSLivingEntityAnimator getAnimator();

    default void setupTermination() {
        getAnimator().getRenderer().setTerminationAction(player -> {
            getDestroyPacket().send(player);
        });
    }

    @Override
    default void setupShowPackets() {
         getAnimator().getRenderer().setInitializationAction(player -> {
            LivingEntityDisplayPackets displayPackets = getDisplayPackets();
            displayPackets.setupDisplayPackets(getAnimator());
            displayPackets.send(player);
        });
    }

    @Override
    default void forceRender() {
        InitializerLocationalRenderer initializerLocationalRenderer = getAnimator().getRenderer();
        initializerLocationalRenderer.setLocation(getAnimator().getLocation());
        initializerLocationalRenderer.check();
    }

}
