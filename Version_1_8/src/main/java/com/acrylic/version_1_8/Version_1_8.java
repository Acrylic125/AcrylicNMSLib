package com.acrylic.version_1_8;

import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import org.bukkit.entity.Player;

public final class Version_1_8 {

    public static CommandBuilder getArgumentComponent() {
        return CommandBuilder
                .create("1.8")
                .filter(AbstractCommandExecuted::isPlayer)
                .handle(commandExecuted -> {
                    Player sender = (Player) commandExecuted.getSender();

                });
    }

}
