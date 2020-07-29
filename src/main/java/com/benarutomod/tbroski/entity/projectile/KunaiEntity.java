package com.benarutomod.tbroski.entity.projectile;

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

public class KunaiEntity extends ProjectileItemEntity {

    public KunaiEntity(EntityType<? extends KunaiEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public KunaiEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.KUNAI.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public KunaiEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.KUNAI.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.KUNAI.get();
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        //System.out.println("IMPACT");
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)5);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }
    
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
