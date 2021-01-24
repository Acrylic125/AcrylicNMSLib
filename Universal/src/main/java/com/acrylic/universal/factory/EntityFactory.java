package com.acrylic.universal.factory;

import com.acrylic.universal.emtityanimator.instances.NMSArmorStandAnimator;
import com.acrylic.universal.emtityanimator.instances.NMSGiantAnimator;
import com.acrylic.universal.emtityanimator.instances.PlayerNPC;
import com.acrylic.universal.entityinstances.instances.ArmorStandEntityInstance;
import com.acrylic.universal.entityinstances.instances.GiantEntityInstance;
import com.acrylic.universal.entityinstances.instances.PlayerNPCEntityInstance;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The entity factory generates NMS entities.
 *
 * This is meant to be used in conjunction with {@link com.acrylic.universal.emtityanimator.NMSEntityAnimator}
 */
public interface EntityFactory {

    @NotNull
    PlayerNPCEntityInstance getNewNPC(@NotNull PlayerNPC animator, @NotNull Location location, @Nullable String name);

    @NotNull
    ArmorStandEntityInstance getNewArmorStandEntityInstance(@NotNull NMSArmorStandAnimator animator, @NotNull Location location);

    @NotNull
    GiantEntityInstance getNewGiantEntityInstance(@NotNull NMSGiantAnimator animator, @NotNull Location location);

}
