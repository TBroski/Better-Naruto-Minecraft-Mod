package com.benarutomod.tbroski.entity.projectile.jutsu.water;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractWaterJutsuEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class WaterSharkBulletEntity extends AbstractWaterJutsuEntity {

    public WaterSharkBulletEntity(EntityType<? extends WaterSharkBulletEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public WaterSharkBulletEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.WATER_SHARK_BULLET_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public WaterSharkBulletEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.WATER_SHARK_BULLET_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.WATER_SHARK_BULLET_JUTSU.get();
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)10);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "water_shark";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
