package com.benarutomod.tbroski.api.entity.jutsu.nature;

import com.benarutomod.tbroski.api.entity.jutsu.AbstractNinjutsuEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class AbstractExplosionJutsuEntity extends AbstractNinjutsuEntity {
    public AbstractExplosionJutsuEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractExplosionJutsuEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractExplosionJutsuEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (!entity.world.isRemote && this.getThrower().getRNG().nextInt(2) == 0) {
                entity.setMotion(entity.getMotion().x * 1.6, entity.getMotion().y + 0.1, entity.getMotion().y * 1.6);
            }
        }
    }
}
