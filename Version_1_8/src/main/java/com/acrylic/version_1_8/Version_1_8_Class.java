package com.acrylic.version_1_8;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.animations.rotational.HandRotationAnimation;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.emtityanimator.NMSArmorStandAnimator;
import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import com.acrylic.universal.emtityanimator.instances.NMSLivingEntityAnimator;
import com.acrylic.universal.packets.SoundPacket;
import com.acrylic.universal.renderer.PlayerRangeRenderer;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.TaskType;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
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
                    SoundPacket soundPacket = NMSBridge.getBridge().getPacketFactory().getSoundPacket();
                    soundPacket.applyBreakSound(test.getBlock());
                    soundPacket.send(sender);
                    /**Location to = test.clone().add(10, 0, 10);
                    sender.sendBlockChange(to.clone().add(0, 1, 0), Material.DIAMOND_BLOCK, (byte) 0);
                    for (Location computedLocation : PathGenerator.A_STAR_GENERATOR.traverseAndCompute(test, to)) {
                        sender.sendBlockChange(computedLocation, Material.GOLD_BLOCK, (byte) 0);
                    }**/
                    PlayerRangeRenderer range = new PlayerRangeRenderer();
                    NMSArmorStandAnimator nmsArmorStandAnimator = new NMSArmorStandAnimator(range, test);
                    nmsArmorStandAnimator.asAnimator().setEquipment(new EntityEquipmentBuilder()
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
                    HandRotationAnimation handRotationAnimation = new HandRotationAnimation(nmsArmorStandAnimator);
                    Scheduler.sync().runRepeatingIndexedTask(1, 1, 100).handle(task -> {
                        TaskType.IndexedRepeatingTask type = (TaskType.IndexedRepeatingTask) task.getTaskType();
                        type.increaseIndex();
                        if (type.hasReachedIndex()) {
                            task.cancel();
                            handRotationAnimation.delete();
                        } else {
                            nmsArmorStandAnimator.getEntityInstance().forceRender();
                            handRotationAnimation.teleport(test);
                        }
                    }).build();
                    /**PlayerNPC npc = new PlayerNPC(range, test, sender.getName());
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
                    ai.setPathfinder(new NPCEntityPathfinder<>(ai).setSpeed(0.6f));
                    ai.setFollowingStrategy(new NPCAttackerStrategy<>(ai));
                    npc.getEntityInstance().setAI(ai);**/
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
