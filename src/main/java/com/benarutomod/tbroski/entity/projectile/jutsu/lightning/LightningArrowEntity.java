package com.benarutomod.tbroski.entity.projectile.jutsu.lightning;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractLightningJutsuEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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

public class LightningArrowEntity extends AbstractLightningJutsuEntity {

    public LightningArrowEntity(EntityType<? extends LightningArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public LightningArrowEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.LIGHTNING_ARROW_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public LightningArrowEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.LIGHTNING_ARROW_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.LIGHTNING_ARROW_JUTSU.get();
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (((EntityRayTraceResult)result).getEntity() instanceof LivingEntity)
            {
                LivingEntity livingEntity = (LivingEntity) ((EntityRayTraceResult)result).getEntity();
                livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 1));
            }
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)3);
        } else if (result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos block = ((BlockRayTraceResult) result).getPos();
            world.playSound(null, block, SoundEvents.ENTITY_ARROW_HIT, SoundCategory.NEUTRAL, 0.7F, 0.4F);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "lightning_arrow";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
