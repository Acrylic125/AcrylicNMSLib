package com.acrylic.universal.entityinstances.instances;

import com.acrylic.universal.emtityanimator.instances.PlayerNPC;
import com.acrylic.universal.entityinstances.LivingEntityInstance;
import com.acrylic.universal.entityinstances.RespawnableInstance;
import com.acrylic.universal.enums.Gamemode;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface PlayerNPCEntityInstance extends LivingEntityInstance, RespawnableInstance {

    void setGamemode(@NotNull Gamemode gamemode);

    byte getDataWatcherEntityAnimation();

    void setDataWatcher(int index, byte bitMask);

    void setSkin(@NotNull String texture, @NotNull String signature);

    void setSneaking(boolean b);

    void setSprinting(boolean b);

    int getInvulnerableTicks();

    void setInvulnerableTicks(int ticks);

    default void updateGravity() {
        PlayerNPC playerNPCEntity = this.getAnimator();
        if (playerNPCEntity.isUsingGravity() && !playerNPCEntity.isNoClip()) {
            playerNPCEntity.setVelocity(0, -0.5, 0);
        }
    }

    void attack(@NotNull LivingEntity entity);

    @NotNull
    @Override
    PlayerNPC getAnimator();

}
