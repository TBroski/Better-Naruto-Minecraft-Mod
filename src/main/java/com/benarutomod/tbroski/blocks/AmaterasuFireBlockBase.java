package com.benarutomod.tbroski.blocks;

import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.DamageInit;
import com.benarutomod.tbroski.init.TileEntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketAmaterasuNBTSync;
import com.benarutomod.tbroski.tileentity.AmaterasuTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class AmaterasuFireBlockBase extends Block {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
    public static final BooleanProperty NORTH = SixWayBlock.NORTH;
    public static final BooleanProperty EAST = SixWayBlock.EAST;
    public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
    public static final BooleanProperty WEST = SixWayBlock.WEST;
    public static final BooleanProperty UP = SixWayBlock.UP;
    private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().stream().filter((p_199776_0_) -> {
        return p_199776_0_.getKey() != Direction.DOWN;
    }).collect(Util.toMapCollector());

    public AmaterasuFireBlockBase() {
        super(Block.Properties.create(Material.FIRE).hardnessAndResistance(0,0).doesNotBlockMovement().tickRandomly().lightValue(15).sound(SoundType.CLOTH).noDrops());
        this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)).with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)));
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        AmaterasuTileEntity tileEntity = TileEntityInit.AMATERASU.get().create();
        return tileEntity;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }


    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.isValidPosition(stateIn, worldIn, currentPos) ? this.getStateForPlacement(worldIn, currentPos).with(AGE, stateIn.get(AGE)) : Blocks.AIR.getDefaultState();
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getStateForPlacement(context.getWorld(), context.getPos());
    }

    public BlockState getStateForPlacement(IBlockReader p_196448_1_, BlockPos p_196448_2_) {
        BlockPos blockpos = p_196448_2_.down();
        BlockState blockstate = p_196448_1_.getBlockState(blockpos);
        if (!this.canCatchFire(p_196448_1_, p_196448_2_, Direction.UP) && !Block.hasSolidSide(blockstate, p_196448_1_, blockpos, Direction.UP)) {
            BlockState blockstate1 = this.getDefaultState();

            for(Direction direction : Direction.values()) {
                BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(direction);
                if (booleanproperty != null) {
                    blockstate1 = blockstate1.with(booleanproperty, Boolean.valueOf(this.canCatchFire(p_196448_1_, p_196448_2_.offset(direction), direction.getOpposite())));
                }
            }

            return blockstate1;
        } else {
            return this.getDefaultState();
        }
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP);
    }

    public int tickRate(IWorldReader worldIn) {
        return 30;
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            if (worldIn.getTileEntity(pos) != null) {
                LivingEntity livingEntity = (LivingEntity) worldIn.getEntityByID(worldIn.getTileEntity(pos).getTileData().getInt("amaterasuconnection"));
                if (livingEntity == null || !livingEntity.isAlive()) {
                    worldIn.removeBlock(pos, false);
                }
            }
        }
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (oldState.getBlock() != state.getBlock()) {
            if (worldIn.dimension.getType() != DimensionType.OVERWORLD && worldIn.dimension.getType() != DimensionType.THE_NETHER || !((NetherPortalBlock)Blocks.NETHER_PORTAL).trySpawnPortal(worldIn, pos)) {
                if (!this.isValidPosition(state, worldIn, pos)) {
                    worldIn.removeBlock(pos, false);
                } else {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(24) == 0) {
            worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
            for(int j = 0; j < 2; ++j) {
                double d3 = (double)pos.getX() + rand.nextDouble() * (double)0.1F;
                double d8 = (double)pos.getY() + rand.nextDouble();
                double d13 = (double)pos.getZ() + rand.nextDouble();
                worldIn.addParticle(ParticleTypes.LARGE_SMOKE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, NORTH, EAST, SOUTH, WEST, UP);
    }

    public boolean canCatchFire(IBlockReader world, BlockPos pos, Direction face) {
        return world.getBlockState(pos).isFlammable(world, pos, face);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.getPersistentData().putBoolean("amaterasufire", true);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity != null) entityIn.getPersistentData().putInt("amaterasuconnection", tileEntity.getTileData().getInt("amaterasuconnection"));
        if (tileEntity != null) entityIn.getPersistentData().putInt("amaterasuconnectionx", pos.getX());
        if (tileEntity != null) entityIn.getPersistentData().putInt("amaterasuconnectiony", pos.getY());
        if (tileEntity != null) entityIn.getPersistentData().putInt("amaterasuconnectionz", pos.getZ());
    }

    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        Entity connectedEntity = event.getEntityLiving().getEntityWorld().getEntityByID(event.getEntityLiving().getPersistentData().getInt("amaterasuconnection"));
        int x = event.getEntityLiving().getPersistentData().getInt("amaterasuconnectionx");
        int y = event.getEntityLiving().getPersistentData().getInt("amaterasuconnectiony");
        int z = event.getEntityLiving().getPersistentData().getInt("amaterasuconnectionz");
        BlockPos pos = new BlockPos(x,y,z);

        if (y != -1 || connectedEntity == null || !connectedEntity.isAlive() && (event.getEntityLiving().world.getBlockState(pos) != BlockInit.AMATERASU.get().getDefaultState())) {
            event.getEntityLiving().getPersistentData().putBoolean("amaterasufire", false);
        }
        if (event.getEntityLiving().getPersistentData().getBoolean("amaterasufire")) {
            event.getEntityLiving().getPersistentData().putInt("amaterasufiretick", event.getEntityLiving().getPersistentData().getInt("amaterasufiretick") + 1);
            if (event.getEntityLiving().getPersistentData().getInt("amaterasufiretick") >= 20) {
                event.getEntityLiving().getPersistentData().putInt("amaterasufiretick", 0);
                event.getEntityLiving().attackEntityFrom(DamageInit.AMATERASU, 1.0F);
            }
        }
        if (!event.getEntityLiving().world.isRemote) NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> event.getEntity()), new PacketAmaterasuNBTSync(event.getEntity().getEntityId(), event.getEntityLiving().getPersistentData().getBoolean("amaterasufire")));
    }
}
