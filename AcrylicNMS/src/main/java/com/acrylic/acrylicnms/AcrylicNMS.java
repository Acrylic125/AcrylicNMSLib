package com.acrylic.acrylicnms;

import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.exceptions.IncompatibleVersion;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.versionstore.exceptions.WrongVersionException;
import com.acrylic.version_1_8.EntityRegistry;
import com.acrylic.version_1_8.NMSBridge_1_8;
import com.acrylic.version_1_8.Version_1_8_Class;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AcrylicNMS extends JavaPlugin {

    @Override
    public void onEnable() {
        load();
        registerTestCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerTestCommand() {
        Universal.setPlugin(this);
        CommandBuilder
                .create("acrylicnms")
                .handle(commandExecuted -> {
                    commandExecuted.getSender().sendMessage(ChatUtils.get("&bDeveloped by acrylic."));
                }).arguments(new AbstractCommandBuilder[] {
                Version_1_8_Class.getArgumentComponent()
        }).register();
    }

    private void load() {
        short version = Universal.getVersionStore().getVersion();
        switch (version) {
            case 8:
                new NMSBridge_1_8();
                return;
            default:
                Bukkit.getPluginManager().disablePlugin(this);
                throw new IncompatibleVersion("Version 1." + version + " is not supported.");
        }
    }

}
