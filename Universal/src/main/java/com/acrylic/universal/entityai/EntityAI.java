package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.strategy.EntityStrategy;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAI<T extends LivingEntityAnimator>
        extends Cloneable {

    void update(@NotNull T entityAnimator);

}
