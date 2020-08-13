package com.benarutomod.tbroski.client.gui.player.body;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.init.BodyInit;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class BodyModeScreen extends AbstractBodyScreen {

    private ArrayList<BeNMBody> bodies = new ArrayList<>();

    public BodyModeScreen() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodymode"));
        for (BeNMBody bodyMode : BeNMRegistry.BODY_MODES.getValues()) {
            this.bodies.add(bodyMode);
        }
    }

    @Override
    public void registerBodyModes(IPlayerHandler playerCapability) {
        int x = -90;
        int y = -90;
        for (BeNMBody body : this.bodies) {
            if (body != BodyInit.NULL) {
                addButton(new GuiButtonJutsu(this.guiLeft + x, this.guiTop + y, body.getU(), body.getV(), body.getCorrelatedPlugin().getPluginId() + "." + body.getName(), false, 0, body.getResourceLocationForGUI(), body.getPress()));
                if (x == 90) {
                    x = -90;
                    y += 20;
                } else {
                    x += 20;
                }
            }
        }
    }

    @Override
    public void setBodyModeBooleans(IPlayerHandler playerCapability) {
        for (BeNMBody body : this.bodies) {
            for (Widget button : this.buttons) {
                if (button instanceof GuiButtonJutsu) {
                    GuiButtonJutsu buttonJutsu = (GuiButtonJutsu) button;
                    if (buttonJutsu.getJutsuName().equalsIgnoreCase(body.getName())) {
                        body.update(buttonJutsu, playerCapability);
                    }
                }
            }
        }
    }
}
