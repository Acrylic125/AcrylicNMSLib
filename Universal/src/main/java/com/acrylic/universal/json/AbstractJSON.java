package com.acrylic.universal.json;

import com.acrylic.universal.packets.Sender;

public interface AbstractJSON extends Sender, Cloneable {

    AbstractJSON append(AbstractJSONComponent component);

    String toJson();

    AbstractJSON duplicate();

}
