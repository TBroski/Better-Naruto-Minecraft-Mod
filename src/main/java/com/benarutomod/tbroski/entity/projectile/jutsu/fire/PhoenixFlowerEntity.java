package com.benarutomod.tbroski.entity.projectile.jutsu.fire;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractFireJutsuEntity;
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
import net.minecraftforge.fml.network.NetworkHooks;

public class PhoenixFlowerEntity extends AbstractFireJutsuEntity {

    public PhoenixFlowerEntity(EntityType<? extends PhoenixFlowerEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PhoenixFlowerEntity(World worldIn, LivingEntity throwerIn, boolean isBlueFire) {
        super(EntityInit.PHOENIX_FLOWER_JUTSU.get(), throwerIn, worldIn, isBlueFire);
    }

    protected Item getDefaultItem() {
        return ItemInit.PHOENIX_FLOWER_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote && result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.setFire(2);
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)3);
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
        return "phoenix_flower";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
