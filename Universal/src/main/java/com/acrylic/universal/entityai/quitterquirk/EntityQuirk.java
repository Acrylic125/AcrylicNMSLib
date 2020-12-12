package com.acrylic.universal.entityai.quitterquirk;

import com.acrylic.universal.entityai.EntityAI;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface EntityQuirk<T extends LivingEntityAnimator>
        extends Cloneable {

    void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI);

}
