package com.ladyvulcan.nowplayingminecraftfm.client.gui;

import javax.annotation.Nonnull;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NowPlayingToast implements Toast {
    private static final ResourceLocation BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("toast/recipe");
    private static final int TITLE_COLOUR = 0xFF500050;
    private static final int NAME_COLOUR = 0xFF000000;
    private static final long DURATION_VISIBLE = 5000L;
    private ItemStack displayStack;
    private Component title;
    private Component name;

    public NowPlayingToast(Component title, Component songName) {
        this.title = title;
        this.name = songName;
        displayStack = new ItemStack(Items.MUSIC_DISC_13.asItem());
    }

    @Override
    public Toast.Visibility render(@Nonnull GuiGraphics guiGraphics, @Nonnull ToastComponent toastComponent, long timeSinceLastVisible) {
        guiGraphics.blitSprite(BACKGROUND_SPRITE, 0, 0, this.width(), this.height());
        guiGraphics.drawString(toastComponent.getMinecraft().font, title, 30, 7, TITLE_COLOUR, false);
        guiGraphics.drawString(toastComponent.getMinecraft().font, this.name, 30, 18, NAME_COLOUR, false);
        guiGraphics.renderFakeItem(displayStack, 8, 8);
        return timeSinceLastVisible >= DURATION_VISIBLE ? Visibility.HIDE : Visibility.SHOW;
    }
    
}
