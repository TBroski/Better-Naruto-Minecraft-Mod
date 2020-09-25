package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.GalePalmEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindExplosionEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;

import java.util.Iterator;
import java.util.List;

public class WindNatureJutsuInit {

    public static void registerWindJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "gale_palm", BeNMJutsu.Type.WIND_NATURE, 4, 20F, 80, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            GalePalmEntity entity = new GalePalmEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.GALE_PALM_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 0.1F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wind_explosion", BeNMJutsu.Type.WIND_NATURE, 6, 80F, 80, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WindExplosionEntity entity = new WindExplosionEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_EXPLOSION_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wind_arrow", BeNMJutsu.Type.WIND_NATURE, 5, 40F, 80, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WindArrowEntity entity = new WindArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "vacuum_sphere", BeNMJutsu.Type.WIND_NATURE, 6, 50F, 80, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    Entity entity = entityRayTraceResult.getEntity();
                    float yaw = playerIn.rotationYaw;
                    float pitch = playerIn.rotationPitch + 0.5F;
                    float velocity = 3.4F;
                    double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                    entity.setMotion(motionX, motionY, motionZ);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceEntities(playerIn, 6F) != null));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "zero_air_pressure", BeNMJutsu.Type.WIND_NATURE, 7, 3F, 80, 64, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            List<LivingEntity> entities = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().grow(6));
            Iterator iterator = entities.iterator();
            while (iterator.hasNext()) {
                LivingEntity entity = (LivingEntity) iterator.next();
                if (entity.ticksExisted % 20 == 0 && entity != playerIn) {
                    entity.attackEntityFrom(DamageSource.IN_WALL, 2.5F);
                }
            }
        }));
    }
}
