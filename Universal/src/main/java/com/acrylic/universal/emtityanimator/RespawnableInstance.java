package com.acrylic.universal.emtityanimator;

import com.acrylic.universal.interfaces.Deletable;
import org.bukkit.Bukkit;

public interface RespawnableInstance extends Deletable {

    void setDead(boolean b);

    boolean isDead();

    default boolean shouldRespawn() {
        return getRespawnCooldown() >= 0;
    }

    default boolean canRespawn() {
        return System.currentTimeMillis() > getRespawnTime();
    }

    /**
     * @return Should handle respawning.
     */
    default boolean handleRespawn() {
        if (isDead()) {
            if (!shouldRespawn())
                delete();
            else if (canRespawn()) {
                setDead(false);
                setRespawnTime(System.currentTimeMillis() + getRespawnCooldown());
                respawn();
            }
            return true;
        }
        return false;
    }

    void setRespawnCooldown(long cooldown);

    /**
     * @return Returns -1 if the entity should not respawn.
     */
    long getRespawnCooldown();

    void setRespawnTime(long respawnTime);

    long getRespawnTime();

    void respawn();

}
