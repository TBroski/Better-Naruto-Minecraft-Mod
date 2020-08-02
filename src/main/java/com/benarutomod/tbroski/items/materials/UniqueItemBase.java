package com.benarutomod.tbroski.items.materials;

import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class UniqueItemBase extends Item {
    public UniqueItemBase() {
        super(new Item.Properties());
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        if (!world.isRemote && !world.isAirBlock(pos) && world.getBlockState(pos).getBlockHardness(world, pos) <= 0.5F) {
            FallingBlockEntity blockEntity = new FallingBlockEntity(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
            float yaw = player.rotationYaw;
            float pitch = 0;
            float velocity = 1.0F;
            double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
            double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
            double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
            blockEntity.setVelocity(motionX, motionY + 0.6D, motionZ);
            world.addEntity(blockEntity);
        }
        return super.onItemUse(context);
    }
}
