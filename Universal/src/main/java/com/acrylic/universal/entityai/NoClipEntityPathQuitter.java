package com.acrylic.universal.entityai;

import com.acrylic.universal.emtityanimator.NMSLivingEntityAnimator;
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

    public boolean noClip() {
        return noClipTime >= System.currentTimeMillis();
    }

    @Override
    public void update(@NotNull T entityAnimator, @NotNull EntityAI<T> entityAI) {
        if (isReadyToGiveUp())
            noClipTime = System.currentTimeMillis() + noClipDuration;
        entityAnimator.setNoClip(noClip());
    }
}