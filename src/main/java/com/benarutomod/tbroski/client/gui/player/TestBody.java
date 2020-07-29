package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class TestBody extends TabedBackground{
    protected TestBody() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
    }

    @Override
    public ArrayList<GuiButtonTab> getTabs() {
        ArrayList<GuiButtonTab> tabs = new ArrayList<>();
        tabs.add(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 220, 220, 0, $ -> {
            System.out.println("CALLED");
        }));
        return tabs;
    }
}
