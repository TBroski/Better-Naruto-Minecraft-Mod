package com.benarutomod.tbroski.client.gui.widgets.jutsu;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.ResourceLocation;

public class GuiButtonJutsu extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png");

    protected final IPressable onPress;
    final int u;
    final int v;
    int widthIn;
    int heightIn;

    public GuiButtonJutsu(int widthIn, int heightIn, int u, int v, IPressable onPress) {
        super(widthIn, heightIn, 16, 16, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.u = u;
        this.v = v;
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
