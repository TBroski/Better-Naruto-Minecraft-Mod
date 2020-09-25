package com.benarutomod.tbroski.api.entity.jutsu.nature;

import com.benarutomod.tbroski.api.entity.jutsu.AbstractNinjutsuEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class AbstractLavaJutsuEntity extends AbstractNinjutsuEntity {
    public AbstractLavaJutsuEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractLavaJutsuEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractLavaJutsuEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (!entity.world.isRemote && this.getThrower().getRNG().nextInt(5) == 0) {
                if (entity.world.isAirBlock(entity.getPosition())) {
                    entity.world.setBlockState(entity.getPosition(), Fluids.LAVA.getDefaultState().getBlockState());
                }
            }
        }
    }
}
