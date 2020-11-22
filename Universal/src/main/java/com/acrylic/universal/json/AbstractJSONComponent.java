package com.acrylic.universal.json;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public interface AbstractJSONComponent {

    AbstractJSONComponent subText(String... text);

    AbstractJSONComponent command(String text);

    AbstractJSONComponent suggestCommand(String text);

    AbstractJSONComponent item(ItemStack item);

    AbstractJSONComponent link(String text);

    TextComponent getTextComponent();

}
