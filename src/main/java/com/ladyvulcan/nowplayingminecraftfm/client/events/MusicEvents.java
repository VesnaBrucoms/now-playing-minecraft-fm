package com.ladyvulcan.nowplayingminecraftfm.client.events;

import com.ladyvulcan.nowplayingminecraftfm.MinecraftFMMod;
import com.ladyvulcan.nowplayingminecraftfm.client.gui.NowPlayingToast;
import com.ladyvulcan.nowplayingminecraftfm.config.ClientConfig;
import com.ladyvulcan.nowplayingminecraftfm.utils.TextUtils;

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
            Component name = TextUtils.extractAndFormatSongName(currentSong.getSound().getPath().getPath());
            LocalPlayer pl = Minecraft.getInstance().player;
            if (pl != null) {
                MutableComponent title = Component.translatable("nowplayingminecraftfm.toast.title");
                if (ClientConfig.useSimpleMessage) {
                    MutableComponent msg = title.append(": ").append(name);
                    pl.displayClientMessage(msg, true);
                } else {
                    Minecraft.getInstance().getToasts().addToast(new NowPlayingToast(title, name));
                }
            }
            isSongPlaying = true;
        } else if (currentSong == null && isSongPlaying) {
            isSongPlaying = false;
        }
    }
}
