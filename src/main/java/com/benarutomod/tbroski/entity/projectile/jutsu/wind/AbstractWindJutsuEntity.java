package com.benarutomod.tbroski.entity.projectile.jutsu.wind;

import com.benarutomod.tbroski.entity.projectile.jutsu.AbstractJutsuEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.world.World;

public abstract class AbstractWindJutsuEntity extends AbstractJutsuEntity {
    public AbstractWindJutsuEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractWindJutsuEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractWindJutsuEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }
}
