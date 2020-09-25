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
        Iterable<BlockPos> blockPosList = BlockPos.getAllInBoxMutable(pos.west(2 + size).south(2 + size).down(1 + size), pos.east(2 + size).north(2 + size));
        for (BlockPos blockPos : blockPosList) {
            float dx = blockPos.getX() - pos.getX();
            float dy = blockPos.getY() - pos.getY();
            float dz = blockPos.getZ() - pos.getZ();

            float d = (float) Math.sqrt((dx * dx) + (dz * dz) + (dy * dy));
            if (d < 5F) {
                if (placer.world.getBlockState(blockPos).getBlockHardness(placer.world, blockPos) < 1.0F)
                    placer.world.setBlockState(blockPos, state);
            }
        }
    }

    public static void placeDome(PlayerEntity placer, BlockPos pos, BlockState state) {
        Iterable<BlockPos> blockPosList = BlockPos.getAllInBoxMutable(pos.west(3).south(3), pos.east(3).north(3).up(5));
        for (BlockPos blockPos : blockPosList) {
            float dx = blockPos.getX() - pos.getX();
            float dy = blockPos.getY() - pos.getY();
            float dz = blockPos.getZ() - pos.getZ();

            float d = (float) Math.sqrt((dx * dx) + (dz * dz) + (dy * dy));
            if (d < 5F && d > 2.5F) {
                if (placer.world.isAirBlock(blockPos))
                    placer.world.setBlockState(blockPos, state);
            }
        }
    }


    public static void placeRing(PlayerEntity placer, BlockPos pos, BlockState state) {
        //Top
        if (placer.world.isAirBlock(pos.north(2)))
            placer.world.setBlockState(pos.north(2), state);
        if (placer.world.isAirBlock(pos.north(3)))
            placer.world.setBlockState(pos.north(3), state);
        if (placer.world.isAirBlock(pos.west().north(2)))
            placer.world.setBlockState(pos.west().north(2), state);
        if (placer.world.isAirBlock(pos.west().north(3)))
            placer.world.setBlockState(pos.west().north(3), state);
        if (placer.world.isAirBlock(pos.west(2).north(2)))
            placer.world.setBlockState(pos.west(2).north(2), state);
        if (placer.world.isAirBlock(pos.west(2).north(3)))
            placer.world.setBlockState(pos.west(2).north(3), state);
        if (placer.world.isAirBlock(pos.west(3).north(2)))
            placer.world.setBlockState(pos.west(3).north(2), state);
        if (placer.world.isAirBlock(pos.west(3).north(3)))
            placer.world.setBlockState(pos.west(3).north(3), state);
        if (placer.world.isAirBlock(pos.east().north(2)))
            placer.world.setBlockState(pos.east().north(2), state);
        if (placer.world.isAirBlock(pos.east().north(3)))
            placer.world.setBlockState(pos.east().north(3), state);
        if (placer.world.isAirBlock(pos.east(2).north(2)))
            placer.world.setBlockState(pos.east(2).north(2), state);
        if (placer.world.isAirBlock(pos.east(2).north(3)))
            placer.world.setBlockState(pos.east(2).north(3), state);
        if (placer.world.isAirBlock(pos.east(3).north(2)))
            placer.world.setBlockState(pos.east(3).north(2), state);
        if (placer.world.isAirBlock(pos.east(3).north(3)))
            placer.world.setBlockState(pos.east(3).north(3), state);

        //Bottom
        if (placer.world.isAirBlock(pos.south(2)))
            placer.world.setBlockState(pos.south(2), state);
        if (placer.world.isAirBlock(pos.south(3)))
            placer.world.setBlockState(pos.south(3), state);
        if (placer.world.isAirBlock(pos.west().south(2)))
            placer.world.setBlockState(pos.west().south(2), state);
        if (placer.world.isAirBlock(pos.west().south(3)))
            placer.world.setBlockState(pos.west().south(3), state);
        if (placer.world.isAirBlock(pos.west(2).south(2)))
            placer.world.setBlockState(pos.west(2).south(2), state);
        if (placer.world.isAirBlock(pos.west(2).south(3)))
            placer.world.setBlockState(pos.west(2).south(3), state);
        if (placer.world.isAirBlock(pos.west(3).south(2)))
            placer.world.setBlockState(pos.west(3).south(2), state);
        if (placer.world.isAirBlock(pos.west(3).south(3)))
            placer.world.setBlockState(pos.west(3).south(3), state);
        if (placer.world.isAirBlock(pos.east().south(2)))
            placer.world.setBlockState(pos.east().south(2), state);
        if (placer.world.isAirBlock(pos.east().south(3)))
            placer.world.setBlockState(pos.east().south(3), state);
        if (placer.world.isAirBlock(pos.east(2).south(2)))
            placer.world.setBlockState(pos.east(2).south(2), state);
        if (placer.world.isAirBlock(pos.east(2).south(3)))
            placer.world.setBlockState(pos.east(2).south(3), state);
        if (placer.world.isAirBlock(pos.east(3).south(2)))
            placer.world.setBlockState(pos.east(3).south(2), state);
        if (placer.world.isAirBlock(pos.east(3).south(3)))
            placer.world.setBlockState(pos.east(3).south(3), state);

        //Left
        if (placer.world.isAirBlock(pos.west(2)))
            placer.world.setBlockState(pos.west(2), state);
        if (placer.world.isAirBlock(pos.west(3)))
            placer.world.setBlockState(pos.west(3), state);
        if (placer.world.isAirBlock(pos.north().west(2)))
            placer.world.setBlockState(pos.north().west(2), state);
        if (placer.world.isAirBlock(pos.north().west(3)))
            placer.world.setBlockState(pos.north().west(3), state);
        if (placer.world.isAirBlock(pos.south().west(2)))
            placer.world.setBlockState(pos.south().west(2), state);
        if (placer.world.isAirBlock(pos.south().west(3)))
            placer.world.setBlockState(pos.south().west(3), state);

        //Right
        if (placer.world.isAirBlock(pos.east(2)))
            placer.world.setBlockState(pos.east(2), state);
        if (placer.world.isAirBlock(pos.east(3)))
            placer.world.setBlockState(pos.east(3), state);
        if (placer.world.isAirBlock(pos.north().east(2)))
            placer.world.setBlockState(pos.north().east(2), state);
        if (placer.world.isAirBlock(pos.north().east(3)))
            placer.world.setBlockState(pos.north().east(3), state);
        if (placer.world.isAirBlock(pos.south().east(2)))
            placer.world.setBlockState(pos.south().east(2), state);
        if (placer.world.isAirBlock(pos.south().east(3)))
            placer.world.setBlockState(pos.south().east(3), state);
    }
}
