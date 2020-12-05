package com.acrylic.version_1_8;

import com.acrylic.universal.Universal;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.players.Gamemode;
import com.acrylic.version_1_8.entity.EntityEquipmentBuilder;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.npc.PlayerPlayerNPC;
import com.acrylic.version_1_8.packets.EntityAnimationPackets;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class Version_1_8 {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();
                    PlayerPlayerNPC npc = new PlayerPlayerNPC(sender.getLocation(), sender.getName());
                    npc.setEquipment(new EntityEquipmentBuilder()
                            .setHelmet(ItemBuilder.of(Material.DIAMOND_HELMET).build())
                            .setChestplate(ItemBuilder.of(Material.DIAMOND_CHESTPLATE).build())
                            .setLeggings(ItemBuilder.of(Material.DIAMOND_LEGGINGS).build())
                            .setBoots(ItemBuilder.of(Material.DIAMOND_BOOTS).build())
                            .setItemInHand(ItemBuilder.of(Material.BOW).build())
                    );
                    npc.setSkin(sender.getName());
                    npc.addToWorld();
                    npc.setGamemode(Gamemode.SURVIVAL);
                    npc.nameVisible(false);
                    npc.setSleeping(true);
                    npc.show();
                    EntityAnimationPackets entityAnimationPackets = new EntityAnimationPackets();
                    entityAnimationPackets.apply(npc.getNMSEntity(), com.acrylic.universal.packets.EntityAnimationPackets.Animation.SLEEP);
                    entityAnimationPackets.sendAll();
                    Bukkit.broadcastMessage("");
                });
    }


}
