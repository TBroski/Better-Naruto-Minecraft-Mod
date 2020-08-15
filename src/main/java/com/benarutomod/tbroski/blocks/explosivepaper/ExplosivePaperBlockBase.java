package com.benarutomod.tbroski.blocks.explosivepaper;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ExplosivePaperBlockBase extends FallingBlock {
    @Nullable LivingEntity placer;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public ExplosivePaperBlockBase() {
        super(Block.Properties.create(Material.WOOL)
                .hardnessAndResistance(0.1F, 0.1F)
                .sound(SoundType.GROUND)
                .notSolid());
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }



    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.FALL, 4F);
        entityIn.setMotion(entityIn.getMotion().getX(), 1D, entityIn.getMotion().getZ());
        worldIn.createExplosion(entityIn, pos.getX(), pos.getY(), pos.getZ(), 3F, Explosion.Mode.DESTROY);
        if (this.placer != null) {
            this.placer.sendMessage(new StringTextComponent("A Explosive Sealed Paper Trap has been Activated."));
        }
        this.placer = null;
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        if (this.placer != null) {
            this.placer.sendMessage(new StringTextComponent("A Explosive Sealed Paper Trap has been Destroyed."));
        }
        this.placer = null;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        this.placer = placer;
    }
}
