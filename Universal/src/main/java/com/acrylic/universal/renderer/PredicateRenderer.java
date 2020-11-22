package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.entity.Player;

import java.util.function.Predicate;

public class PredicateRenderer implements PacketRenderer {

    private Predicate<Player> condition;

    public Predicate<Player> getCondition() {
        return condition;
    }

    public void setCondition(Predicate<Player> condition) {
        this.condition = condition;
    }

    @Override
    public void send(PacketSender packetSender) {
        if (condition != null)
            packetSender.send(condition);
        else
            packetSender.sendAll();
    }
}
