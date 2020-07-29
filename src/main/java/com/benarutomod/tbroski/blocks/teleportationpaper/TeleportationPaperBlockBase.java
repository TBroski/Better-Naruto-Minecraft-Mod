package com.benarutomod.tbroski.blocks.teleportationpaper;

import com.benarutomod.tbroski.init.TileEntityInit;
import com.benarutomod.tbroski.tileentity.TeleportationPaperTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TeleportationPaperBlockBase extends FallingBlock {

    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public TeleportationPaperBlockBase() {
        super(Block.Properties.create(Material.WOOL)
                .hardnessAndResistance(0.1F, 0.1F)
                .sound(SoundType.GROUND)
                .notSolid());
    }


    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        return TileEntityInit.TELEPORTATION_PAPER.get().create();
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }



    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        //System.out.println("COLLIDED");
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TeleportationPaperTileEntity)
        {
            ((TeleportationPaperTileEntity) tileentity).setIftimer(false);
            ((TeleportationPaperTileEntity) tileentity).setTeletimer(0);
            tileentity.setPos(pos);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        //System.out.println(placer);

        if (tileentity instanceof TeleportationPaperTileEntity)
        {
            ((TeleportationPaperTileEntity) tileentity).setPlacer(placer);
            ((TeleportationPaperTileEntity) tileentity).setIftimer(true);
            ((TeleportationPaperTileEntity) tileentity).setTeletimer(0);
            tileentity.setPos(pos);
        }
    }
}
