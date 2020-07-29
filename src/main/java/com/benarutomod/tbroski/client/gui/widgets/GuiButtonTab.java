package com.benarutomod.tbroski.client.gui.widgets;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTab extends Button {

    private final ResourceLocation tab_texture = new ResourceLocation(Main.MODID, "textures/gui/tabedbackground.png");
    private final int symbolU;
    private final int symbolV;

    public boolean toggled;
    protected final IPressable onPress;
    int u = 0;
    int v = 0;
    int widthIn;
    int heightIn;
    final int tabNumber;

    public GuiButtonTab(int widthIn, int heightIn, int symbolU, int symbolV, int tabNumber, IPressable onPress) {
        super(widthIn, heightIn, 28, 31, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.onPress = onPress;
        this.symbolU = symbolU;
        this.symbolV = symbolV;
        this.tabNumber = tabNumber;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(tab_texture);
        if (!visible)
        {
            return;
        }
        if (toggled) {
            v = 32;
        }
        else {
            v = 1;
        }
        u += this.tabNumber * 28;
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
        mc.ingameGUI.blit(widthIn + 5, heightIn, symbolU, symbolV, 16, 16);
    }

    public void toggle() {
        if (toggled) {
            toggled = false;
        }
        else {
            toggled = true;
        }
    }
}
