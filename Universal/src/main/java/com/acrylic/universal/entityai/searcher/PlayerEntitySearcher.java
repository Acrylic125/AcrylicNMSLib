package com.acrylic.universal.entityai.searcher;

import com.acrylic.universal.entityai.TargetableAI;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerEntitySearcher<T extends LivingEntityAnimator>
        extends SimpleEntitySearcher<T> {

    public PlayerEntitySearcher(@NotNull TargetableAI<T> ai) {
        super(ai);
    }

    @Override
    public boolean canFollow(@NotNull LivingEntity entity) {
        return super.canFollow(entity) && entity instanceof Player && ((Player) entity).isOnline();
    }

}
