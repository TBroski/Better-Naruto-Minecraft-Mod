package com.benarutomod.tbroski.entity.projectile.jutsu.fire;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractFireJutsuEntity;
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

public class FlameDragonEntity extends AbstractFireJutsuEntity {

    public FlameDragonEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public FlameDragonEntity(LivingEntity livingEntityIn, World worldIn, boolean isBlueFire) {
        super(EntityInit.FLAME_DRAGON_JUTSU.get(), livingEntityIn, worldIn, isBlueFire);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "flame_dragon";
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.FLAME_DRAGON_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote && result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)6.5);
            entity.setFire(2);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }
}
