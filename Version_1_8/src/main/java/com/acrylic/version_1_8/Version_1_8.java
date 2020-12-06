package com.acrylic.version_1_8;

import com.acrylic.universal.Universal;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.pathfinder.SimpleBlockExaminer;
import com.acrylic.universal.pathfinder.astar.AStarGenerator;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
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
                    Location[] locations = aStarGenerator.traverseAndCompute(sender.getLocation(), sender.getLocation().add(30, 0, 30));
                    new BukkitRunnable() {
                        float s = 5f;
                        int i = 0;
                        int h = 0;
                        int b4i2 = 0;
                        @Override
                        public void run() {
                            int i2 = (int) Math.floor(i / s);
                            if (i2 >= locations.length - 1) {
                                cancel();
                            } else {
                                if (i2 > 0) {
                                    // sender.sendBlockChange(locations[i2], Material.GOLD_BLOCK, (byte) 0);
                                    if (b4i2 != i2) {
                                        b4i2 = i2;
                                        h = 0;
                                    }
                                    Location before = locations[i2 - 1];
                                    Location now = locations[i2];
                                    h++;
                                    Vector dV = now.toVector().add(before.toVector().multiply(-1));
                                    if (dV.getY() > 0) {
                                        npc.setVelocity(0, 0.5, 0);
                                    } else {
                                        npc.setVelocity(0, -0.5, 0);
                                    }
                                    dV.multiply(h / s);
                                    npc.teleport(before.clone().add(dV));
                                }
                                i++;
                            }
                        }
                    }.runTaskTimer(Universal.getPlugin(), 1, 1);

                });
    }


}
