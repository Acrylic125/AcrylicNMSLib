package com.acrylic.universal.entityinstances.instances;

import com.acrylic.universal.emtityanimator.NMSArmorStandAnimator;
import com.acrylic.universal.entityinstances.LivingEntityInstance;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public interface ArmorStandEntityInstance
        extends LivingEntityInstance  {

    @NotNull
    @Override
    NMSArmorStandAnimator getAnimator();

    void setGravity(boolean b);

    void setArms(boolean b);

    void setBasePlate(boolean b);

    void setMarker(boolean b);

    void setSmall(boolean b);

    void setHeadRotation(@NotNull EulerAngle angle);

    void setLeftArmRotation(@NotNull EulerAngle angle);

    void setRightArmRotation(@NotNull EulerAngle angle);

    void setLeftLegRotation(@NotNull EulerAngle angle);

    void setRightLegRotation(@NotNull EulerAngle angle);

}
