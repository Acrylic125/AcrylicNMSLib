package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.packets.EntityAnimationPackets;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface NMSLivingEntityAnimator extends LivingEntityAnimator, NMSEntityAnimator {

    @Override
    LivingEntityInstance getEntityInstance();

    EntityAnimationPackets getAnimationPackets();

    void damageEffect(@NotNull LivingEntity attacker);

    EntityEquipmentPackets getEquipmentPackets();

    default void animate(EntityAnimationEnum... animation) {
        EntityAnimationPackets entityAnimationPackets = getAnimationPackets();
        entityAnimationPackets.apply(getBukkitEntity(), animation);
        sendPacketsViaRenderer(entityAnimationPackets);
    }

    default void show() {
        LivingEntityDisplayPackets showPackets = getDisplayPackets();
        showPackets.setupDisplayPackets(this);
        sendPacketsViaRenderer(showPackets);
    }

    default void showWithAction(@NotNull Consumer<Player> sendWithAction) {
        LivingEntityDisplayPackets showPackets = getDisplayPackets();
        showPackets.setupDisplayPackets(this);
        sendPacketsViaRendererWithAction(showPackets, sendWithAction);
    }

}
