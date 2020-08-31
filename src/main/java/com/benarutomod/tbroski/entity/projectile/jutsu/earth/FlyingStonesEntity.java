package com.benarutomod.tbroski.entity.projectile.jutsu.earth;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractEarthJutsuEntity;
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

public class FlyingStonesEntity extends AbstractEarthJutsuEntity {

    public FlyingStonesEntity(EntityType<? extends FlyingStonesEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public FlyingStonesEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.FLYING_STONES_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public FlyingStonesEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.FLYING_STONES_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.FLYING_STONES_JUTSU.get();
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)8);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "flying_stones";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
