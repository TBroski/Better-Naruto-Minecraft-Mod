package com.benarutomod.tbroski.world.biome;

import com.benarutomod.tbroski.common.IBeNMBiome;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.EntityInit;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class MountMyobokuBiome extends Biome implements IBeNMBiome {


    public MountMyobokuBiome() {
        super(new Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockInit.LIGNITE.get().getDefaultState(), BlockInit.LIGNITE.get().getDefaultState(), BlockInit.LIGNITE.get().getDefaultState())).precipitation(Biome.RainType.RAIN).category(Category.NONE).depth(2.0F).scale(0.5F).temperature(0.4F).downfall(0.2F).waterColor(0xCA8813).waterFogColor(0xCA8813).parent((String)null));
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addOres(this);
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.withConfiguration(DefaultBiomeFeatures.GRASS_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(10))));
        this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 90, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 8, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    @Override
    public float getTranquility() {
        return 1.4F;
    }
}
