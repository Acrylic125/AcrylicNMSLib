package com.acrylic.version_1_8.npc;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.npc.AbstractPlayerNPCEntity;
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

public class PlayerPlayerNPC extends NMSLivingEntityAnimator implements AbstractPlayerNPCEntity {

    private final EntityPlayer entityPlayer;

    public PlayerPlayerNPC(@NotNull Location location, @Nullable String name) {
        this(NMSBukkitConverter.getMCServer(), NMSBukkitConverter.convertToWorldServer(location.getWorld()), location, name);
    }

    private PlayerPlayerNPC(@NotNull MinecraftServer server, @NotNull WorldServer worldServer, @NotNull Location location, @Nullable String name) {
        super(new NPCPlayerDisplayPackets());
        entityPlayer = new EntityPlayer(server, worldServer, new GameProfile(UUID.randomUUID(), (name == null) ? null : ChatUtils.get(name)), new PlayerInteractManager(worldServer));
        new PlayerConnection(server, new NetworkManager(EnumProtocolDirection.CLIENTBOUND), entityPlayer);
        entityPlayer.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        UniversalNMS.getNpcHandler().addNPC(this);
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
        LivingEntityDisplayPackets packets = getDisplayPackets();
        if (packets instanceof NPCPlayerDisplayPackets) {
            EntityHeadRotationPacket headRotationPacket = ((NPCPlayerDisplayPackets) packets).getHeadRotationPacket();
            headRotationPacket.apply(entityPlayer);
            sendPacketsViaRenderer(headRotationPacket);
        }
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
    public void setSkin(@NotNull String texture, @NotNull String signature) {
        GameProfile gameProfile = entityPlayer.getProfile();
        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
    }
}
