package com.benarutomod.tbroski.world.gen.feature;

import com.benarutomod.tbroski.init.BlockInit;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IceSpikeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class LigniteHoodooFeature extends Feature<NoFeatureConfig> {

    public LigniteHoodooFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        while(worldIn.isAirBlock(pos) && pos.getY() > 2) {
            pos = pos.down();
        }

        boolean w = rand.nextInt(100) > 80;
        int h = rand.nextInt(12) + 14;

        for (int i = 0; i <= h; i += 1) {

            pos = pos.add(0, 1, 0);

            if (w && i < h / 2.5) {
                this.setBlockState(worldIn, pos, BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), BlockInit.LIGNITE.get().getDefaultState());
            }
            else {
                this.setBlockState(worldIn, pos, BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), BlockInit.LIGNITE.get().getDefaultState());
                this.setBlockState(worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), BlockInit.LIGNITE.get().getDefaultState());
            }
        }
        return true;
    }
}
