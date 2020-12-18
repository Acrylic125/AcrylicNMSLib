package com.acrylic.version_1_8;

import com.acrylic.universal.NMSBridgeUtils;
import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class NMSBridgeUtils_1_8 extends NMSBridgeUtils {

    @Override
    public BoundingBoxExaminer getBlockExaminer() {
        return new com.acrylic.version_1_8.misc.BoundingBoxExaminer();
    }

}
