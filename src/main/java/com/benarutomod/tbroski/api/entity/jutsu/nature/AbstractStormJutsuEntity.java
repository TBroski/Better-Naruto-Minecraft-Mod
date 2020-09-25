package com.benarutomod.tbroski.api.entity.jutsu.nature;

import com.benarutomod.tbroski.api.entity.jutsu.AbstractNinjutsuEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class AbstractStormJutsuEntity extends AbstractNinjutsuEntity {
    public AbstractStormJutsuEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractStormJutsuEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractStormJutsuEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (!entity.world.isRemote && this.getThrower().getRNG().nextInt(10) == 0) {
                LightningBoltEntity lightning = new LightningBoltEntity(entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), false);
                entity.world.addEntity(lightning);
            }
        }
    }
}
