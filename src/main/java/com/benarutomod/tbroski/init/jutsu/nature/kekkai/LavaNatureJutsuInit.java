package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.entity.clones.LavaCloneEntity;
import com.benarutomod.tbroski.entity.clones.WoodCloneEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lava.LavaShurikenEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lava.ScorchingStreamRockEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import com.benarutomod.tbroski.util.helpers.StaticFeatureHelper;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class LavaNatureJutsuInit {

    public static void registerLavaJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "scorching_stream_rock", BeNMJutsu.Type.LAVA_NATURE, 10, 150F, 128, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                ScorchingStreamRockEntity entity = new ScorchingStreamRockEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.SCORCHING_STREAM_ROCK_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.4F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lava_wall", BeNMJutsu.Type.LAVA_NATURE, 7, 100F, 128, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
                if (blockRayTraceResult != null) {
                    StaticFeatureHelper.placeWall(playerIn, blockRayTraceResult.getPos(), Fluids.LAVA.getDefaultState().getBlockState(), (int) playerCapability.returnChakraControl());
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceBlocks(playerIn, 6F) != null));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lava_shuriken", BeNMJutsu.Type.LAVA_NATURE, 6, 30F, 128, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                LavaShurikenEntity entity = new LavaShurikenEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.SCORCHING_STREAM_ROCK_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "great_eruption", BeNMJutsu.Type.LAVA_NATURE, 15, 200F, 128, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    BlockPos entityPos = getClosestBelowBlock(playerIn.world, entityRayTraceResult.getEntity().getPosition());
                    StaticFeatureHelper.digHole(playerIn, entityPos, Fluids.LAVA.getDefaultState().getBlockState(), taijutsuModifier0);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceEntities(playerIn, 6F) != null));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lava_clone", BeNMJutsu.Type.LAVA_NATURE, 10, 120F, 128, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                LavaCloneEntity entity = new LavaCloneEntity(EntityInit.LAVA_CLONE.get(), playerIn.world);
                entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                entity.setOwnerID(playerIn.getEntityId());
                playerIn.world.addEntity(entity);
            }
        }));
    }


    private static BlockPos getClosestBelowBlock(IWorld worldIn, BlockPos posIn) {
        int i = posIn.getY();
        while (i > 0) {
            if (!worldIn.isAirBlock(posIn.down(posIn.getY() - i))) {
                posIn = posIn.down(posIn.getY() - i);
                break;
            }
            if (i == 1)
                return null;
            i--;
        }
        return posIn;
    }
}
