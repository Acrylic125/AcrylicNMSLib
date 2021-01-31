package com.acrylic.version_1_8.npc;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.entityinstances.RespawnStrategy;
import com.acrylic.universal.entityinstances.instances.PlayerNPCEntityInstance;
import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.npc.NPCTabRemoverEntry;
import com.acrylic.universal.packets.EntityEquipmentPackets;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.entityanimator.LivingEntityInstance_1_8;
import com.acrylic.version_1_8.packets.*;
import com.acrylic.universal.emtityanimator.instances.PlayerNPC;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerEntityInstance
        extends EntityPlayer
        implements PlayerNPCEntityInstance, LivingEntityInstance_1_8 {

    private final EntityDestroyPacket entityDestroyPacket = new EntityDestroyPacket();
    private final LivingEntityDisplayPackets displayPackets = new NPCPlayerDisplayPackets();
    private final TeleportPacket teleportPacket = new TeleportPacket();
    private final NPCPlayerInfoPacket removeFromTabPacket = new NPCPlayerInfoPacket();
    private final EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();
    private EntityEquipmentPackets equipmentPackets;

    private EntityAI<PlayerNPC> entityAI;
    private final PlayerNPC playerNPC;
    private RespawnStrategy respawnStrategy;
    private boolean wasOnFire = false;

    public PlayerEntityInstance(Location location, PlayerNPC playerNPC, MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
        removeFromTabPacket.apply(this, NPCPlayerInfoPacket.EnumPlayerInfoAction.REMOVE_PLAYER);
        this.playerNPC = playerNPC;
        entityDestroyPacket.apply(getBukkitEntity());
        initialize(location);
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
    public void setGamemode(@NotNull Gamemode gamemode) {
        playerInteractManager.b(WorldSettings.EnumGamemode.valueOf(gamemode.getIdentifier()));
    }

    @Override
    public byte getDataWatcherEntityAnimation() {
        return getDataWatcher().getByte(0);
    }

    @Override
    public void setDataWatcher(int index, byte bitMask) {
        getDataWatcher().watch(index, bitMask);
    }

    @Override
    public void setSkin(@NotNull String texture, @NotNull String signature) {
        getProfile().getProperties().put("textures", new Property("textures", texture, signature));
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
    public PlayerNPC getAnimator() {
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
        if (super.locY < -64)
            damageEntity(DamageSource.GENERIC, 7f);
        if (this.joining) {
            this.joining = false;
        }

        this.playerInteractManager.a();
        --this.invulnerableTicks;
        if (this.noDamageTicks > 0)
            --this.noDamageTicks;

        Entity entity = this.C();
        if (entity != this) {
            this.setLocation(entity.locX, entity.locY, entity.locZ, entity.yaw, entity.pitch);
            this.server.getPlayerList().d(this);
        }
        //super.t_();
        if (isDead()) {
            checkRespawn(this);
        } else {
            tickingEntity();
            updateGravity();
            render();
            handleFire();
        }
    }

    @Override
    public void die(DamageSource damagesource) {
        handleDeath(this);
        EntityEquipment equipment = getBukkitEntity().getEquipment();
        ItemStack weapon = equipment.getItemInHand(),
                helmet = equipment.getHelmet(),
                chestplate = equipment.getChestplate(),
                leggings = equipment.getLeggings(),
                boots = equipment.getBoots();
        super.die(damagesource);
        setFireTicks(0);
        equipment.setItemInHand(weapon);
        equipment.setHelmet(helmet);
        equipment.setChestplate(chestplate);
        equipment.setLeggings(leggings);
        equipment.setBoots(boots);
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
    public void setRespawnStrategy(@NotNull RespawnStrategy respawnStrategy) {
        this.respawnStrategy = respawnStrategy;
    }

    @Nullable
    @Override
    public RespawnStrategy getRespawnStrategy() {
        return respawnStrategy;
    }

    @Override
    public void respawn() {
        dead = false;
        setHealth(getMaxHealth());
        this.getAnimator().getRenderer().unrenderAll();
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
        this.getAnimator().getRenderer().setInitializationAction(player -> {
            com.acrylic.universal.packets.LivingEntityDisplayPackets displayPackets = getDisplayPackets();
            displayPackets.setupDisplayPackets(this.getAnimator());
            UniversalNMS.getNpcHandler()
                    .getNPCTabRemoverEntries()
                    .add(new NPCTabRemoverEntry(player, removeFromTabPacket));
            displayPackets.send(player);
        });
    }
}
