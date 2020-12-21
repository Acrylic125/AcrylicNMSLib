package com.acrylic.version_1_8.npc;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.npc.PlayerNPCEntity;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.entityanimator.NMSLivingEntityAnimator;
import com.acrylic.version_1_8.packets.EntityHeadRotationPacket;
import com.acrylic.version_1_8.packets.LivingEntityDisplayPackets;
import com.acrylic.version_1_8.packets.NPCPlayerDisplayPackets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PlayerNPC
        extends NMSLivingEntityAnimator
        implements PlayerNPCEntity {

    private final PlayerEntityInstance entityPlayer;
    private boolean gravity = true;

    public PlayerNPC(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull Location location, @Nullable String name) {
        this(initializerLocationalRenderer, NMSBukkitConverter.getMCServer(), NMSBukkitConverter.convertToWorldServer(location.getWorld()), location, name);
    }

    private PlayerNPC(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull MinecraftServer server, @NotNull WorldServer worldServer, @NotNull Location location, @Nullable String name) {
        super(initializerLocationalRenderer);
        entityPlayer = new PlayerEntityInstance(this, server, worldServer, new GameProfile(UUID.randomUUID(), (name == null) ? null : ChatUtils.get(name)), new PlayerInteractManager(worldServer));
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        UniversalNMS.getNpcHandler().addNPC(this);
    }

    @Override
    public void setEquipment(AbstractEntityEquipmentBuilder entityEquipment) {
        super.setEquipment(entityEquipment);
    }

    @Override
    public float getHeight() {
        return 2;
    }

    @Override
    public float getHologramHeight() {
        return 0.2f;
    }

    @Override
    public void teleport(@NotNull Location location) {
        super.teleport(location);
        updateHeadPose();
    }

    @Override
    public EntityLiving getNMSEntity() {
        return entityPlayer;
    }

    @Override
    public LivingEntity getBukkitEntity() {
        return entityPlayer.getBukkitEntity();
    }

    @Override
    public void setGravity(boolean b) {
        this.gravity = b;
    }

    @Override
    public boolean isUsingGravity() {
        return gravity;
    }

    @Override
    public void updateHeadPose() {
        updateHeadPose(entityPlayer.yaw);
    }

    @Override
    public void updateHeadPose(float angle) {
        LivingEntityDisplayPackets packets = entityPlayer.getDisplayPackets();
        if (packets instanceof NPCPlayerDisplayPackets) {
            EntityHeadRotationPacket headRotationPacket = ((NPCPlayerDisplayPackets) packets).getHeadRotationPacket();
            headRotationPacket.apply(entityPlayer, angle);
            headRotationPacket.send(getRenderer());
        }
    }

    @Override
    public void attack(@NotNull LivingEntity victim) {
        entityPlayer.attack(NMSBukkitConverter.convertToNMSEntity(victim));
        animate(EntityAnimationEnum.ARM_SWING);
    }

    @Override
    public void setGamemode(@NotNull Gamemode gamemode) {
        entityPlayer.playerInteractManager.b(WorldSettings.EnumGamemode.valueOf(gamemode.getIdentifier()));
    }

    @Override
    public void setSneaking(boolean flag) {
        entityPlayer.setSneaking(flag);
    }

    @Override
    public void setSprinting(boolean flag) {
        entityPlayer.setSprinting(flag);
    }

    @Override
    public void setVisible(boolean flag) {
        entityPlayer.setInvisible(!flag);
    }

    @Override
    public void setDataWatcher(int index, byte bitMask) {
        entityPlayer.getDataWatcher().watch(index, bitMask);
    }

    @Override
    public byte getDataWatcherEntityAnimation() {
        return entityPlayer.getDataWatcher().getByte(0);
    }

    @Override
    public void setSkin(@NotNull String texture, @NotNull String signature) {
        GameProfile gameProfile = entityPlayer.getProfile();
        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
    }

    /**@Override
    public void addToWorld(@NotNull WorldServer worldServer) {
        NetworkManager com = new NetworkManager(EnumProtocolDirection.CLIENTBOUND);
        PlayerConnection playerConnection = new PlayerConnection(NMSBukkitConverter.getMCServer(), com, entityPlayer);
        com.a(playerConnection);
        /** PlayerList playerList = ((CraftServer) Bukkit.getServer()).getHandle();
        * playerList.players.add(entityPlayer);
        * playerList.a(entityPlayer, worldServer);
        super.addToWorld();
    }**/

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public PlayerEntityInstance getEntityInstance() {
        return entityPlayer;
    }

}
