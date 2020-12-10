package com.acrylic.version_1_8.npc;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.emtityanimator.PlayerNPCEntityInstance;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.entityanimator.LivingEntityInstance;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEntityInstance extends EntityPlayer
        implements PlayerNPCEntityInstance, LivingEntityInstance {

    private EntityAI<PlayerNPC> entityAI;
    private PlayerNPC playerNPC;
    private long respawnCooldown = -1;
    private long respawnTime = 0;

    public PlayerEntityInstance(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
    }

    @Override
    public void tickingEntity() {
        if (this.entityAI != null && playerNPC != null) {
            this.entityAI.update(playerNPC);
        }
    }

    public void setAi(@NotNull EntityAI<PlayerNPC> ai) {
        this.entityAI = ai;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setAI(@Nullable EntityAI<?> ai) {
        if (ai == null) {
            this.entityAI = null;
            return;
        }
        try {
            this.entityAI = (EntityAI<PlayerNPC>) ai;
        } catch (ClassCastException ex) {
            throw new IllegalStateException("THe AI specified must be of " + PlayerNPC.class.getName() + ".");
        }
    }

    @Nullable
    @Override
    public EntityAI<?> getAI() {
        return this.entityAI;
    }

    public void setAnimator(@Nullable PlayerNPC animator) {
        this.playerNPC = animator;
    }

    @Override
    public void setAnimator(@Nullable NMSLivingEntityAnimator animator) {
        if (animator == null) {
            this.playerNPC = null;
            return;
        }
        if (animator instanceof PlayerNPC)
           this.playerNPC = (PlayerNPC) animator;
        else
            throw new IllegalStateException("THe Animator specified must be of " + PlayerNPC.class.getName() + ".");
    }


    @Override
    public EntityPlayer getNMSEntity() {
        return this;
    }

    @Override
    public void attack(@NotNull LivingEntity entity) {
        attack(NMSBukkitConverter.convertToNMSEntity(entity));
    }

    @NotNull
    @Override
    public PlayerNPC getAnimatior() {
        return playerNPC;
    }

    @Override
    public void t_() {
        super.t_();
        if (!handleRespawn())
            tickingEntity();
    }

    @Override
    public void setDead(boolean b) {
        dead = b;
    }

    @Override
    public boolean isDead() {
        return dead || getHealth() <= 0;
    }

    @Override
    public void setRespawnCooldown(long cooldown) {
        respawnCooldown = cooldown;
    }

    @Override
    public long getRespawnCooldown() {
        return respawnCooldown;
    }

    @Override
    public void setRespawnTime(long respawnTime) {
        this.respawnTime = respawnTime;
    }

    @Override
    public long getRespawnTime() {
        return respawnTime;
    }

    @Override
    public void respawn() {
        setHealth(getMaxHealth());
    }

    @Override
    public void delete() {
        if (playerNPC != null)
            playerNPC.delete();
    }



}
