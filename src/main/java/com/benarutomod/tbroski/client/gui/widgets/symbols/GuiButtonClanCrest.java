package com.benarutomod.tbroski.client.gui.widgets.symbols;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;

public class GuiButtonClanCrest extends Button {

    private BeNMClan clan;
    private int widthIn;
    private int heightIn;

    public GuiButtonClanCrest(int widthIn, int heightIn, BeNMClan clan, IPressable onPress) {
        super(widthIn, heightIn, 20,20, "", onPress);
        this.clan = clan;
        this.widthIn = widthIn;
        this.heightIn = heightIn;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(this.clan.getResourceLocation());
        if (!visible) {
            return;
        }
        mc.ingameGUI.blit(widthIn, heightIn, this.clan.getU(), this.clan.getV(), width, height);
    }

}
