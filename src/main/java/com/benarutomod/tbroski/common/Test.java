package com.benarutomod.tbroski.common;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.jutsu.ERankJutsuInit;
import com.benarutomod.tbroski.init.jutsu.nature.*;

import java.util.List;

@BeNMPlugin
public class Test implements IBeNMPlugin {

    @Override
    public String getPluginId() {
        return Main.MODID;
    }

    @Override
    public void registerNewDojutsus(BeNMRegistry.DojutsuRegistry dojutsuRegistry) {
        dojutsuRegistry.register(DojutsuInit.NULL);
        dojutsuRegistry.register(DojutsuInit.EMPTY);
        dojutsuRegistry.register(DojutsuInit.SHARINGAN);
        dojutsuRegistry.register(DojutsuInit.MANGEKYOU_SHARINGAN);
        dojutsuRegistry.register(DojutsuInit.RINNEGAN);
        dojutsuRegistry.register(DojutsuInit.BYAKUGAN);
    }

    @Override
    public void registerNewClans(BeNMRegistry.ClanRegistry clanRegistry) {
        clanRegistry.register(ClanInit.NULL);
        clanRegistry.register(ClanInit.UCHIHA);
        clanRegistry.register(ClanInit.HYUGA);
        clanRegistry.register(ClanInit.UZUMAKI);
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
        ERankJutsuInit.registerERankJutsu(jutsuRegistry, this);
    }
}
