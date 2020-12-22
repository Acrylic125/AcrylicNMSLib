package com.acrylic.acrylicnms;

import com.acrylic.paginatedcollection.PaginatedArrayList;
import com.acrylic.universal.Universal;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.command.AbstractCommandBuilder;
import com.acrylic.universal.command.CommandBuilder;
import com.acrylic.universal.emtityanimator.NMSEntity;
import com.acrylic.universal.emtityanimator.NMSEntityAnimator;
import com.acrylic.universal.exceptions.IncompatibleVersion;
import com.acrylic.universal.text.ChatUtils;
import com.acrylic.universal.versionstore.exceptions.WrongVersionException;
import com.acrylic.version_1_8.EntityRegistry;
import com.acrylic.version_1_8.NMSBridge_1_8;
import com.acrylic.version_1_8.Version_1_8_Class;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class AcrylicNMS extends JavaPlugin {

    @Override
    public void onEnable() {
        load();
        registerTestCommand();
        UniversalNMS.getGlobalEntityMap().setup();
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
                    CommandSender sender = commandExecuted.getSender();
                    sender.sendMessage(ChatUtils.get(
                            "&e&lNMS Commands:\n" +
                                    "&f/acrylicnms entities"));
                }).arguments(new AbstractCommandBuilder[] {
                        Version_1_8_Class.getArgumentComponent(),
                        getEntitiesCMD()
        }).register();
    }

    private AbstractCommandBuilder getEntitiesCMD() {
        return CommandBuilder
                .create("entities")
                .aliases("entity")
                .handle(commandExecuted -> {
                    CommandSender sender = commandExecuted.getSender();
                    sender.sendMessage(ChatUtils.get(
                            "&e&lNMS Entities Commands:\n" +
                            "&f/acrylicnms entities list"));
                }).arguments(new AbstractCommandBuilder[] {
                        CommandBuilder.create("list")
                                .handle(commandExecuted -> {
                                    CommandSender sender = commandExecuted.getSender();
                                    int page = 1;
                                    try {
                                        if (commandExecuted.getArgs().length > 0)
                                            page = Integer.parseInt(commandExecuted.getArg(0));
                                    } catch (NumberFormatException ex) {
                                        sender.sendMessage(ChatUtils.get("&cPlease specify a valid number."));
                                    } finally {
                                        PaginatedArrayList<NMSEntityAnimator> nmsEntities = new PaginatedArrayList<>(10);
                                        nmsEntities.addAll(UniversalNMS.getGlobalEntityMap().getMap().values());
                                        sender.sendMessage(ChatUtils.get(
                                                "&e&l-== NMS ENTITY ANIMATORS [" + page + "/" + nmsEntities.getMaxPage() + "] ==-\n" +
                                                "&7There are &f" + nmsEntities.size() + "&7 registered NMS Entities."
                                        ));
                                        if (nmsEntities.size() > 0) {
                                            ArrayList<NMSEntityAnimator> nmsEntityArrayList = nmsEntities.getPageList(page);
                                            if (nmsEntityArrayList != null) {
                                                for (NMSEntityAnimator nmsEntity : nmsEntityArrayList) {
                                                    Entity entity = nmsEntity.getBukkitEntity();
                                                    sender.sendMessage(ChatUtils.get("&e&l" + entity.getEntityId() + "&r&f " + entity.getName() + " &r&7" + entity.getType()));
                                                }
                                            }
                                        }
                                    }
                        })

                });
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
