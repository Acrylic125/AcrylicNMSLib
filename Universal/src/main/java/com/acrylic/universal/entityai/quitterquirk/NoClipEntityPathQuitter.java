package com.acrylic.universal.entityai.quitterquirk;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
import com.acrylic.universal.entityai.EntityAI;
import org.jetbrains.annotations.NotNull;

public class NoClipEntityPathQuitter<T extends NMSLivingEntityAnimator>
        extends SimpleEntityPathQuitter<T> {

    private long noClipDuration = 5_000;
    private long noClipTime = 0;

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
    public void update(@NotNull EntityAI<T> ai) {
        if (isReadyToGiveUp())
            noClipTime = System.currentTimeMillis() + noClipDuration;
        ai.getAnimator().setNoClip(isNoClipActive());
    }

    @Override
    public NoClipEntityPathQuitter<T> clone() {
        NoClipEntityPathQuitter<T> entityPathQuitter = (NoClipEntityPathQuitter<T>) super.clone();
        entityPathQuitter.setNoClipDuration(noClipDuration);
        return entityPathQuitter;
    }
}
