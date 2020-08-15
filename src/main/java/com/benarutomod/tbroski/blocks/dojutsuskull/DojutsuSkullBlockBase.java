package com.benarutomod.tbroski.blocks.dojutsuskull;

import com.benarutomod.tbroski.client.gui.DojutsuTransplant;
import com.benarutomod.tbroski.api.interfaces.IDojutsuEntity;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class DojutsuSkullBlockBase extends ContainerBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

    public DojutsuSkullBlockBase() {
        super(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.1F));
        this.setDefaultState(this.stateContainer.getBaseState().with(ROTATION, Integer.valueOf(0)));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new DojutsuSkullTileEntity();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }


    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(ROTATION, Integer.valueOf(MathHelper.floor((double)(context.getPlacementYaw() * 16.0F / 360.0F) + 0.5D) & 15));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ROTATION, Integer.valueOf(rot.rotate(state.get(ROTATION), 16)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ROTATION, Integer.valueOf(mirrorIn.mirrorRotation(state.get(ROTATION), 16)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (worldIn.isRemote && tileEntity instanceof DojutsuSkullTileEntity && ((DojutsuSkullTileEntity) tileEntity).getLivingEntity() instanceof IDojutsuEntity) {
            Minecraft.getInstance().displayGuiScreen(new DojutsuTransplant<>((DojutsuSkullTileEntity) tileEntity));
        }
        return ActionResultType.SUCCESS;
    }
}
