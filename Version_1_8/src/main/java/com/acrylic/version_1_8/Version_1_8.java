package com.acrylic.version_1_8;

import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.entityai.AttackerAI;
import com.acrylic.universal.entityai.quitterquirk.NoClipEntityPathQuitter;
import com.acrylic.universal.entityai.strategy.NPCAttackerStrategy;
import com.acrylic.universal.entityai.pathfinder.NPCEntityPathfinder;
import com.acrylic.universal.enums.Gamemode;
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
                    npc.show();
                    npc.setSprinting(true);
                    NPCEntityPathfinder<PlayerNPC> entityPathfinder = new NPCEntityPathfinder<>();
                    entityPathfinder.setEntityQuitter(new NoClipEntityPathQuitter<>());
                    npc.getEntityInstance().setAi(new AttackerAI<>(entityPathfinder, new NPCAttackerStrategy<>()));
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
