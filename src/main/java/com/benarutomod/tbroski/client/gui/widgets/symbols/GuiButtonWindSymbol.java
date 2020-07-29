package com.benarutomod.tbroski.client.gui.widgets.symbols;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonWindSymbol extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/symbols.png");

    protected final IPressable onPress;
    int u = 0;
    int v = 40;
    int widthIn;
    int heightIn;

    public GuiButtonWindSymbol(int widthIn, int heightIn, IPressable onPress) {
        super(widthIn, heightIn, 10, 10, "", onPress);
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
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
