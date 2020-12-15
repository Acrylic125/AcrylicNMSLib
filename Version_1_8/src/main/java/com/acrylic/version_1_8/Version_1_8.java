package com.acrylic.version_1_8;

import com.acrylic.universal.Universal;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.pathfinder.NPCEntityPathfinder;
import com.acrylic.universal.entityai.quitterstrategy.SimpleEntityPathQuitter;
import com.acrylic.universal.entityai.strategy.NPCAttackerStrategy;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.renderer.PlayerRangeRenderer;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.npc.PlayerNPC;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class Version_1_8 {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .setTimerActive(true)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();

                    Location test = sender.getLocation();
                    PlayerNPC npc = new PlayerNPC(test, sender.getName());
                    npc.setEquipment(new EntityEquipmentBuilder()
                            .setHelmet(ItemBuilder.of(Material.DIAMOND_HELMET).build())
                            .setChestplate(ItemBuilder.of(Material.DIAMOND_CHESTPLATE).build())
                            .setLeggings(ItemBuilder.of(Material.DIAMOND_LEGGINGS).build())
                            .setBoots(ItemBuilder.of(Material.DIAMOND_BOOTS).build())
                            .setItemInHand(ItemBuilder.of(Material.DIAMOND_SWORD).build())
                    );
                    npc.setSkin(sender.getName());
                    npc.addToWorld();
                    npc.setGamemode(Gamemode.SURVIVAL);
                    PlayerRangeRenderer range = new PlayerRangeRenderer();
                    npc.setRenderer(range);
                    npc.setupShowPackets();
                    npc.setSprinting(true);
                    FollowerAI<PlayerNPC> ai = new FollowerAI<>(npc);
                    ai.setEntityQuitter(new SimpleEntityPathQuitter<>(ai));
                    ai.setPathfinder(new NPCEntityPathfinder<>(ai));
                    ai.setFollowingStrategy(new NPCAttackerStrategy<>(ai));
                    npc.getEntityInstance().setAi(ai);
                    npc.setInvulnerableTicks(0);
                    npc.setMaxDamageCooldown(1);
                    MinecraftServer
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            npc.getRenderer().setLocation(npc.getLocation());
                            npc.getRenderer().check();
                        }
                    }.runTaskTimerAsynchronously(Universal.getPlugin(), 10 ,20);
                });
    }

}
