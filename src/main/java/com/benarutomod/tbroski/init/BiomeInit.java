package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.world.biome.MountMyobokuBiome;
import com.benarutomod.tbroski.world.biome.SakuraBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Main.MODID);

    public static final RegistryObject<Biome> SAKURA_BIOME = BIOMES.register("sakura", () -> new SakuraBiome());
    public static final RegistryObject<Biome> MOUNT_MYOBOKU_BIOME = BIOMES.register("mount_myoboku", () -> new MountMyobokuBiome());


    public static void registerBiomes() {
        registerBiome(SAKURA_BIOME.get(), 1000, BiomeDictionary.Type.SAVANNA, BiomeDictionary.Type.OVERWORLD);
        registerBiome(MOUNT_MYOBOKU_BIOME.get(), 1000, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.OVERWORLD);
    }

    private static void registerBiome(Biome biome, int weight, BiomeDictionary.Type... types) {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, weight));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }
}
