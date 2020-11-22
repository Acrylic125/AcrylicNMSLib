package com.acrylic.universal.renderer;

import com.acrylic.universal.packets.PacketSender;
import org.bukkit.Location;

public class RangeRenderer implements PacketRenderer {

    private float range = 32;
    private Location location;

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void send(PacketSender packetSender) {
        if (location != null)
           packetSender.send(location, range);
    }

}
