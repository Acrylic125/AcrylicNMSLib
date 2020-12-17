package com.acrylic.version_1_8;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.entityai.FollowerAI;
import com.acrylic.universal.entityai.pathfinder.NPCEntityPathfinder;
import com.acrylic.universal.entityai.quitterstrategy.SimpleEntityPathQuitter;
import com.acrylic.universal.entityai.strategy.NPCAttackerStrategy;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import com.acrylic.universal.pathfinder.PathFace;
import com.acrylic.universal.pathfinder.PathGenerator;
import com.acrylic.universal.pathfinder.newimp.AStarGeneratorB;
import com.acrylic.universal.renderer.PlayerRangeRenderer;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.npc.PlayerNPC;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

public final class Version_1_8_Class {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .setTimerActive(true)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();
                    Location test = sender.getLocation();
                    AStarGeneratorB aStarGeneratorB = new AStarGeneratorB();
                    for (Location location : aStarGeneratorB.traverseAndCompute(test, test.clone().add(30, 0, 30))) {
                        sender.sendBlockChange(location, Material.GOLD_BLOCK, (byte) 0);
                    }
                    /**PlayerRangeRenderer range = new PlayerRangeRenderer();
                    PlayerNPC npc = new PlayerNPC(range, test, sender.getName());
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
                    npc.setupShowPackets();
                    npc.setSprinting(true);
                    FollowerAI<PlayerNPC> ai = new FollowerAI<>(npc);
                    //ai.setEntityQuitter(new SimpleEntityPathQuitter<>(ai));
                    ai.setPathfinder(new NPCEntityPathfinder<>(ai).setSpeed(0.2f));
                    ai.setFollowingStrategy(new NPCAttackerStrategy<>(ai));
                    npc.getEntityInstance().setAi(ai);
                    npc.setInvulnerableTicks(0);
                    npc.setMaxDamageCooldown(1);**/
                });
    }

}
