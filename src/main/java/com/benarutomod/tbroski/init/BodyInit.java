package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.ModelCurseMarkWings;
import com.benarutomod.tbroski.common.BeNMBody;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class BodyInit {

    public static final BeNMBody NULL = new BeNMBody(new IBeNMPlugin() {
        @Override
        public String getPluginId() {
            return Main.MODID;
        }

        @Override
        public void registerNewDojutsus(BeNMRegistry.DojutsuRegistry dojutsuRegistry) {

        }

        @Override
        public void registerNewClans(BeNMRegistry.ClanRegistry clanRegistry) {

        }

        @Override
        public void registerNewBodyModes(BeNMRegistry.BodyModeRegistry bodyModeRegistry) {

        }

        @Override
        public void registerNewJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry) {

        }
    }, "null", 240, 240, (buttonJutsu, playerCapability) -> {

    },(buttonBody, playerCapability) -> {
        buttonBody.setHasJutsu(true);
    });
}
