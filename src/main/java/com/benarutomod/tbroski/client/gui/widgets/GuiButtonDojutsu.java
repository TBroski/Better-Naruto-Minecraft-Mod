package com.benarutomod.tbroski.client.gui.widgets;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.common.BeNMDojutsu;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDojutsu extends Button {

    public boolean toggled;
    protected final Button.IPressable onPress;
    int u = 16;
    int v = 16;
    BeNMDojutsu dojutsu;
    int widthIn;
    int heightIn;


    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonDojutsu(int widthIn, int heightIn, BeNMDojutsu dojutsu, IPressable onPress) {
        super(widthIn, heightIn, 16 * (dojutsu.getSize() + 1), 16, "", onPress);
        this.onPress = onPress;
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.dojutsu = dojutsu;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        if (!visible || !DojutsuHelper.dojutsuNotNull(dojutsu))
        {
            return;
        }
        if (toggled) {
            mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
            mc.ingameGUI.blit(widthIn - 1, heightIn - 1, 238, 206, 18, 18);
        }
        mc.textureManager.bindTexture(dojutsu.getResourceLocation());
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height, 256, 256);
    }
}
