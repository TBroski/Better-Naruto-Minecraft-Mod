package com.benarutomod.tbroski.entity.projectile.jutsu.wind;

import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class GalePalmEntity extends AbstractWindJutsuEntity {

    public GalePalmEntity(EntityType<? extends GalePalmEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public GalePalmEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.GALE_PALM_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public GalePalmEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.GALE_PALM_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.GALE_PALM_JUTSU.get();
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.addVelocity(this.getMotion().x * 1.1D, entityCollisionReduction, this.getMotion().z * 1.1D);
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)0.1);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "gale_palm";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
