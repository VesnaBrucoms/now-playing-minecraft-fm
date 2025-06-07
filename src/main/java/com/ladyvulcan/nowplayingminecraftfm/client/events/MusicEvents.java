package com.ladyvulcan.nowplayingminecraftfm.client.events;

import com.ladyvulcan.nowplayingminecraftfm.MinecraftFMMod;
import com.ladyvulcan.nowplayingminecraftfm.client.gui.NowPlayingToast;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.SelectMusicEvent;

@EventBusSubscriber(modid = MinecraftFMMod.MODID, value = Dist.CLIENT)
public class MusicEvents {

    private static boolean isSongPlaying = false;

    @SubscribeEvent
    public static void onMusicSelected(SelectMusicEvent e)
    {
        SoundInstance currentSong = e.getPlayingMusic();
        if (currentSong != null && currentSong.getSound() != null && !isSongPlaying) {
            Component name = extractAndFormatSongName(currentSong.getSound().getPath().getPath());
            LocalPlayer pl = Minecraft.getInstance().player;
            if (pl != null) {
                MutableComponent title = Component.translatable("nowplayingminecraftfm.toast.title");
                MutableComponent msg = title.append(": ").append(name);
                pl.displayClientMessage(msg, true);
                Minecraft.getInstance().getToasts().addToast(new NowPlayingToast(title, name));
            }
            isSongPlaying = true;
        } else if (currentSong == null && isSongPlaying) {
            isSongPlaying = false;
        }
    }

    private static Component extractAndFormatSongName(String filePath) {
        int start = filePath.lastIndexOf("/") + 1;
        int end = filePath.indexOf(".");

        String name = filePath.substring(start, end);
        return Component.literal(formatName(name));
    }

    private static String formatName(String name) {
        name = name.replaceAll("_", " ");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
}
