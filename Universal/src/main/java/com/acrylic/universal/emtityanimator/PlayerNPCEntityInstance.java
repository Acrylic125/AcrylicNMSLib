package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.npc.PlayerNPCEntity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface PlayerNPCEntityInstance extends LivingEntityInstance, RespawnableInstance {

    int getInvulnerableTicks();

    void setInvulnerableTicks(int ticks);

    default void updateGravity() {
        PlayerNPCEntity playerNPCEntity = getAnimatior();
        if (playerNPCEntity.isUsingGravity() && !playerNPCEntity.isNoClip()) {
            playerNPCEntity.setVelocity(0, -0.5, 0);
        }
    }

    void attack(@NotNull LivingEntity entity);

    @NotNull
    @Override
    PlayerNPCEntity getAnimatior();

}
