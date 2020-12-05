package com.acrylic.universal.packets;

public interface AnimationPackets extends PacketSender {

    enum Animation {
        ARM_SWING,
        ARM_SWING_OFFHAND,
        CRIT,
        EAT_FOOD,
        HURT,
        MAGIC_CRIT,
        SIT,
        SLEEP,
        SNEAK,
        START_ELYTRA,
        START_USE_MAINHAND_ITEM,
        START_USE_OFFHAND_ITEM,
        STOP_SITTING,
        STOP_SLEEPING,
        STOP_SNEAKING,
        STOP_USE_ITEM;
    }

}
