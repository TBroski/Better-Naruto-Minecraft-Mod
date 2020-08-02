package com.benarutomod.tbroski.client.gui.widgets.symbols;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSymbol extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/symbols.png");

    protected final Button.IPressable onPress;
    final int u;
    final int v;
    int widthIn;
    int heightIn;

    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonSymbol(int widthIn, int heightIn, int u, int v, IPressable onPress) {
        super(widthIn, heightIn, 10, 10, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.onPress = onPress;
        this.u = u;
        this.v = v;
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
