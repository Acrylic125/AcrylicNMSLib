package com.acrylic.version_1_8;

import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.json.JSON;
import com.acrylic.universal.json.JSONComponent;
import com.acrylic.universal.shapes.spiral.MultiSpiral;
import com.acrylic.version_1_8.nbt.NBTItem;
import com.acrylic.version_1_8.particles.RGBParticles;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.entity.Player;

public final class Version_1_8 {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();
                    new JSON()
                            .append(JSONComponent.of("Hello").item(new NBTItem(sender.getItemInHand())))
                            .sendAll();
                });
    }

}
