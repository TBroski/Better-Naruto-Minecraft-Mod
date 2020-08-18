package com.benarutomod.tbroski;

import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.ModelCurseMarkWings;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.BeNMPlugin;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.EffectInit;
import com.benarutomod.tbroski.init.jutsu.BijuuJutsuInit;
import com.benarutomod.tbroski.init.jutsu.ERankJutsuInit;
import com.benarutomod.tbroski.init.jutsu.SharinganJutsuInit;
import com.benarutomod.tbroski.init.jutsu.SixPathJutsuInit;
import com.benarutomod.tbroski.init.jutsu.nature.*;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

@BeNMPlugin
public class MainPlugin implements IBeNMPlugin {

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
    public void registerNewBodyModes(BeNMRegistry.BodyModeRegistry bodyModeRegistry) {
        bodyModeRegistry.register(BodyInit.NULL);
        bodyModeRegistry.register(new BeNMBody(this, "curse_mark", 224, 0, (buttonBody, playerCapability) -> {
            buttonBody.setHasJutsu(playerCapability.returnPlayerCurseMark() > 0);
        }).setAttackingEffect(Effects.WITHER).setModelOnRender(new ModelCurseMarkWings(), new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/cursemarkwings.png")));

        bodyModeRegistry.register(new BeNMBody(this, "toad_sage", 224, 16, (buttonBody, playerCapability) -> {
            buttonBody.setHasJutsu(playerCapability.returnPlayerToadSageMode() > 0);
        }).setPlayerEffect(EffectInit.SAGE_CHAKRA_REG.get()).setDojutsuModelOnRender(1, 1, new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/toadsageeyes.png")));
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
        SharinganJutsuInit.registerSharinganJutsu(jutsuRegistry, this);
        SixPathJutsuInit.registerSixPathJutsu(jutsuRegistry, this);
        BijuuJutsuInit.registerBijuuJutsu(jutsuRegistry, this);
    }
}
