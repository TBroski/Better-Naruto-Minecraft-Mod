package com.benarutomod.tbroski.world.gen.feature;

import com.benarutomod.tbroski.Main;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Function;

public class FourPillarHouseFeature extends Feature<NoFeatureConfig> {

    public FourPillarHouseFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld iworld, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
        int ci = pos.getX();
        int ck = pos.getZ();
        DimensionType dimensionType = iworld.getDimension().getType();
        boolean dimensionCriteria = false;
        if (dimensionType == DimensionType.OVERWORLD)
            dimensionCriteria = true;
        if (!dimensionCriteria)
            return false;
//        if ((random.nextInt(1000000) + 1) <= 10000) {
            int count = random.nextInt(1) + 1;
            for (int a = 0; a < count; a++) {
                int i = ci + random.nextInt(16) + 8;
                int k = ck + random.nextInt(16) + 8;
                //int j = iworld.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
                //j -= 1;
                Template template = ((ServerWorld) iworld.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation(Main.MODID, "four_pillar_house"));
                if (template == null)
                    return false;
                Rotation rotation = Rotation.values()[random.nextInt(3)];
                Mirror mirror = Mirror.values()[random.nextInt(2)];
                BlockPos spawnTo = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                template.addBlocksToWorldChunk(iworld, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK).setChunk((ChunkPos) null).setIgnoreEntities(false));
            }
 //       }
        return true;
    }

}
