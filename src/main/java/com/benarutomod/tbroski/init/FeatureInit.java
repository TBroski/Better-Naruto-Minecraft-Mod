package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.world.gen.feature.FourPillarHouseFeature;
import com.benarutomod.tbroski.world.gen.feature.LigniteHoodooFeature;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureInit {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, Main.MODID);


    public static final RegistryObject<LigniteHoodooFeature> LIGNITE_HOODOO = FEATURES.register("lignite_hoodoo", () -> new LigniteHoodooFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<FourPillarHouseFeature> FOUR_PILLAR_HOUSE = FEATURES.register("four_pillar_house", () -> new FourPillarHouseFeature(NoFeatureConfig::deserialize));


    public static void registerBiomeFeatures() {
        registerBiomeFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, LIGNITE_HOODOO.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP.configure(new FrequencyConfig(8))), BiomeInit.MOUNT_MYOBOKU_BIOME.get());
        registerBiomeFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(FluidInit.TOAD_OIL_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(5))), BiomeInit.MOUNT_MYOBOKU_BIOME.get());
    }

    public static void registerBiomeFeature(GenerationStage.Decoration decorationStage, ConfiguredFeature<?, ?> featureIn, Biome... biomes) {
        for (Biome biome : biomes) {
            biome.addFeature(decorationStage, featureIn);
        }
    }
}
