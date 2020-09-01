package com.benarutomod.tbroski.util.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;

public class StaticFeatureHelper {
    
    public static void placePrison(PlayerEntity placer, BlockPos pos, BlockState state) {
        // Bottom
        if (placer.world.isAirBlock(pos.down()))
            placer.world.setBlockState(pos.down(), state);
        if (placer.world.isAirBlock(pos.down().south()))
            placer.world.setBlockState(pos.down().south(), state);
        if (placer.world.isAirBlock(pos.down().north()))
            placer.world.setBlockState(pos.down().north(), state);
        if (placer.world.isAirBlock(pos.down().east()))
            placer.world.setBlockState(pos.down().east(), state);
        if (placer.world.isAirBlock(pos.down().west()))
            placer.world.setBlockState(pos.down().west(), state);
        if (placer.world.isAirBlock(pos.down().south().east()))
            placer.world.setBlockState(pos.down().south().east(), state);
        if (placer.world.isAirBlock(pos.down().south().west()))
            placer.world.setBlockState(pos.down().south().west(), state);
        if (placer.world.isAirBlock(pos.down().north().east()))
            placer.world.setBlockState(pos.down().north().east(), state);
        if (placer.world.isAirBlock(pos.down().north().west()))
            placer.world.setBlockState(pos.down().north().west(), state);

        // Top
        if (placer.world.isAirBlock(pos.up(2)))
            placer.world.setBlockState(pos.up(2), state);
        if (placer.world.isAirBlock(pos.up(2).south()))
            placer.world.setBlockState(pos.up(2).south(), state);
        if (placer.world.isAirBlock(pos.up(2).north()))
            placer.world.setBlockState(pos.up(2).north(), state);
        if (placer.world.isAirBlock(pos.up(2).east()))
            placer.world.setBlockState(pos.up(2).east(), state);
        if (placer.world.isAirBlock(pos.up(2).west()))
            placer.world.setBlockState(pos.up(2).west(), state);
        if (placer.world.isAirBlock(pos.up(2).south().east()))
            placer.world.setBlockState(pos.up(2).south().east(), state);
        if (placer.world.isAirBlock(pos.up(2).south().west()))
            placer.world.setBlockState(pos.up(2).south().west(), state);
        if (placer.world.isAirBlock(pos.up(2).north().east()))
            placer.world.setBlockState(pos.up(2).north().east(), state);
        if (placer.world.isAirBlock(pos.up(2).north().west()))
            placer.world.setBlockState(pos.up(2).north().west(), state);

        // Bars
        if (placer.world.isAirBlock(pos.up().south().east()))
            placer.world.setBlockState(pos.up().south().east(), state);
        if (placer.world.isAirBlock(pos.up().south().west()))
            placer.world.setBlockState(pos.up().south().west(), state);
        if (placer.world.isAirBlock(pos.up().north().east()))
            placer.world.setBlockState(pos.up().north().east(), state);
        if (placer.world.isAirBlock(pos.up().north().west()))
            placer.world.setBlockState(pos.up().north().west(), state);

        // Sides
        if (placer.world.isAirBlock(pos.east()))
            placer.world.setBlockState(pos.east(), state);
        if (placer.world.isAirBlock(pos.west()))
            placer.world.setBlockState(pos.west(), state);
        if (placer.world.isAirBlock(pos.north()))
            placer.world.setBlockState(pos.north(), state);
        if (placer.world.isAirBlock(pos.south()))
            placer.world.setBlockState(pos.south(), state);
        if (placer.world.isAirBlock(pos.south().east()))
            placer.world.setBlockState(pos.south().east(), state);
        if (placer.world.isAirBlock(pos.south().west()))
            placer.world.setBlockState(pos.south().west(), state);
        if (placer.world.isAirBlock(pos.north().west()))
            placer.world.setBlockState(pos.north().west(), state);
        if (placer.world.isAirBlock(pos.north().east()))
            placer.world.setBlockState(pos.north().east(), state);
    }

    public static void placeWall(PlayerEntity placer, BlockPos pos, BlockState state, int modifier) {
        Iterable<BlockPos> blockPosIterable = BlockPos.getAllInBoxMutable(pos.west(modifier + 3), pos.east(modifier + 3).up(modifier + 4));
        for (BlockPos blockPos : blockPosIterable) {
            if (placer.world.isAirBlock(blockPos))
                placer.world.setBlockState(blockPos, state);
        }
    }

    public static void digHole(PlayerEntity placer, BlockPos pos, BlockState state, int size) {
        Iterable<BlockPos> blockPosList = BlockPos.getAllInBoxMutable(pos.west(2 + size).south(2 + size).down(1 + size), pos.east(2 + size).south(2 + size));
        for (BlockPos blockPos : blockPosList) {
            Vec2f point = new Vec2f(blockPos.getX(), blockPos.getZ());
            if ((point.x * point.x) + (point.y * point.y) - ((2 + size) * 2) < 0) {
                if (!placer.world.isAirBlock(blockPos) && placer.world.getBlockState(blockPos).getBlockHardness(placer.world, blockPos) < 2.0F) {
                    placer.world.setBlockState(blockPos, state);
                }
            }
        }
    }

    public static void placeDome(PlayerEntity placer, BlockPos pos, BlockState state) {
        Iterable<BlockPos> blockPosList = BlockPos.getAllInBoxMutable(pos.west(3).south(3), pos.east(3).north(3).up(5));
        for (BlockPos blockPos : blockPosList) {
            Vec2f point = new Vec2f(blockPos.getX(), blockPos.getZ());
            double p = (point.x * point.x) + (point.y * point.y) - 6;
            if (p < 1 && p > -1) {
                if (placer.world.isAirBlock(blockPos)) {
                    placer.world.setBlockState(blockPos, state);
                }
            }
        }
    }
}
