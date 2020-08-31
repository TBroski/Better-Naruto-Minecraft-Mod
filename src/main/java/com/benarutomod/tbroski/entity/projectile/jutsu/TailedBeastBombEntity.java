package com.benarutomod.tbroski.entity.projectile.jutsu;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.api.entity.jutsu.AbstractJutsuEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TailedBeastBombEntity extends AbstractJutsuEntity {

    @Override
    public String getAffiliatedJutsuName() {
        return "tailed_beast_bomb";
    }

    public TailedBeastBombEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public TailedBeastBombEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public TailedBeastBombEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.TAILED_BEAST_BOMB.get(), livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.TAILED_BEAST_BOMB.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            if (Config.COMMON.playerWorldDamage.get()) {
                this.world.createExplosion(null, this.getPosX(), this.getPosY(), this.getPosZ(), 7F, Explosion.Mode.BREAK);
            }
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                entity.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.5F, 0.4F);
                entity.addVelocity(this.getMotion().x * 1.3D, entity.getMotion().getY() + 0.5D, this.getMotion().z * 1.3D);
                entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)5);
            }
            else if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos block = ((BlockRayTraceResult)result).getPos();
                world.playSound(null, block, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1.5F, 0.4F);
            }
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }
}
