package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class TestBody extends TabedBackground {

    private ArrayList<GuiButtonTab> tabs = new ArrayList<>();

    protected TestBody() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
    }

    @Override
    public void registerTabs() {
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 0, 240, 0, "Chakra Control", $ -> {
            openedTab = 0;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(1), this.getHeightInFromTab(1), 16, 240, 1, "Body Mode", $ -> {
            openedTab = 1;
        }));
    }

    @Override
    public void renderPage(int openedTab) {

    }
}
