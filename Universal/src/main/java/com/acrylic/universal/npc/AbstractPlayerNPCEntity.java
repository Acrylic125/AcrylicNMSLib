package com.acrylic.universal.npc;

import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.enums.Gamemode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public interface AbstractPlayerNPCEntity extends NMSLivingEntityAnimator {

    void attack(@NotNull LivingEntity victim);

    void setGamemode(@NotNull Gamemode gamemode);

    void setSneaking(boolean flag);

    void setSprinting(boolean flag);

    void setVisible(boolean flag);

    default void setBowFoodUse(boolean flag) {
        setDataWatcherEntityAnimation((byte) 16, flag);
    }

    default void setOnFire(boolean flag) {
        setDataWatcherEntityAnimation((byte) 0x01, flag);
    }

    void setDataWatcher(int index, byte bitMask);

    byte getDataWatcherEntityAnimation();

    default void setDataWatcherEntityAnimation(byte bitMask, boolean flag) {
        byte dataWatcherEntityAnimation = getDataWatcherEntityAnimation();
        setDataWatcher(0, (byte) ((flag) ? (dataWatcherEntityAnimation | bitMask) : (dataWatcherEntityAnimation & ~bitMask)));
    }

    void setSkin(@NotNull String texture, @NotNull String signature);

    default void setSkin(@NotNull String name) {
        new BukkitRunnable() {
            @Override
            public void run() {
                SimpleNPCSkin simpleNPCSkin = UniversalNMS.getSkinMap().getAndAddIfNotExist(name);
                setSkin(simpleNPCSkin.getTexture(), simpleNPCSkin.getSignature());
            }
        }.runTaskAsynchronously(Universal.getPlugin());
    }

    default void setSkin(@NotNull SimpleNPCSkin simpleNPCSkin) {
        setSkin(simpleNPCSkin.getTexture(), simpleNPCSkin.getSignature());
    }

}
