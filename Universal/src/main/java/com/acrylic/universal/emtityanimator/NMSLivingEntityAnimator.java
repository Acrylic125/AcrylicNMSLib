package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface NMSLivingEntityAnimator extends LivingEntityAnimator, NMSEntityAnimator {

    void damageEffect(@NotNull LivingEntity attacker);

    void damage(@NotNull LivingEntity attacker);

    void damage(@NotNull LivingEntity attacker, double amount);

    void damage(double damage);

    EntityEquipmentPackets getEquipmentPackets();

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
