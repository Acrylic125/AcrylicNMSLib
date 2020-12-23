package com.acrylic.universal.emtityanimator.instances;

import com.acrylic.universal.NMSBridge;
import com.acrylic.universal.UniversalNMS;
import com.acrylic.universal.entityanimations.equipment.AbstractEntityEquipmentBuilder;
import com.acrylic.universal.entityinstances.instances.PlayerNPCEntityInstance;
import com.acrylic.universal.enums.EntityAnimationEnum;
import com.acrylic.universal.enums.Gamemode;
import com.acrylic.universal.npc.NPCDisplayPackets;
import com.acrylic.universal.npc.SimpleNPCSkin;
import com.acrylic.universal.packets.EntityHeadRotationPacket;
import com.acrylic.universal.renderer.InitializerLocationalRenderer;
import com.acrylic.universal.threads.Scheduler;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerNPC
        extends NMSLivingEntityAnimator {

    private final PlayerNPCEntityInstance entityPlayer;
    private boolean gravity = true;

    public PlayerNPC(@NotNull InitializerLocationalRenderer initializerLocationalRenderer, @NotNull Location location, @Nullable String name) {
        super(initializerLocationalRenderer);
        entityPlayer = NMSBridge.getBridge().getEntityFactory().getNewNPC(this, location, name);
        UniversalNMS.getNpcHandler().addNPC(this);
    }

    @Override
    public void setEquipment(AbstractEntityEquipmentBuilder entityEquipment) {
        super.setEquipment(entityEquipment);
    }

    @Override
    public float getHeight() {
        return 2;
    }

    @Override
    public float getHologramHeight() {
        return 0.2f;
    }

    @Override
    public void teleport(@NotNull Location location) {
        super.teleport(location);
        updateHeadPose();
    }

    @Override
    public Object getNMSEntity() {
        return entityPlayer;
    }

    @Override
    public Player getBukkitEntity() {
        return (Player) entityPlayer.getBukkitEntity();
    }

    public void setGravity(boolean b) {
        this.gravity = b;
    }

    public boolean isUsingGravity() {
        return gravity;
    }

    public void updateHeadPose() {
        updateHeadPose(entityPlayer.getYaw());
    }

    public void updateHeadPose(float angle) {
        NPCDisplayPackets packets = (NPCDisplayPackets) entityPlayer.getDisplayPackets();
        EntityHeadRotationPacket headRotationPacket = packets.getHeadRotationPacket();
        headRotationPacket.apply(getBukkitEntity(), angle);
        headRotationPacket.send(getRenderer());
    }

    public void attack(@NotNull LivingEntity victim) {
        entityPlayer.attack(victim);
        animate(EntityAnimationEnum.ARM_SWING);
    }

    public void setGamemode(@NotNull Gamemode gamemode) {
        entityPlayer.setGamemode(gamemode);
    }

    public void setSneaking(boolean flag) {
        entityPlayer.setSneaking(flag);
    }

    public void setSprinting(boolean flag) {
        entityPlayer.setSprinting(flag);
    }

    public void setVisible(boolean flag) {
        entityPlayer.setVisible(flag);
    }

    public void setDataWatcher(int index, byte bitMask) {
        entityPlayer.setDataWatcher(index, bitMask);
    }

    public byte getDataWatcherEntityAnimation() {
        return entityPlayer.getDataWatcherEntityAnimation();
    }

    public void setSkin(@NotNull String texture, @NotNull String signature) {
        entityPlayer.setSkin(texture, signature);
    }

    public void setSkin(@NotNull String name) {
        SimpleNPCSkin skin = UniversalNMS.getSkinMap().getSkin(name);
        if (skin != null)
            setSkin(skin.getTexture(), skin.getSignature());
        else {
            Scheduler.async()
                    .handle(task -> {
                        SimpleNPCSkin simpleNPCSkin = UniversalNMS.getSkinMap().getAndAddIfNotExist(name);
                        setSkin(simpleNPCSkin.getTexture(), simpleNPCSkin.getSignature());
                    })
                    .build();
        }
    }

    public void setSkin(@NotNull SimpleNPCSkin simpleNPCSkin) {
        setSkin(simpleNPCSkin.getTexture(), simpleNPCSkin.getSignature());
    }

    /**@Override
    public void addToWorld(@NotNull WorldServer worldServer) {
        NetworkManager com = new NetworkManager(EnumProtocolDirection.CLIENTBOUND);
        PlayerConnection playerConnection = new PlayerConnection(NMSBukkitConverter.getMCServer(), com, entityPlayer);
        com.a(playerConnection);
        /** PlayerList playerList = ((CraftServer) Bukkit.getServer()).getHandle();
        * playerList.players.add(entityPlayer);
        * playerList.a(entityPlayer, worldServer);
        super.addToWorld();
    }**/

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public PlayerNPCEntityInstance getEntityInstance() {
        return entityPlayer;
    }

    public void setBowFoodUse(boolean flag) {
        setDataWatcherEntityAnimation((byte) 16, flag);
    }

    public void setOnFire(boolean flag) {
        setDataWatcherEntityAnimation((byte) 0x01, flag);
    }

    public void setDataWatcherEntityAnimation(byte bitMask, boolean flag) {
        byte dataWatcherEntityAnimation = getDataWatcherEntityAnimation();
        setDataWatcher(0, (byte) ((flag) ? (dataWatcherEntityAnimation | bitMask) : (dataWatcherEntityAnimation & ~bitMask)));
    }

}
