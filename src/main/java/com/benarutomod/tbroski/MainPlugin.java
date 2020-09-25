package com.benarutomod.tbroski;

import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.ModelCurseMarkWings;
import com.benarutomod.tbroski.api.internal.BeNMBody;
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
import com.benarutomod.tbroski.init.jutsu.nature.kekkai.*;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

public class MainPlugin implements IBeNMPlugin {

    @Override
    public String getPluginId() {
        return Main.MODID;
    }

    @Override
    public void registerNewDojutsus(BeNMRegistry.DojutsuRegistry dojutsuRegistry) {
        dojutsuRegistry.register(DojutsuInit.NULL);
        dojutsuRegistry.register(DojutsuInit.SHARINGAN);
        dojutsuRegistry.register(DojutsuInit.MANGEKYOU_SHARINGAN);
        dojutsuRegistry.register(DojutsuInit.ETERNAL_MANGEKYOU_SHARINGAN);
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
        bodyModeRegistry.register(new BeNMBody(this, "curse_mark", 480, 0, (buttonBody, playerCapability) -> {
            buttonBody.setHasJutsu(playerCapability.returnPlayerCurseMark() > 0);
        }).setAttackingEffect(Effects.WITHER).setModelOnRender(new ModelCurseMarkWings(), new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/cursemarkwings.png")));

        bodyModeRegistry.register(new BeNMBody(this, "toad_sage", 480, 16, (buttonBody, playerCapability) -> {
            buttonBody.setHasJutsu(playerCapability.returnPlayerToadSageMode() > 0);
        }).addPlayerEffect(EffectInit.SAGE_CHAKRA_REG.get()).setDojutsuModelOnRender(1, 1, new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/toadsageeyes.png")));

        bodyModeRegistry.register(new BeNMBody(this, "bijuu", 480, 32, (buttonBody, playerCapability) -> {
            buttonBody.setHasJutsu(playerCapability.returnPlayerBijuuLevel() > 3);
        }).addPlayerEffect(EffectInit.BIJUU_MODE.get()).setModelOnRender(new PlayerModel(1F, false), new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/bijuu_mode.png")).setDojutsuModelOnRender(1, 1, new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/toadsageeyes.png")));

        bodyModeRegistry.register(new BeNMBody(this, "six_path_sage", 480, 48, (buttonBody, playerCapability) -> {
            buttonBody.setHasJutsu(playerCapability.returnPlayerLeftDojutsu() == DojutsuInit.RINNEGAN || playerCapability.returnPlayerRightDojutsu() == DojutsuInit.RINNEGAN);
        }).addPlayerEffect(EffectInit.SAGE_CHAKRA_REG.get()).addPlayerEffect(EffectInit.SIX_PATH_SAGE.get()).setAllowsPlayerFlight());
    }

    @Override
    public void registerNewJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry) {
        FireNatureJutsuInit.registerFireJutsu(jutsuRegistry, this);
        EarthNatureJutsuInit.registerEarthJutsu(jutsuRegistry, this);
        WaterNatureJutsuInit.registerWaterJutsu(jutsuRegistry, this);
        WindNatureJutsuInit.registerWindJutsu(jutsuRegistry, this);
        LightningNatureJutsuInit.registerLightningJutsu(jutsuRegistry, this);
        MagnetNatureJutsuInit.registerMagnetJutsu(jutsuRegistry, this);
        WoodNatureJutsuInit.registerWoodJutsu(jutsuRegistry, this);
        LavaNatureJutsuInit.registerLavaJutsu(jutsuRegistry, this);
        BoilNatureJutsuInit.registerBoilJutsu(jutsuRegistry, this);
        IceNatureJutsuInit.registerIceJutsu(jutsuRegistry, this);
        StormNatureJutsuInit.registerStormJutsu(jutsuRegistry, this);
        ScorchNatureJutsuInit.registerScorchJutsu(jutsuRegistry, this);
        ExplosionNatureJutsuInit.registerExplosionJutsu(jutsuRegistry, this);
        ERankJutsuInit.registerERankJutsu(jutsuRegistry, this);
        SharinganJutsuInit.registerSharinganJutsu(jutsuRegistry, this);
        SixPathJutsuInit.registerSixPathJutsu(jutsuRegistry, this);
        BijuuJutsuInit.registerBijuuJutsu(jutsuRegistry, this);
    }
}
