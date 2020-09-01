package com.benarutomod.tbroski.api.entity.jutsu.nature;

import com.benarutomod.tbroski.api.entity.jutsu.AbstractJutsuEntity;
import com.benarutomod.tbroski.init.EffectInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class AbstractBoilJutsuEntity extends AbstractJutsuEntity {
    public AbstractBoilJutsuEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractBoilJutsuEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractBoilJutsuEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (!entity.world.isRemote && entity instanceof LivingEntity && this.getThrower().getRNG().nextInt(2) == 0) {
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.EXHAUSTION.get(), 200));
            }
        }
    }
}
