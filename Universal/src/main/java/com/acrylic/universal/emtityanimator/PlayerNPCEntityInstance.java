package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.npc.PlayerNPCEntity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface PlayerNPCEntityInstance extends LivingEntityInstance {

    void attack(@NotNull LivingEntity entity);

    @NotNull
    @Override
    PlayerNPCEntity getAnimatior();

}
