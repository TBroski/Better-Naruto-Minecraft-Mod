package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.player.jutsu.SharinganJutsuScreen;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonDojutsu;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.common.BeNMDojutsu;
import com.benarutomod.tbroski.init.DojutsuInit;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PlayerDojutsu extends AbstractTabedBackground {

    GuiButtonDojutsu guiButtonSharingan;

    protected PlayerDojutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.dojutsu"));
    }

    @Override
    public void registerTabsAndWidgets(IPlayerHandler playerCapability) {
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 32, 240, 0, "Dojutsu Abilities", $ -> {
            openedTab = 0;
        }));
        addButton(guiButtonSharingan = new GuiButtonDojutsu(this.guiLeft - 32, this.guiTop - 32, DojutsuInit.MANGEKYOU_SHARINGAN, $ -> {
            if ((playerCapability.returnPlayerLeftDojutsu().getType() == BeNMDojutsu.Type.SHARINGAN && playerCapability.returnPlayerLeftDojutsu() != DojutsuInit.SHARINGAN) || (playerCapability.returnPlayerLeftDojutsu().getType() == BeNMDojutsu.Type.SHARINGAN && playerCapability.returnPlayerRightDojutsu() != DojutsuInit.SHARINGAN)) {
                Minecraft.getInstance().displayGuiScreen(new SharinganJutsuScreen());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't posses a Mangekyou Sharingan."));
            }
        }));
    }

    @Override
    public void renderPage(int openedTab, int p_render_1_, int p_render_2_, float p_render_3_) {

        if (openedTab != 0) {
            guiButtonSharingan.visible = false;
        }
        switch (openedTab) {
            case 0:
                guiButtonSharingan.visible = true;
                guiButtonSharingan.renderButton(p_render_1_, p_render_2_, p_render_3_);
        }
    }
}
