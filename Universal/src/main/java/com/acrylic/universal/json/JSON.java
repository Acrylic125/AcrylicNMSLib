package com.acrylic.universal.json;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Example:
 *
 * new JSON().append(JSONComponent.of("&eHello LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOL").item(p.getItemInHand()))
 *                 .append(JSONComponent.of(" LOL").suggestCommand("/kill ").subText("&7Click to kill."))
 *                 .append(JSONComponent.of(" Idiot"))
 *                 .append(JSONComponent.of(" &6&lMe! "))
 *                 .send(p);
 */
public final class JSON implements AbstractJSON {

    private final TextComponent textComponent;

    private JSON(TextComponent textComponent) {
        this.textComponent = (TextComponent) textComponent.duplicate();
    }

    public JSON(AbstractJSONComponent component) {
        textComponent = new TextComponent(component.getTextComponent());
    }

    public JSON() {
        textComponent = new TextComponent("");
    }

    @Override
    public AbstractJSON append(AbstractJSONComponent component) {
        textComponent.addExtra(component.getTextComponent());
        return this;
    }

    @Override
    public void sendAll() {
        for (Player player : Bukkit.getOnlinePlayers())
            send(player);
    }

    @Override
    public void send(@NotNull Player player) {
        player.spigot().sendMessage(textComponent);
    }

    @Override
    public void send(Player... players) {
        for (Player player : players)
            send(player);
    }

    @Override
    public void send(@NotNull Location location, float radius) {
        float d = radius * radius;
        send(player -> player.getLocation().distanceSquared(location) <= d);
    }

    @Override
    public void send(@NotNull Collection<? extends Player> players) {
        for (Player player : players)
            send(player);
    }

    @Override
    public void send(@NotNull Predicate<Player> condition) {
        for (Player player : Bukkit.getOnlinePlayers())
            if (condition.test(player))
                send(player);
    }

    @Override
    public String toJson() {
        return toString();
    }

    @Override
    public AbstractJSON duplicate() {
        return new JSON(textComponent);
    }

    @Override
    public String toString() {
        return ComponentSerializer.toString(textComponent);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
