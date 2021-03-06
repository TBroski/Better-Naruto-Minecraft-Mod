package com.benarutomod.tbroski.client.gui.widgets.shinobistats;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSettingsWheel extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png");

    protected final IPressable onPress;
    int u = 234;
    int v = 68;
    int widthIn;
    int heightIn;

    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonSettingsWheel(int widthIn, int heightIn, IPressable onPress) {
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
            v = 47;
        } else {
            v = 68;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
