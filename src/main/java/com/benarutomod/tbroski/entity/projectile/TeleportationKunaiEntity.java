package com.benarutomod.tbroski.entity.projectile;

import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class TeleportationKunaiEntity extends ProjectileItemEntity {

    @Nullable LivingEntity kunaiThrower;
    public TeleportationKunaiEntity(EntityType<? extends TeleportationKunaiEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public TeleportationKunaiEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.TELEPORTATION_KUNAI.get(), throwerIn, worldIn);
        this.kunaiThrower = throwerIn;
    }

    @OnlyIn(Dist.CLIENT)
    public TeleportationKunaiEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.TELEPORTATION_KUNAI.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.TELEPORTATION_KUNAI.get();
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        //System.out.println("IMPACT");
        //System.out.println(this.kunaiThrower);
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            //System.out.println("ENTITY HIT");
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (this.kunaiThrower instanceof ServerPlayerEntity) {
                if (!this.world.isRemote) {
                    this.kunaiThrower.setPositionAndUpdate(entity.getPosX(), entity.getPosY() + 1, entity.getPosZ());
                }
            }
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)5);
        }
        else if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos block = ((BlockRayTraceResult)result).getPos();
            if (this.kunaiThrower instanceof ServerPlayerEntity) {
                if (!this.world.isRemote) {
                    this.kunaiThrower.setPositionAndUpdate(block.getX(), block.getY() + 1, block.getZ());
                }
            }
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
