package com.benarutomod.tbroski.entity.projectile.jutsu.lava;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractLavaEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ScorchingStreamRockEntity extends AbstractLavaEntity {
    public ScorchingStreamRockEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ScorchingStreamRockEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.SCORCHING_STREAM_ROCK_JUTSU.get(), livingEntityIn, worldIn);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "scorching_stream_rock";
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.SCORCHING_STREAM_ROCK_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)5);
        }
    }
}
