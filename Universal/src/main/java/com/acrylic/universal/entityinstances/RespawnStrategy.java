package com.acrylic.universal.entityinstances;

import org.jetbrains.annotations.NotNull;

public class RespawnStrategy {

    private long respawnCooldown;
    private long respawnTime = 0;

    public RespawnStrategy() {
        this(10_000);
    }

    public RespawnStrategy(long respwnCooldown) {
        this.respawnCooldown = respwnCooldown;
    }

    public void respawn(@NotNull RespawnableInstance respawnableInstance) {
        respawnableInstance.respawn();
    }

    public boolean canRespawn() {
        return System.currentTimeMillis() > getRespawnTime();
    }

    public long getRespawnCooldown() {
        return respawnCooldown;
    }

    public void setRespawnCooldown(long respawnCooldown) {
        this.respawnCooldown = respawnCooldown;
    }

    public void resetRespawnTime() {
        this.respawnTime = respawnCooldown + System.currentTimeMillis();
    }

    public long getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(long respawnTime) {
        this.respawnTime = respawnTime;
    }

    public static RespawnStrategy getDefault() {
        return new RespawnStrategy();
    }

}
