package com.benarutomod.tbroski.fluid;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.FluidInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class ToadOilFluid extends FlowingFluid {
    @Override
    public Fluid getFlowingFluid() {
        return FluidInit.FLOWING_TOAD_OIL.get();
    }

    @Override
    public Fluid getStillFluid() {
        return FluidInit.TOAD_OIL.get();
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
        TileEntity tileentity = state.getBlock().hasTileEntity() ? worldIn.getTileEntity(pos) : null;
        Block.spawnDrops(state, worldIn.getWorld(), pos, tileentity);
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader worldIn) {
        return 2;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
        return 2;
    }

    @Override
    public Item getFilledBucket() {
        return ItemInit.TOAD_OIL_BUCKET_ITEM.get();
    }

    @Override
    protected boolean canDisplace(IFluidState p_215665_1_, IBlockReader p_215665_2_, BlockPos p_215665_3_, Fluid p_215665_4_, Direction p_215665_5_) {
        return p_215665_5_ == Direction.DOWN && p_215665_4_.isIn(FluidInit.Tags.TOAD_OIL);
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        return 40;
    }

    @Override
    protected float getExplosionResistance() {
        return 100F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return FluidInit.TOAD_OIL_BLOCK.get().getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == FluidInit.TOAD_OIL.get() || fluidIn == FluidInit.FLOWING_TOAD_OIL.get();
    }

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(new ResourceLocation(Main.MODID, "blocks/toad_oil_still"), new ResourceLocation(Main.MODID, "blocks/toad_oil_flow")).build(this);
    }

    public static class Flowing extends ToadOilFluid {
        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }

        @Override
        public int getLevel(IFluidState p_207192_1_) {
            return p_207192_1_.get(LEVEL_1_8);
        }
    }

    public static class Source extends ToadOilFluid {

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }

        @Override
        public int getLevel(IFluidState p_207192_1_) {
            return 8;
        }
    }
}
