package com.acrylic.version_1_8.factory;

import com.acrylic.universal.emtityanimator.instances.NMSArmorStandAnimator;
import com.acrylic.universal.emtityanimator.instances.NMSGiantAnimator;
import com.acrylic.universal.emtityanimator.instances.PlayerNPC;
import com.acrylic.universal.entityinstances.instances.ArmorStandEntityInstance;
import com.acrylic.universal.entityinstances.instances.GiantEntityInstance;
import com.acrylic.universal.entityinstances.instances.PlayerNPCEntityInstance;
import com.acrylic.universal.factory.EntityFactory;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.NMSBukkitConverter;
import com.acrylic.version_1_8.npc.PlayerEntityInstance;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class EntityFactory_1_8 implements EntityFactory {

    @NotNull
    @Override
    public PlayerNPCEntityInstance getNewNPC(@NotNull PlayerNPC animator, @NotNull Location location, @Nullable String name) {
        MinecraftServer server = NMSBukkitConverter.getMCServer();
        WorldServer worldServer = NMSBukkitConverter.convertToWorldServer(location.getWorld());
        return new PlayerEntityInstance(location, animator, server, worldServer, new GameProfile(UUID.randomUUID(), (name == null) ? null : ChatUtils.get(name)), new PlayerInteractManager(worldServer));
    }

    @NotNull
    @Override
    public ArmorStandEntityInstance getNewArmorStandEntityInstance(@NotNull NMSArmorStandAnimator animator, @NotNull Location location) {
        return new com.acrylic.version_1_8.entityanimator.ArmorStandEntityInstance(animator, location);
    }

    @NotNull
    @Override
    public GiantEntityInstance getNewGiantEntityInstance(@NotNull NMSGiantAnimator animator, @NotNull Location location) {
        return new com.acrylic.version_1_8.entityanimator.GiantEntityInstance(animator, location);
    }
}
