package com.benarutomod.tbroski.entity.projectile.jutsu.boil;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractBoilJutsuEntity;
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
import net.minecraft.world.World;

public class CorrosiveArrowEntity extends AbstractBoilJutsuEntity {
    public CorrosiveArrowEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public CorrosiveArrowEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.CORROSIVE_ARROW_JUTSU.get(), livingEntityIn, worldIn);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "corrosive_arrow";
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.CORROSIVE_ARROW_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)3);
        } else if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos block = ((BlockRayTraceResult) result).getPos();
            world.playSound(null, block, SoundEvents.ENTITY_ARROW_HIT, SoundCategory.NEUTRAL, 0.7F, 0.4F);
        }
    }
}
