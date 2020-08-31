package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.world.dimension.TsukuyomiDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {

    public static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, Main.MODID);

    public static final ResourceLocation TSUKUYOMI_DIMENSION = new ResourceLocation(com.benarutomod.tbroski.Main.MODID, "tsukuyomi");
    public static final RegistryObject<ModDimension> TSUKUYOMI = DIMENSIONS.register("tsukuyomi", () -> new TsukuyomiDimension());
}
