package com.acrylic.version_1_8;

import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.emtityanimator.*;
import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.pathfinder.NPCEntityPathfinder;
import com.acrylic.universal.entityai.strategy.NPCAttackerStrategy;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.renderer.PlayerRangeRenderer;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.npc.PlayerNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public final class Version_1_8_Class {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .setTimerActive(true)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();
                    Location test = sender.getLocation();
                    /**Location to = test.clone().add(10, 0, 10);
                    sender.sendBlockChange(to.clone().add(0, 1, 0), Material.DIAMOND_BLOCK, (byte) 0);
                    for (Location computedLocation : PathGenerator.A_STAR_GENERATOR.traverseAndCompute(test, to)) {
                        sender.sendBlockChange(computedLocation, Material.GOLD_BLOCK, (byte) 0);
                    }**/
                    PlayerRangeRenderer range = new PlayerRangeRenderer();
                    PlayerNPC npc = new PlayerNPC(range, test, sender.getName());
                    npc.setEquipment(new EntityEquipmentBuilder()
                            .setHelmet(ItemBuilder.of(Material.DIAMOND_HELMET).build())
                            .setChestplate(ItemBuilder.of(Material.DIAMOND_CHESTPLATE).build())
                            .setLeggings(ItemBuilder.of(Material.DIAMOND_LEGGINGS).build())
                            .setBoots(ItemBuilder.of(Material.DIAMOND_BOOTS).build())
                            .setItemInHand(ItemBuilder.of(Material.DIAMOND_SWORD)
                                    .enchant(Enchantment.DAMAGE_ALL, 5)
                                    .enchant(Enchantment.FIRE_ASPECT, 2)
                                    .enchant(Enchantment.KNOCKBACK, 2)
                                    .build())
                    );
                    npc.setSkin(sender.getName());
                    npc.addToWorld();
                    npc.setGamemode(Gamemode.SURVIVAL);
                    FollowerAI<PlayerNPC> ai = new FollowerAI<>(npc);
                    //ai.setEntityQuitter(new SimpleEntityPathQuitter<>(ai));
                    ai.setPathfinder(new NPCEntityPathfinder<>(ai).setSpeed(0.6f));
                    ai.setFollowingStrategy(new NPCAttackerStrategy<>(ai));
                    npc.getEntityInstance().setAi(ai);
                }).arguments(new AbstractCommandBuilder[] {
                        CommandBuilder.create("killall")
                                .filter(AbstractCommandExecuted::isPlayer)
                        .handle(commandExecuted -> {
                            Player sender = (Player) commandExecuted.getSender();
                            for (NMSEntityAnimator value : UniversalNMS.getGlobalEntityMap().getMap().values()) {
                                if (value instanceof NMSLivingEntityAnimator) {
                                    ((LivingEntity) value.getBukkitEntity()).setHealth(0);
                                }
                            }
                        })
                });
    }

}
