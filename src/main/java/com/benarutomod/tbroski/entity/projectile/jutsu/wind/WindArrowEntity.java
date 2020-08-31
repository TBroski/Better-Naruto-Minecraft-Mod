package com.benarutomod.tbroski.entity.projectile.jutsu.wind;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractWindJutsuEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class WindArrowEntity extends AbstractWindJutsuEntity {

    public WindArrowEntity(EntityType<? extends WindArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public WindArrowEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.WIND_ARROW_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public WindArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.WIND_ARROW_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.WIND_ARROW_JUTSU.get();
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            entity.playSound(SoundEvents.ENTITY_ARROW_HIT, 0.7F, 0.4F);
            entity.addVelocity(this.getMotion().x * 1.05D, entityCollisionReduction, this.getMotion().z * 1.05D);
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 3);
        } else if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos block = ((BlockRayTraceResult) result).getPos();
            world.playSound(null, block, SoundEvents.ENTITY_ARROW_HIT, SoundCategory.NEUTRAL, 0.7F, 0.4F);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "wind_arrow";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
