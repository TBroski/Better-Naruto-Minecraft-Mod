package com.benarutomod.tbroski.client.gui.widgets;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTab extends Button {

    private final ResourceLocation tab_texture = new ResourceLocation(Main.MODID, "textures/gui/tabedbackground.png");
    private final int symbolU;
    private final int symbolV;
    private final String toolTip;

    public boolean toggled;
    protected final IPressable onPress;
    int u = 0;
    int widthIn;
    int heightIn;
    final int tabNumber;

    @Override
    public void onPress() {
        toggle();
        this.onPress.onPress(this);
    }

    public GuiButtonTab(int widthIn, int heightIn, int symbolU, int symbolV, int tabNumber, String toolTip, IPressable onPress) {
        super(widthIn, heightIn, 28, 31, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.onPress = onPress;
        this.symbolU = symbolU;
        this.symbolV = symbolV;
        this.tabNumber = tabNumber;
        this.toolTip = toolTip;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(tab_texture);
        if (!visible)
        {
            return;
        }
        u = this.tabNumber * 28;
        if (toggled) {
            mc.ingameGUI.blit(widthIn, heightIn , u, 32, width, height);
        }
        else {
            mc.ingameGUI.blit(widthIn, heightIn , u, 2, width, height);
        }
        mc.ingameGUI.blit(widthIn + 6, heightIn + 8, symbolU, symbolV, 16, 16);
    }

    public void toggle() {
        if (toggled) {
            toggled = false;
        }
        else {
            toggled = true;
        }
    }

    public int getTab() {
        return this.tabNumber;
    }

    public String getToolTip() {
        return this.toolTip;
    }
}
