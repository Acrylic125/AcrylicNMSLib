package com.acrylic.acrylicnms;

import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.events.EventBuilder;
import com.acrylic.universal.npc.AbstractPlayerNPCEntity;
import com.acrylic.universal.protocollib.packetlistener.PacketListenerBuilder;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.Version_1_8;
import com.comphenix.protocol.PacketType;
import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class AcrylicNMS extends JavaPlugin {

    @Override
    public void onEnable() {
        registerTestCommand();
        EventBuilder.listen(NPCDamageEntityEvent.class).handle(npcDamageEntityEvent -> {
            Bukkit.broadcastMessage(npcDamageEntityEvent.getDamage() + " CCC");
        }).register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerTestCommand() {
        Universal.setPlugin(this);
        EventBuilder.listen(EntityDamageByEntityEvent.class).handle(entityDamageByEntityEvent -> {
            Bukkit.broadcastMessage("Test " + entityDamageByEntityEvent.getDamage());
        }).register();
        EventBuilder.listen(PlayerInteractAtEntityEvent.class).handle(entityDamageByEntityEvent -> {
            Bukkit.broadcastMessage("DIE " + entityDamageByEntityEvent.getPlayer());
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
