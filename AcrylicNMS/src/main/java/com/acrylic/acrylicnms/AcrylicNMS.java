package com.acrylic.acrylicnms;

import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.AbstractCommandExecuted;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.version_1_8.Version_1_8;
import com.acrylic.version_1_8.nbt.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AcrylicNMS extends JavaPlugin {

    @Override
    public void onEnable() {
        registerTestCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerTestCommand() {
        CommandBuilder
                .create("acrylicnms")
                .handle(commandExecuted -> {
                    commandExecuted.getSender().sendMessage(ChatUtils.get("&bDeveloped by acrylic."));
                }).arguments(new AbstractCommandBuilder[] {
                Version_1_8.getArgumentComponent()
        }).register(this);
    }

}
