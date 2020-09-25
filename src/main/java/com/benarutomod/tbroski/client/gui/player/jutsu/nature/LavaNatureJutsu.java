package com.benarutomod.tbroski.client.gui.player.jutsu.nature;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class LavaNatureJutsu extends AbstractJutsuScreen {

    private ArrayList<BeNMJutsu> jutsus = new ArrayList<>();

    public LavaNatureJutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.lavanaturejutsu"));
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            if (jutsu.getType() == BeNMJutsu.Type.LAVA_NATURE) {
                this.jutsus.add(jutsu);
            }
        }
    }

    @Override
    public void registerJutsus(IPlayerHandler playerCapability) {
        int x = -90;
        int y = -90;
        for (BeNMJutsu jutsu : this.jutsus) {
            addButton(new GuiButtonJutsu(this.guiLeft + x, this.guiTop + y, jutsu.getU(), jutsu.getV(), jutsu.getCorrelatedPlugin().getPluginId() + "." + jutsu.getName(), false, jutsu.getCost(), jutsu.getResourceLocationForGUI(), jutsu.getPress()));
            if (x == 90) {
                x = -90;
                y += 20;
            }
            else {
                x += 20;
            }
        }
    }

    @Override
    public void setJutsuBooleans(IPlayerHandler playerCapability) {
        for (BeNMJutsu jutsu : this.jutsus) {
            for (Widget button : this.buttons) {
                if (button instanceof GuiButtonJutsu) {
                    GuiButtonJutsu buttonJutsu = (GuiButtonJutsu) button;
                    if (buttonJutsu.getJutsuName().equalsIgnoreCase(jutsu.getName())) {
                        jutsu.update(buttonJutsu, jutsu, playerCapability);
                    }
                }
            }
        }
    }
}
