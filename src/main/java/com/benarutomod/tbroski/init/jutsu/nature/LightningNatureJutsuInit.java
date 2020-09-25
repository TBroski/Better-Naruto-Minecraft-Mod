package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.KirinEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningBallEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;

import java.util.Iterator;
import java.util.List;

public class LightningNatureJutsuInit {

    public static void registerLightningJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lightning_ball", BeNMJutsu.Type.LIGHTNING_NATURE, 4, 50F, 48, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            LightningBallEntity entity = new LightningBallEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.LIGHTNING_BALL_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "stun_gun", BeNMJutsu.Type.LIGHTNING_NATURE, 6, 40F, 48, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            List<LivingEntity> entities = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().grow(6));
            Iterator iterator = entities.iterator();
            while (iterator.hasNext()) {
                LivingEntity entity = (LivingEntity) iterator.next();
                if (entity != playerIn) {
                    entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1F);
                    entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 50, 1));
                }
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lightning_arrow", BeNMJutsu.Type.LIGHTNING_NATURE, 5, 40F, 48, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            LightningArrowEntity entity = new LightningArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.LIGHTNING_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "kirin", BeNMJutsu.Type.LIGHTNING_NATURE, 10, 250F, 48, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                playerIn.world.getWorldInfo().setClearWeatherTime(0);
                playerIn.world.getWorldInfo().setRainTime(400);
                playerIn.world.getWorldInfo().setThunderTime(400);
                playerIn.world.getWorldInfo().setRaining(true);
                playerIn.world.getWorldInfo().setThundering(true);
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    Entity entityTarget = entityRayTraceResult.getEntity();
                    KirinEntity entity = new KirinEntity(playerIn, playerIn.world);
                    entity.setItem(new ItemStack(ItemInit.KIRIN_JUTSU.get()));
                    entity.setPosition(entityTarget.getPosX(), entityTarget.getPosY() + 40, entity.getPosZ());
                    entity.setMotion(0, -1.1, 0);
                    playerIn.world.addEntity(entity);
                } else if (blockRayTraceResult != null) {
                    BlockPos pos = blockRayTraceResult.getPos();
                    KirinEntity entity = new KirinEntity(playerIn, playerIn.world);
                    entity.setItem(new ItemStack(ItemInit.KIRIN_JUTSU.get()));
                    entity.setPosition(pos.getX(), pos.getY() + 40, pos.getZ());
                    entity.setMotion(0, -1.1, 0);
                    playerIn.world.addEntity(entity);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceEntities(playerIn, 6F) != null || RayTraceHelper.rayTraceBlocks(playerIn, 6F) != null));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lightning_bolt", BeNMJutsu.Type.LIGHTNING_NATURE, 2, 105F, 48, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    Entity entityTarget = entityRayTraceResult.getEntity();
                    LightningBoltEntity lightning = new LightningBoltEntity(playerIn.world, entityTarget.getPosX(), entityTarget.getPosY(), entityTarget.getPosZ(), false);
                    playerIn.world.addEntity(lightning);
                } else if (blockRayTraceResult != null) {
                    BlockPos pos = blockRayTraceResult.getPos();
                    LightningBoltEntity lightning = new LightningBoltEntity(playerIn.world, pos.getX(), pos.getY(), pos.getZ(), false);
                    playerIn.world.addEntity(lightning);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceEntities(playerIn, 6F) != null || RayTraceHelper.rayTraceBlocks(playerIn, 6F) != null));
    }
}
