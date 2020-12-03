package com.acrylic.universal.npc;

import com.acrylic.universal.packets.EntityHeadRotationPacket;
import com.acrylic.universal.packets.LivingEntityDisplayPackets;

public interface NPCDisplayPackets extends LivingEntityDisplayPackets {

    EntityHeadRotationPacket getHeadRotationPacket();

}
