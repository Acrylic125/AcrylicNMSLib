package com.acrylic.universal.text;

import org.jetbrains.annotations.NotNull;

public interface TitleSender extends DurationTextSender {

    TitleSender subTitle(@NotNull String text);

    String getSubTitle();

    TitleSender fadeIn(int fadeIn);

    int getFadeIn();

    TitleSender fadeOut(int fadeOut);

    int getFadeOut();

    TitleSender duration(int duration);

    int getDuration();

}
