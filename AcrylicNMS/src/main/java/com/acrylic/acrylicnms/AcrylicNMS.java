package com.acrylic.acrylicnms;

import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.protocollib.packetlistener.PacketListenerBuilder;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.Version_1_8;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AcrylicNMS extends JavaPlugin {

    @Override
    public void onEnable() {
        registerTestCommand();
        PacketListenerBuilder
                .listen(PacketType.Play.Client.USE_ENTITY)
                .handleReceivingPacket(packetEvent -> {
                    Entity readEntity = packetEvent.getPacket().getEntityModifier(packetEvent).read(0);
                    if (readEntity instanceof LivingEntity) {
                        ((LivingEntity) readEntity).damage(0, packetEvent.getPlayer());
                    }
                    Bukkit.broadcastMessage(readEntity + "");
                })
                .build();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerTestCommand() {
        Universal.setPlugin(this);
        EventBuilder.listen(EntityDamageByEntityEvent.class).handle(entityDamageByEntityEvent -> {
            Bukkit.broadcastMessage("Yes!");
        }).register();
        CommandBuilder
                .create("acrylicnms")
                .handle(commandExecuted -> {
                    commandExecuted.getSender().sendMessage(ChatUtils.get("&bDeveloped by acrylic."));
                }).arguments(new AbstractCommandBuilder[] {
                Version_1_8.getArgumentComponent()
        }).register();
    }

}
