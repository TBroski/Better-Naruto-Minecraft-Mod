package com.benarutomod.tbroski.entity.projectile.jutsu.magnet;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractMagnetJutsuEntity;
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

public class BlackIronFistEntity extends AbstractMagnetJutsuEntity {
    public BlackIronFistEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BlackIronFistEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.BLACK_IRON_FIST_JUTSU.get(), livingEntityIn, worldIn);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "iron_fist";
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.BLACK_IRON_FIST_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)7);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }
}
