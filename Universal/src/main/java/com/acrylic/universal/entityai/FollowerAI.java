package com.acrylic.universal.entityai;

import com.acrylic.universal.entityai.attack.EntityAttacker;
import com.acrylic.universal.entityai.pathfinder.EntityPathfinder;
import com.acrylic.universal.entityai.quitterstrategy.EntityQuitterStrategy;
import com.acrylic.universal.entityai.searcher.EntitySearcher;
import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class FollowerAI<T extends LivingEntityAnimator>
        implements TargetableAI<T> {

    private final T animator;
    private EntityPathfinder<T> entityPathfinder;
    private EntitySearcher<T> searcher;
    private EntityAttacker<T> attacker;
    private WeakReference<Entity> targetReference;

    public FollowerAI(T animator) {
        this.animator = animator;
    }

    public void setPathfinder(@Nullable EntityPathfinder<T> pathfinder) {
        this.entityPathfinder = pathfinder;
    }

    @Nullable
    public EntityPathfinder<T> getPathfinder() {
        return entityPathfinder;
    }

    public EntitySearcher<T> getSearcher() {
        return searcher;
    }

    public void setSearcher(@Nullable EntitySearcher<T> searcher) {
        this.searcher = searcher;
    }

    public EntityAttacker<T> getAttackerStrategy() {
        return attacker;
    }

    public void setAttackerStrategy(EntityAttacker<T> attacker) {
        this.attacker = attacker;
    }

    @Override
    public T getAnimator() {
        return animator;
    }

    @Override
    public void update() {
        Entity entity = getTarget();
        if (entity != null && !entity.isValid())
            setTarget(null);
        if (entityPathfinder != null)
            entityPathfinder.update();
        if (searcher != null)
            searcher.update();
        if (attacker != null)
            attacker.update();
    }

    @Override
    public void cleanAI() {
        setTarget(null);
        entityPathfinder.setTargetLocation(null);
    }

    @Override
    public void aiUnloadCheck(@NotNull Player player) {
        if (getTarget() != player)
            cleanAI();
    }

    @Override
    public void setTarget(@Nullable Entity entity) {
        this.targetReference = (entity == null) ? null : new WeakReference<>(entity);
    }

    @Nullable
    @Override
    public Entity getTarget() {
        return (targetReference == null) ? null : this.targetReference.get();
    }

    @Override
    public void setTargetLocation(@Nullable Location location) {
        entityPathfinder.setTargetLocation(location);
    }

    @Nullable
    @Override
    public Location getTargetLocation() {
        return entityPathfinder.getTargetLocation();
    }
}
