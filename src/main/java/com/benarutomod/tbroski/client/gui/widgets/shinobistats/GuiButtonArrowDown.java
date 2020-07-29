package com.benarutomod.tbroski.client.gui.widgets.shinobistats;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonArrowDown extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png");

    protected final Button.IPressable onPress;
    int buttonWidth = 21;
    int buttonHeight = 20;
    int u = 234;
    int v = 194;
    int widthIn;
    int heightIn;


    public GuiButtonArrowDown(int widthIn, int heightIn, Button.IPressable onPress) {
        super(widthIn, heightIn, 21, 20, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.onPress = onPress;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(texture);
        if (!visible)
        {
            return;
        }
        if (isHovered) {
            v = 173;
        } else {
            v = 194;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
