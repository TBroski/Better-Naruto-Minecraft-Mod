package com.benarutomod.tbroski.blocks.trees;

import com.benarutomod.tbroski.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.AcaciaTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.ForestBiome;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class SakuraTree extends Tree {

    public static final TreeFeatureConfig SAKURA_TREE_CONFIG = new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.SAKURA_LOG.get().getDefaultState()), new SimpleBlockStateProvider(BlockInit.SAKURA_LEAVES.get().getDefaultState()), new AcaciaFoliagePlacer(2, 0)).baseHeight(5).heightRandA(2).heightRandB(2).trunkHeight(0).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) Blocks.ACACIA_SAPLING).build(); //.setSapling((net.minecraftforge.common.IPlantable) BlockInit.SAKURA_SAPLING.get())

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.ACACIA_TREE.withConfiguration(SAKURA_TREE_CONFIG); //Feature.NORMAL_TREE.withConfiguration(SAKURA_TREE_CONFIG)
    }
}
