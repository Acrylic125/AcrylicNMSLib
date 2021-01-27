package com.acrylic.universal.packets;

import com.acrylic.universal.text.ChatUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TablistHeaderFooterPacket extends SinglePacketSender {

    @Nullable
    String getHeader();

    TablistHeaderFooterPacket removeHeader();

    TablistHeaderFooterPacket setHeader(@NotNull String header);

    @NotNull
    default TablistHeaderFooterPacket setHeader(@NotNull String... header) {
        return setHeader(toStringFromArray(header));
    }

    @Nullable
    String getFooter();

    @NotNull
    TablistHeaderFooterPacket removeFooter();

    @NotNull
    TablistHeaderFooterPacket setFooter(@NotNull String footer);

    default TablistHeaderFooterPacket setFooter(@NotNull String... footer) {
        return setFooter(toStringFromArray(footer));
    }

    default String toStringFromArray(@NotNull String[] str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0, m = str.length;
        for (String s : str) {
            stringBuilder.append(ChatUtils.get(s));
            if (i >= m - 1)
                break;
            stringBuilder.append("\n");
            i++;
        }
        return stringBuilder.toString();
    }

    @NotNull
    TablistHeaderFooterPacket build();

}
