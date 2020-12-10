package com.acrylic.version_1_8;

import com.acrylic.universal.Universal;
import com.acrylic.universal.animations.rotational.HandRotationAnimation;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.pathfinder.BlockExaminer;
import com.acrylic.universal.pathfinder.astar.AStarGenerator;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.entityanimator.NMSArmorStandAnimator;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.npc.PlayerNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class Version_1_8 {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .setTimerActive(true)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();

                    /**NMSArmorStandAnimator nmsArmorStandAnimator = new NMSArmorStandAnimator(sender.getLocation());
                    nmsArmorStandAnimator.gravity(false).asAnimator().setEquipment(new EntityEquipmentBuilder().setItemInHand(ItemBuilder.of(Material.DIAMOND_SWORD).build()));
                    nmsArmorStandAnimator.addToWorld();

                    Location location = sender.getLocation();
                    HandRotationAnimation handRotationAnimation = new HandRotationAnimation(nmsArmorStandAnimator);
                    nmsArmorStandAnimator.show();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            handRotationAnimation.teleport(location);
                        }
                    }.runTaskTimer(Universal.getPlugin(), 1, 1);
                    **/
                    AStarGenerator aStarGenerator = new AStarGenerator();
                    aStarGenerator
                            .setLookUpThreshold(30)
                            .setSearchDownAmount(5)
                            .setSearchUpAmount(2);

                    PlayerNPC npc = new PlayerNPC(sender.getLocation(), sender.getName());
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
                    npc.show();
                    npc.setSprinting(true);
                     new BukkitRunnable() {
                         Location[] locations = aStarGenerator.traverseAndCompute(npc.getLocation(), sender.getLocation());
                         final float s = 4f;
                        float i = 0;
                        @Override
                        public void run() {
                            if (i >= locations.length - 1) {
                                locations = aStarGenerator.traverseAndCompute(npc.getLocation(), sender.getLocation());
                                //npc.delete();
                                i = 0;
                                //cancel();
                            } else {
                                Location now = locations[(int) Math.floor(i)];
                                Location loc = npc.getLocation();
                                Vector dV = now.toVector().add(loc.toVector().multiply(-1));
                                loc.setDirection(dV.clone().normalize());
                                npc.teleport(loc);
                                if (dV.getY() > 0 || (BlockExaminer.isLiquid(loc.getBlock().getType()))) {
                                    dV.setY(dV.getY() + ((BlockExaminer.isLiquid(loc.getBlock().getType())) ? 0.1f : 1));
                                } else if (!npc.getNMSEntity().onGround) {
                                    dV.setY(dV.getY() - 1f);
                                }
                                npc.attack(sender);
                                dV.multiply(1f / s);
                                npc.getNMSEntity().noclip = now.getBlock().getType().equals(Material.WOODEN_DOOR);
                                npc.setVelocity(dV);
                                i += 1f / s;
                              //  Bukkit.broadcastMessage(npc.getNMSEntity().onGround + "");
                            }
                        }
                    }.runTaskTimer(Universal.getPlugin(), 1, 1);
                });
    }


}
