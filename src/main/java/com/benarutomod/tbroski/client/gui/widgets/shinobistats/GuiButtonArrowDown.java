package com.benarutomod.tbroski.client.gui.widgets.shinobistats;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonArrowDown extends Button {

    final ResourceLocation gray_texture = new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png");
    final ResourceLocation brown_texture = new ResourceLocation(Main.MODID + ":textures/gui/tabedbackground.png");

    protected final Button.IPressable onPress;
    final boolean gray;
    int buttonWidth = 21;
    int buttonHeight = 20;
    int u = 234;
    int v = 194;
    int widthIn;
    int heightIn;


    public GuiButtonArrowDown(int widthIn, int heightIn, boolean gray, Button.IPressable onPress) {
        super(widthIn, heightIn, 21, 20, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.gray = gray;
        this.onPress = onPress;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        if (!visible)
        {
            return;
        }
        if (gray) {
            mc.textureManager.bindTexture(gray_texture);
            if (isHovered) {
                v = 173;
            } else {
                v = 194;
            }
        }
        else {
            mc.textureManager.bindTexture(brown_texture);
            u = 233;
            if (isHovered) {
                v = 123;
            } else {
                v = 144;
            }
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
