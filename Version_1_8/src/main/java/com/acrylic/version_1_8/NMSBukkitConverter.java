package com.acrylic.version_1_8;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class NMSBukkitConverter {

    public static ItemStack convertToBukkitItem(net.minecraft.server.v1_8_R3.ItemStack item) {
        return CraftItemStack.asBukkitCopy(item);
    }

    public static net.minecraft.server.v1_8_R3.ItemStack convertToNMSItem(ItemStack item) {
        return CraftItemStack.asNMSCopy(item);
    }

    public static Entity convertToBukkitEntity(CraftEntity entity) {
        return entity.getHandle().getBukkitEntity();
    }

    public static net.minecraft.server.v1_8_R3.Entity convertToNMSEntity(Entity entity) {
        return ((CraftEntity) entity).getHandle();
    }

    public static WorldServer convertToWorldServer(CraftWorld world) {
        return world.getHandle();
    }

    public static World convertToBukkitWorld(CraftWorld world) {
        return (World) convertToWorldServer(world);
    }

    public static CraftWorld convertToNMSWorld(World world) {
        return (CraftWorld) world;
    }

    public static TileEntity convertToNMSTileEntity(Block block) {
        return convertToNMSWorld(block.getWorld()).getTileEntityAt(block.getX(), block.getY(), block.getZ());
    }

    public static Player convertToBukkitPlayer(CraftPlayer player) {
        return player.getPlayer();
    }

    public static EntityPlayer convertToNMSPlayer(Player player) {
        return ((CraftPlayer) player).getHandle();
    }

}
