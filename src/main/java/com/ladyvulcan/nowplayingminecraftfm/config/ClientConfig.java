package com.ladyvulcan.nowplayingminecraftfm.config;

import com.ladyvulcan.nowplayingminecraftfm.MinecraftFMMod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;

@EventBusSubscriber(modid = MinecraftFMMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ClientConfig
{
    private static final Builder BUILDER = new ModConfigSpec.Builder();

    private static final BooleanValue USE_SIMPLE_MESSAGE = BUILDER
            .comment("Set to True to use a simple 'Now Playing...' message that displays above the action bar.")
            .define("useSimpleMessage", false);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean useSimpleMessage;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        useSimpleMessage = USE_SIMPLE_MESSAGE.get();
    }
}
