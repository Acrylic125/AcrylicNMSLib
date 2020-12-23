package com.acrylic.universal.entityai.quitterstrategy;

import com.acrylic.universal.emtityanimator.instances.NMSLivingEntityAnimator;
import com.acrylic.universal.entityai.FollowerAI;
import org.jetbrains.annotations.NotNull;

public class NoClipEntityPathQuitter<T extends NMSLivingEntityAnimator>
        extends SimpleEntityPathQuitter<T> {

    private long noClipDuration = 5_000;
    private long noClipTime = 0;

    public NoClipEntityPathQuitter(@NotNull FollowerAI<T> ai) {
        super(ai);
    }

    public void setNoClipDuration(long noClipDuration) {
        this.noClipDuration = noClipDuration;
    }

    public long getNoClipDuration() {
        return noClipDuration;
    }

    public boolean isNoClipActive() {
        return noClipTime >= System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (isReadyToGiveUp())
            noClipTime = System.currentTimeMillis() + noClipDuration;
        getAnimator().setNoClip(isNoClipActive());
    }

    @Override
    public NoClipEntityPathQuitter<T> clone() {
        NoClipEntityPathQuitter<T> entityPathQuitter = (NoClipEntityPathQuitter<T>) super.clone();
        entityPathQuitter.setNoClipDuration(noClipDuration);
        return entityPathQuitter;
    }
}
