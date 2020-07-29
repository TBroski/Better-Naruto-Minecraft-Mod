package com.benarutomod.tbroski.client.gui.widgets.shinobistats.settings;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class GuiButtonScrollRenderer extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/rendersettings.png");

    protected final IPressable onPress;
    int u = 234;
    int v = 236;
    int widthIn;
    int heightIn;

    public void onPress() {
        this.onPress.onPress(this);
    }

    public GuiButtonScrollRenderer(int widthIn, int heightIn, IPressable onPress) {
        super(widthIn, heightIn, 21, 20, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.onPress = onPress;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        IPlayerHandler playercap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        mc.textureManager.bindTexture(texture);
        if (!visible)
        {
            return;
        }
        if (playercap.returnToggleScrollRenderer()) {
            v = 215;
        } else {
            v = 236;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }
}
