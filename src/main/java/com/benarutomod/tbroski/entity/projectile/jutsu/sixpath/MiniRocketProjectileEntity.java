package com.benarutomod.tbroski.entity.projectile.jutsu.sixpath;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class MiniRocketProjectileEntity extends ProjectileItemEntity {
    public MiniRocketProjectileEntity(EntityType<? extends MiniRocketProjectileEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MiniRocketProjectileEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.MINI_ROCKET.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public MiniRocketProjectileEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.MINI_ROCKET.get(), x, y, z, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(ParticleTypes.FLAME, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.MINI_ROCKET.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (Config.COMMON.playerWorldDamage.get()) {
                this.world.createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), 3F, Explosion.Mode.BREAK);
            }
            else if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                entity.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 0.7F, 0.4F);
                entity.addVelocity(this.getMotion().x * 1.3D, entity.getMotion().getY() + 0.5D, this.getMotion().z * 1.3D);
                entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)3);
            }
            else if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos block = ((BlockRayTraceResult)result).getPos();
                world.playSound(null, block, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.7F, 0.4F);
            }
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
