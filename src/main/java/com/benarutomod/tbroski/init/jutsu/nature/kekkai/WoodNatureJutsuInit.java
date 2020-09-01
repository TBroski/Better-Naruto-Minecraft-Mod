package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.entity.clones.WoodCloneEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.FeatureInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import com.benarutomod.tbroski.util.helpers.StaticFeatureHelper;
import net.minecraft.block.*;
import net.minecraft.block.trees.OakTree;
import net.minecraft.util.math.*;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

public class WoodNatureJutsuInit {

    public static void registerWoodJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wood_clone", BeNMJutsu.Type.WOOD_NATURE, 13, 120F, 112, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                WoodCloneEntity entity = new WoodCloneEntity(EntityInit.WOOD_CLONE.get(), playerIn.world);
                entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                entity.setOwnerID(playerIn.getEntityId());
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tree_summoning", BeNMJutsu.Type.WOOD_NATURE, 10, 80F, 112, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                Vec3d vec = playerIn.getPositionVector();
                Vec3d vec3 = new Vec3d(vec.x,vec.y + playerIn.getEyeHeight(),vec.z);
                Vec3d vec3a = playerIn.getLook(1.0F);
                Vec3d vec3b = vec3.add(vec3a.getX() * 5, vec3a.getY() *  5, vec3a.getZ() *  5);
                BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
                if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos())) {
                    if (playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlock() instanceof SaplingBlock) {
                        ((SaplingBlock) playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlock()).grow((ServerWorld) playerIn.world, playerIn.getRNG(), blockRayTraceResult.getPos(), playerIn.world.getBlockState(blockRayTraceResult.getPos()));
                        return;
                    }
                    BlockPos newPos = new BlockPos(blockRayTraceResult.getPos().getX(), blockRayTraceResult.getPos().getY() + 1, blockRayTraceResult.getPos().getZ());
                    OakTree tree = new OakTree();
                    tree.func_225545_a_((ServerWorld) playerIn.world, ((ServerWorld) playerIn.world).getChunkProvider().getChunkGenerator(), newPos, playerIn.world.getBlockState(newPos), playerIn.getRNG());
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            Vec3d vec = playerIn.getPositionVector();
            Vec3d vec3 = new Vec3d(vec.x,vec.y + playerIn.getEyeHeight(),vec.z);
            Vec3d vec3a = playerIn.getLook(1.0F);
            Vec3d vec3b = vec3.add(vec3a.getX() * 5, vec3a.getY() *  5, vec3a.getZ() *  5);
            BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
            if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) && playerIn.world.isAirBlock(new BlockPos(blockRayTraceResult.getPos().getX(), blockRayTraceResult.getPos().getY() + 1, blockRayTraceResult.getPos().getZ()))) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "sea_of_trees", BeNMJutsu.Type.WOOD_NATURE, 20, 300F, 112, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                if (playerIn.world.getBlockState(playerIn.getPosition().down()).getBlock() instanceof IGrowable) {
                    ((IGrowable) playerIn.world.getBlockState(playerIn.getPosition().down())).grow((ServerWorld) playerIn.world, playerIn.getRNG(), playerIn.getPosition().down(), playerIn.world.getBlockState(playerIn.getPosition().down()));
                }
                if (playerIn.world.getBlockState(playerIn.getPosition()) instanceof IGrowable) {
                    ((IGrowable) playerIn.world.getBlockState(playerIn.getPosition())).grow((ServerWorld) playerIn.world, playerIn.getRNG(), playerIn.getPosition(), playerIn.world.getBlockState(playerIn.getPosition()));
                }
                AxisAlignedBB box = playerIn.getBoundingBox().grow(5);
                Iterable<BlockPos> blockPos = BlockPos.getAllInBoxMutable(MathHelper.floor(box.minX), MathHelper.floor(box.minY), MathHelper.floor(box.minZ), MathHelper.floor(box.maxX), MathHelper.floor(box.maxY), MathHelper.floor(box.maxZ));
                for (BlockPos pos : blockPos) {
                    if (playerIn.world.getBlockState(pos) instanceof IGrowable) {
                        ((IGrowable) playerIn.world.getBlockState(pos)).grow((ServerWorld) playerIn.world, playerIn.getRNG(), pos, playerIn.world.getBlockState(pos));
                    }
                    if (playerIn.getRNG().nextInt(3) == 0 && playerIn.world.isAirBlock(pos) && playerIn.world.getBlockState(pos.down()).getBlock() instanceof GrassBlock) {
                        OakTree tree = new OakTree();
                        tree.func_225545_a_((ServerWorld) playerIn.world, ((ServerWorld) playerIn.world).getChunkProvider().getChunkGenerator(), pos, playerIn.world.getBlockState(pos), playerIn.getRNG());
                    }
                }
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "four_pillar_prison", BeNMJutsu.Type.WOOD_NATURE, 15, 270F, 112, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    BlockPos setPos = entityRayTraceResult.getEntity().getPosition();
                    entityRayTraceResult.getEntity().setPosition(setPos.getX(), setPos.getY(), setPos.getZ());
                    StaticFeatureHelper.placePrison(playerIn, setPos, Blocks.OAK_LOG.getDefaultState());
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            if (!playerIn.world.isRemote)
                return RayTraceHelper.rayTraceEntities(playerIn, 6F) != null;
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "four_pillar_house", BeNMJutsu.Type.WOOD_NATURE, 22, 320F, 112, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
                if (blockRayTraceResult != null) {
                    FeatureInit.FOUR_PILLAR_HOUSE.get().place((ServerWorld) playerIn.world, ((ServerWorld) playerIn.world).getChunkProvider().getChunkGenerator(), playerIn.getRNG(), blockRayTraceResult.getPos().up(), new NoFeatureConfig());
                }
                /*                Template template = ((ServerWorld) playerIn.world).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation(Main, "download_(14)"));
                if (template != null) {
                    template.addBlocksToWorldChunk(playerIn.world, new BlockPos((int) x, (int) y, (int) z), new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
                }*/
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            if (!playerIn.world.isRemote)
                return RayTraceHelper.rayTraceBlocks(playerIn, 6F) != null;
            return false;
        }));
    }
}
