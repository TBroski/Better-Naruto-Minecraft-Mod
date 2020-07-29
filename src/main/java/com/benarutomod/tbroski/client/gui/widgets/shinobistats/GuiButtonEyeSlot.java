package com.benarutomod.tbroski.client.gui.widgets.shinobistats;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonEyeSlot extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png");

    protected final Button.IPressable onPress;
    int u = 234;
    int v = 236;
    int widthIn;
    int heightIn;

    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonEyeSlot(int widthIn, int heightIn, IPressable onPress) {
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
            v = 215;
        } else {
            v = 236;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
