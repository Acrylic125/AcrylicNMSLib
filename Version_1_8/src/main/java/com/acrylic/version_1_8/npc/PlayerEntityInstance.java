package com.acrylic.version_1_8.npc;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.emtityanimator.PlayerNPCEntityInstance;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.npc.NPCTabRemoverEntry;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.entityanimator.LivingEntityInstance;
import com.acrylic.version_1_8.packets.*;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEntityInstance
        extends EntityPlayer
        implements PlayerNPCEntityInstance, LivingEntityInstance {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets = new NPCPlayerDisplayPackets();
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private final NPCPlayerInfoPacket removeFromTabPacket = new NPCPlayerInfoPacket();
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();
    private EntityEquipmentPackets equipmentPackets;

    private EntityAI<PlayerNPC> entityAI;
    private final PlayerNPC playerNPC;
    private long respawnCooldown = -1;
    private long respawnTime = 0;
    private boolean wasOnFire = false;

    public PlayerEntityInstance(PlayerNPC playerNPC, MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
        removeFromTabPacket.apply(this, NPCPlayerInfoPacket.EnumPlayerInfoAction.REMOVE_PLAYER);
        this.playerNPC = playerNPC;
        entityDestroyPacket.apply(getBukkitEntity());
        setupShowPackets();
        setupTermination();
    }

    @Override
    public void tickingEntity() {
        if (this.entityAI != null && playerNPC != null) {
            this.entityAI.update();
        }
    }

    public void setAi(@NotNull EntityAI<PlayerNPC> ai) {
        this.entityAI = ai;
    }

    @Override
    public int getMaxDamageCooldown() {
        return maxNoDamageTicks;
    }

    @Override
    public void setMaxDamageCooldown(int ticks) {
        maxNoDamageTicks = ticks;
    }

    @Override
    public boolean isNoClip() {
        return noclip;
    }

    @Override
    public void setNoClip(boolean b) {
        noclip = b;
    }

    @Override
    public void setEntityEquipmentPackets(@Nullable EntityEquipmentPackets entityEquipmentPackets) {
        this.equipmentPackets = entityEquipmentPackets;
    }

    @Override
    public EntityEquipmentPackets getEquipmentPackets() {
        return equipmentPackets;
    }

    @Override
    public EntityAnimationPackets getAnimationPackets() {
        return entityAnimationPackets;
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

    @Override
    public EntityPlayer getNMSEntity() {
        return this;
    }

    @Override
    public void setFireTicks(int ticks) {
        fireTicks = ticks;
    }

    @Override
    public int getFireTicks() {
        return fireTicks;
    }

    @Override
    public int getTicksLived() {
        return ticksLived;
    }

    @Override
    public int getInvulnerableTicks() {
        return invulnerableTicks;
    }

    @Override
    public void setInvulnerableTicks(int ticks) {
        invulnerableTicks = ticks;
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

    @NotNull
    @Override
    public TeleportPacket getTeleportPacket() {
        return teleportPacket;
    }

    @NotNull
    @Override
    public EntityDestroyPacket getDestroyPacket() {
        return entityDestroyPacket;
    }

    @NotNull
    @Override
    public LivingEntityDisplayPackets getDisplayPackets() {
        return displayPackets;
    }

    @Override
    public void t_() {
        super.t_();
        if (!handleRespawn()) {
            tickingEntity();
            updateGravity();
            render();
            handleFire();
        }
    }

    private void handleFire() {
        if (fireTicks > 0) {
            if (!wasOnFire)
                playerNPC.setOnFire(true);
            wasOnFire = true;
            fireTicks--;
            if (ticksLived % 20 == 0)
                damageEntity(DamageSource.BURN, 0.5f);
        } else {
            if (wasOnFire)
                playerNPC.setOnFire(false);
            wasOnFire = false;
        }
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


    @Override
    public void removeFromWorld() {
        world.removeEntity(this);
    }

    @Override
    public void addToWorld() {
        NetworkManager com = new NetworkManager(EnumProtocolDirection.CLIENTBOUND);
        PlayerConnection playerConnection = new PlayerConnection(NMSBukkitConverter.getMCServer(), com, this);
        com.a(playerConnection);
        getWorld().addEntity(this);
    }

    @Override
    public void setupShowPackets() {
        getAnimatior().getRenderer().setInitializationAction(player -> {
            com.acrylic.universal.packets.LivingEntityDisplayPackets displayPackets = getDisplayPackets();
            displayPackets.setupDisplayPackets(getAnimatior());
            UniversalNMS.getNpcHandler()
                    .getNPCTabRemoverEntries()
                    .add(new NPCTabRemoverEntry(player, removeFromTabPacket));
            displayPackets.send(player);
            Bukkit.broadcastMessage("Display!");
        });
    }
}
