package com.acrylic.version_1_8.particles;

import com.acrylic.version_1_8.NMSBukkitConverter;
import net.minecraft.server.v1_8_R3.Item;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.inventory.ItemStack;

public class ItemParticles
        extends Particles
        implements com.acrylic.universal.particles.ItemParticles {

    private int itemId = 0;
    private int data = 0;
    private boolean isValidItem = false;

    @Override
    public void item(ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack itemStack = NMSBukkitConverter.convertToNMSItem(item);
        if (itemStack != null) {
            itemId = Item.getId(itemStack.getItem());
            data = itemStack.getData();
            isValidItem = true;
        } else
            isValidItem = false;
    }

    @Override
    public boolean isValidItem() {
        return isValidItem;
    }

    @Override
    public void build() {
        checkConditions();
        this.packet = (offset == null) ?
                new PacketPlayOutWorldParticles(particleType,
                        this.longDistance, this.location[0], this.location[1], this.location[2],
                        0, 0, 0,
                        this.speed, this.amount,
                        itemId, data
                )
                :
                new PacketPlayOutWorldParticles(particleType,
                        this.longDistance, this.location[0], this.location[1], this.location[2],
                        offset[0], offset[1], offset[2],
                        this.speed, this.amount,
                        itemId, data
                );
    }
}
