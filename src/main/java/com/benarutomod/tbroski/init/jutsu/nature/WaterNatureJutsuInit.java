package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.RagingWavesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterSharkBulletEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterShurikenEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import com.benarutomod.tbroski.util.helpers.StaticFeatureHelper;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockRayTraceResult;

public class WaterNatureJutsuInit {

    public static void registerWaterJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "water_shuriken", BeNMJutsu.Type.WATER_NATURE, 4, 20F, 32, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                WaterShurikenEntity entity = new WaterShurikenEntity(playerIn.world, playerIn);
                entity.setItem(new ItemStack(ItemInit.WATER_SHURIKEN_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.75F, 0.5F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "raging_waves", BeNMJutsu.Type.WATER_NATURE, 6, 45F, 32, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                RagingWavesEntity entity = new RagingWavesEntity(playerIn.world, playerIn);
                entity.setItem(new ItemStack(ItemInit.RAGING_WAVES_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "water_shark_bullet", BeNMJutsu.Type.WATER_NATURE, 8, 100F, 32, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                WaterSharkBulletEntity entity = new WaterSharkBulletEntity(playerIn.world, playerIn);
                entity.setItem(new ItemStack(ItemInit.WATER_SHARK_BULLET_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "stormy_blockade", BeNMJutsu.Type.WATER_NATURE, 7, 100F, 32, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
                if (blockRayTraceResult != null) {
                    StaticFeatureHelper.placeWall(playerIn, blockRayTraceResult.getPos(), Fluids.WATER.getDefaultState().getBlockState(), (int) playerCapability.returnChakraControl());
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceBlocks(playerIn, 6F) != null));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "exploding_water_colliding_wave", BeNMJutsu.Type.WATER_NATURE, 17, 1.5F, 32, 64, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                playerIn.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 40));
                if (playerIn.world.isAirBlock(playerIn.getPosition()))
                    playerIn.world.setBlockState(playerIn.getPosition(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().up()))
                    playerIn.world.setBlockState(playerIn.getPosition().up(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().up(2)))
                    playerIn.world.setBlockState(playerIn.getPosition().up(2), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().down()))
                    playerIn.world.setBlockState(playerIn.getPosition().down(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().east()))
                    playerIn.world.setBlockState(playerIn.getPosition().east(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().east().up()))
                    playerIn.world.setBlockState(playerIn.getPosition().east().up(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().east().up(2)))
                    playerIn.world.setBlockState(playerIn.getPosition().east().up(2), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().west()))
                    playerIn.world.setBlockState(playerIn.getPosition().west(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().west().up()))
                    playerIn.world.setBlockState(playerIn.getPosition().west().up(), Fluids.WATER.getDefaultState().getBlockState());
                if (playerIn.world.isAirBlock(playerIn.getPosition().west().up(2)))
                    playerIn.world.setBlockState(playerIn.getPosition().west().up(2), Fluids.WATER.getDefaultState().getBlockState());
            }
        }));
    }
}
