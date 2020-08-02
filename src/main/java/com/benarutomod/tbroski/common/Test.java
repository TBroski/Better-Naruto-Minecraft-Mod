package com.benarutomod.tbroski.common;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.jutsu.*;

import java.util.List;

@BeNMPlugin
public class Test implements IBeNMPlugin {

    @Override
    public String getPluginId() {
        return Main.MODID;
    }

    @Override
    public void registerNewDojutsus(List<BeNMDojutsu> dojutsus) {

    }

    @Override
    public void registerNewClans(List<BeNMClan> clans) {

    }

    @Override
    public void registerNewBodyModes(List<BeNMBody> bodies) {

    }

    @Override
    public void registerNewJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry) {
        FireNatureJutsuInit.registerFireJutsu(jutsuRegistry, this);
        EarthNatureJutsuInit.registerEarthJutsu(jutsuRegistry, this);
        WaterNatureJutsuInit.registerWaterJutsu(jutsuRegistry, this);
        WindNatureJutsuInit.registerWindJutsu(jutsuRegistry, this);
        LightningNatureJutsuInit.registerLightningJutsu(jutsuRegistry, this);
        MagnetNatureJutsuInit.registerMagnetJutsu(jutsuRegistry, this);
    }
}
