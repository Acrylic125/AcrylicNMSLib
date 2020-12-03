package com.acrylic.universal.npc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
import java.util.logging.Level;

public interface NPCSkin {

    void setTexture(@NotNull String texture);

    @NotNull
    String getTexture();

    void setSignature(@NotNull String signature);

    @NotNull
    String getSignature();

    default void queryFromName(@NotNull String name) {
        queryFromOfflinePlayer(Bukkit.getOfflinePlayer(name));
    }

    default void queryFromOfflinePlayer(@NotNull OfflinePlayer player) {
        queryFromUUID(player.getUniqueId());
    }

    default void queryFromPlayer(@NotNull Player player) {
        queryFromUUID(player.getUniqueId());
    }

    default void queryFromUUID(@NotNull UUID uuid) {
        queryFromUUID(uuid.toString());
    }

    default void queryFromUUID(@NotNull String uuid) {
        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" +
                     uuid + "?unsigned=false");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonObject property = new JsonParser().parse(reader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            JsonElement value = property.get("value");
            JsonElement signature = property.get("signature");
            if (value != null && signature != null) {
                setTexture(value.getAsString());
                setSignature(signature.getAsString());
            }
            reader.close();
            Bukkit.getLogger().log(Level.ALL, "Skin querying " + uuid + " succeeded!");
        } catch (IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().log(Level.ALL, "Skin querying " + uuid + " failed!");
        }
    }

}
