package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;

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
