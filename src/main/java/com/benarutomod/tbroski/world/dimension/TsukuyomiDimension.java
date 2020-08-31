package com.benarutomod.tbroski.world.dimension;

import com.benarutomod.tbroski.api.interfaces.IBeNMBiome;
import com.benarutomod.tbroski.init.BiomeInit;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.ModDimension;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.function.BiFunction;

public class TsukuyomiDimension extends ModDimension {
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return Factory::new;
    }

    public static class Factory extends Dimension {

        public Factory(World p_i225788_1_, DimensionType p_i225788_2_) {
            super(p_i225788_1_, p_i225788_2_, 0F);
        }

        @Override
        public ChunkGenerator<?> createChunkGenerator() {
            return new Generator(this.world, new Provider(), new Settings());
        }

        @Nullable
        @Override
        public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
            return null;
        }

        @Nullable
        @Override
        public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
            return null;
        }

        @Override
        public float calculateCelestialAngle(long worldTime, float partialTicks) {
            return 0;
        }

        @Override
        public boolean isSurfaceWorld() {
            return false;
        }

        @Override
        public Vec3d getFogColor(float celestialAngle, float partialTicks) {
            return new Vec3d(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue());
        }

        @Override
        public boolean canRespawnHere() {
            return false;
        }

        @Override
        public boolean doesXZShowFog(int x, int z) {
            return true;
        }

        @Override
        public boolean doesWaterVaporize() {
            return true;
        }

        @Override
        public boolean canMineBlock(PlayerEntity player, BlockPos pos) {
            return false;
        }

        @Override
        public int getActualHeight() {
            return 32;
        }
    }

    private static class Generator extends ChunkGenerator<Settings> {

        public Generator(IWorld worldIn, BiomeProvider biomeProviderIn, Settings generationSettingsIn) {
            super(worldIn, biomeProviderIn, generationSettingsIn);
        }

        @Override
        public void func_225551_a_(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {

        }

        @Override
        public int getGroundHeight() {
            return 2;
        }

        @Override
        public void makeBase(IWorld worldIn, IChunk chunkIn) {
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
            Heightmap heightmap = chunkIn.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
            Heightmap heightmap1 = chunkIn.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
            for(int j = 0; j < 16; ++j) {
                BlockState blockstate = this.settings.getDefaultBlock();
                for(int k = 0; k < 16; ++k) {
                    chunkIn.setBlockState(blockpos$mutable.setPos(j, 0, k), blockstate, false);
                    heightmap.update(j, 0, k, blockstate);
                    heightmap1.update(j, 0, k, blockstate);
                }
            }
        }

        @Override
        public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
            return 0;
        }
    }

    private static class Settings extends GenerationSettings {
        @Override
        public BlockState getDefaultBlock() {
            return Blocks.RED_SAND.getDefaultState();
        }
    }

    private static class Provider extends BiomeProvider {

        protected Provider() {
            super(ImmutableSet.of(BiomeInit.TSUKUYOMI.get()));
        }

        @Override
        public net.minecraft.world.biome.Biome getNoiseBiome(int x, int y, int z) {
            return BiomeInit.TSUKUYOMI.get();
        }
    }

    public static class Biome extends net.minecraft.world.biome.Biome implements IBeNMBiome {
        public Biome() {
            super(new net.minecraft.world.biome.Biome.Builder().surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState())).precipitation(RainType.NONE).category(Category.NONE).depth(0.0F).scale(0.5F).temperature(0.4F).waterColor(0xCA8813).waterFogColor(0xCA8813).downfall(0.0F).parent((String)null));
        }

        @Override
        public float getTranquility() {
            return 0.1F;
        }
    }
}
