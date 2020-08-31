package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.effects.BijuuModeEffect;
import com.benarutomod.tbroski.effects.ChakraRegEffect;
import com.benarutomod.tbroski.effects.SageChakraEffect;
import com.benarutomod.tbroski.effects.TsukuyomiEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {

    public static final DeferredRegister<Effect> EFFECT = new DeferredRegister<>(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> CHAKRA_REG = EFFECT.register("chakra_reg", ChakraRegEffect::new);
    public static final RegistryObject<Effect> SAGE_CHAKRA_REG = EFFECT.register("sage_chakra_reg", SageChakraEffect::new);
    public static final RegistryObject<Effect> TSUKUYOMI = EFFECT.register("tsukuyomi", TsukuyomiEffect::new);
    public static final RegistryObject<Effect> BIJUU_MODE = EFFECT.register("bijuu_mode", BijuuModeEffect::new);
}
