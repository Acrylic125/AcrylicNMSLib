package com.acrylic.version_1_8;

import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.shapes.Circle;
import com.acrylic.universal.shapes.spiral.MultiSpiral;
import com.acrylic.version_1_8.items.ItemBuilder;
import com.acrylic.version_1_8.particles.ItemParticles;
import com.acrylic.version_1_8.particles.Particles;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class Version_1_8 {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();
                    ItemParticles particles = (ItemParticles) new ItemParticles()
                            .particleType(EnumParticle.ITEM_CRACK)
                            .amount(1)
                            .speed(0);
                    particles.item(ItemBuilder.of(Material.WOOL).damage((short) 14).build());
                    MultiSpiral multiSpiral = new MultiSpiral(0.3f, 10);
                    multiSpiral.setAngleOffset(25);
                    multiSpiral.setRadiusIncrement(0.2f);
                    multiSpiral.setOrientation(sender);
                    multiSpiral.invokeAction(100, sender.getLocation().add(0,1.5,0).add(sender.getLocation().getDirection().multiply(1.5f)), (integer, location) -> {
                        particles.location(location).build();
                        particles.sendAll();
                    });
                });
    }

}
