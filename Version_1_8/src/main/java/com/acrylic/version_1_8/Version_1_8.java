package com.acrylic.version_1_8;

import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.entityai.EntityAnimatorAI;
import com.acrylic.universal.entityai.NPCAttackerStrategy;
import com.acrylic.universal.entityai.NPCEntityPathfinder;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.pathfinder.astar.AStarGenerator;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.npc.PlayerNPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

                    Location test = sender.getLocation();
                    test.setYaw(0);
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
                    npc.show();
                    npc.setSprinting(true);
                    EntityAnimatorAI<PlayerNPC> entityAnimatorAI = new EntityAnimatorAI<>();
                    entityAnimatorAI.setPathfinder(new NPCEntityPathfinder<>());
                    entityAnimatorAI.setStrategy(new NPCAttackerStrategy<>());
                    npc.getEntityInstance().setAi(entityAnimatorAI);
                });
    }
    protected static byte getByteAngle(Vector vector) {
        return (byte) getCompressedAngle(getAngle(vector));
    }

    private static float getAngle(Vector vector) {
        return (float) Math.toDegrees(Math.atan2(vector.getZ(), vector.getX()) - 90f);
    }

    protected static int getCompressedAngle(float value) {
        return (int)(value * 256.0F / 360.0F);
    }

}
