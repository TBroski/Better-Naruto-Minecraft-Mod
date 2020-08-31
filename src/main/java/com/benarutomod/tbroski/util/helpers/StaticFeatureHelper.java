package com.benarutomod.tbroski.util.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

public class StaticFeatureHelper {
    
    public static void placePrison(PlayerEntity placer, BlockPos pos, BlockState state) {
        // Bottom
        if (placer.world.isAirBlock(pos.down()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().south()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().north()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().east()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().west()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().south().east()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().south().west()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().north().east()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().north().west()))
            placer.world.setBlockState(pos.down(), state);

        // Top
        if (placer.world.isAirBlock(pos.up()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().south()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().north()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().east()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().west()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().south().east()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().south().west()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().north().east()))
            placer.world.setBlockState(pos.up(), state);
        if (placer.world.isAirBlock(pos.up().north().west()))
            placer.world.setBlockState(pos.up(), state);

        // Sides
        if (placer.world.isAirBlock(pos.east()))
            placer.world.setBlockState(pos.east(), state);
        if (placer.world.isAirBlock(pos.west()))
            placer.world.setBlockState(pos.west(), state);
        if (placer.world.isAirBlock(pos.north()))
            placer.world.setBlockState(pos.north(), state);
        if (placer.world.isAirBlock(pos.south()))
            placer.world.setBlockState(pos.south(), state);
    }

    public static void placeWall(PlayerEntity placer, BlockPos pos, BlockState state, int modifier) {
        Iterable<BlockPos> blockPosIterable = BlockPos.getAllInBoxMutable(pos.west(modifier + 3), pos.east(modifier + 3).up(modifier + 4));
        for (BlockPos blockPos : blockPosIterable) {
            if (placer.world.isAirBlock(blockPos))
                placer.world.setBlockState(blockPos, state);
        }
    }
}
