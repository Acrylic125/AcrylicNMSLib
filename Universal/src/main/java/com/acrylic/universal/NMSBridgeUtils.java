package com.acrylic.universal;

import com.acrylic.universal.misc.BoundingBoxExaminer;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class NMSBridgeUtils {

    public abstract BoundingBoxExaminer getBlockExaminer();

    public BoundingBoxExaminer getBlockExaminer(@NotNull Location location) {
        BoundingBoxExaminer examiner = getBlockExaminer();
        examiner.examine(location);
        return examiner;
    }
}
