package com.benarutomod.tbroski.client.gui.player.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.common.jutsu.SharinganJutsu;
import net.minecraft.util.text.TranslationTextComponent;

public class SharinganJutsuScreen extends AbstractJutsuScreen{

    GuiButtonJutsu guiButtonAmaterasu;
    GuiButtonJutsu guiButtonTsukuyomi;

    public SharinganJutsuScreen() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.sharinganjutsu"));
    }

    @Override
    public void registerJutsus(IPlayerHandler playerCapability) {
        addButton(guiButtonAmaterasu = new GuiButtonJutsu(this.guiLeft - 90, this.guiTop - 90, 240, 0, "amaterasu", playerCapability.hasAmaterasuJutsuBoolean(), 15, $ -> {
            boolean didBuy = guiButtonAmaterasu.doNormalPress(this);
            if (didBuy) {
                playerCapability.setAmaterasuJutsuBoolean(true);
                guiButtonAmaterasu.sendPackets(SharinganJutsu.AmaterasuJutsuID, true);
            }
        }));
        addButton(guiButtonTsukuyomi = new GuiButtonJutsu(this.guiLeft - 70, this.guiTop - 90, 240, 16, "tsukuyomi", playerCapability.hasTsukuyomiJutsuBoolean(), 8,$ -> {
            boolean didBuy = guiButtonTsukuyomi.doNormalPress(this);
            if (didBuy) {
                playerCapability.setTsukuyomiJutsuBoolean(true);
                guiButtonTsukuyomi.sendPackets(SharinganJutsu.TsukuyomiJutsuID, true);
            }
        }));
    }

    @Override
    public void setJutsuBooleans(IPlayerHandler playerCapability) {
        guiButtonAmaterasu.setHasJutsu(playerCapability.hasAmaterasuJutsuBoolean());
        guiButtonTsukuyomi.setHasJutsu(playerCapability.hasTsukuyomiJutsuBoolean());
    }
}
