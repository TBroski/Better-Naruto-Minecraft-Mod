package com.benarutomod.tbroski.client.gui.widgets.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;

public class GuiButtonPlayerHead extends Button{

    AbstractClientPlayerEntity player;
    protected final Button.IPressable onPress;
    int u = (8 * 4);
    int v = (8 * 4);
    int widthIn;
    int heightIn;

    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonPlayerHead(int widthIn, int heightIn, AbstractClientPlayerEntity player, Button.IPressable onPress) {
        super(widthIn, heightIn, (8 * 4), (8 * 4), "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.onPress = onPress;
        this.player = player;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(player.getLocationSkin());
        if (!visible)
        {
            return;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
