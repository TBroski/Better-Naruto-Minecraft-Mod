package com.benarutomod.tbroski.client.gui.widgets.shinobistats;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonClanConfiguration extends Button {

    static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png");

    int u = 49;
    int v = 242;
    int widthIn;
    int heightIn;

    public GuiButtonClanConfiguration(int widthIn, int heightIn, IPressable onPress) {
        super(widthIn, heightIn, 14, 14, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(TEXTURE);
        if (!visible)
        {
            return;
        }
        if (isHovered) {
            u = 64;
        } else {
            u = 49;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
