package com.benarutomod.tbroski.entity.projectile.jutsu.ice;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractIceJutsuEntity;
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

public class IcePetalEntity extends AbstractIceJutsuEntity {
    public IcePetalEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public IcePetalEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.TEN_THOUSAND_ICE_PETALS_JUTSU.get(), livingEntityIn, worldIn);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "ten_thousand_ice_petals";
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.TEN_THOUSAND_ICE_PETALS_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.5F);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }
}
