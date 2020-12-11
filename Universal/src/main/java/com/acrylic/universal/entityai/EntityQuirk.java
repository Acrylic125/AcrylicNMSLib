package com.acrylic.universal.entityai;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;

public interface EntityQuirk<T extends LivingEntityAnimator> {

    void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI);

}
