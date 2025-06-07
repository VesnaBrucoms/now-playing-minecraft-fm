package com.ladyvulcan.nowplayingminecraftfm.utils;

import net.minecraft.network.chat.Component;

public class TextUtils {
    public static Component extractAndFormatSongName(String filePath) {
        int start = filePath.lastIndexOf("/") + 1;
        int end = filePath.indexOf(".");

        String name = filePath.substring(start, end);
        return Component.literal(formatName(name));
    }

    public static String formatName(String name) {
        name = name.replaceAll("_", " ");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
}
