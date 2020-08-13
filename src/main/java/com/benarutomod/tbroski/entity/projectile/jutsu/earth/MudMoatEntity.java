package com.benarutomod.tbroski.entity.projectile.jutsu.earth;

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
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class MudMoatEntity extends AbstractEarthJutsuEntity {

    public MudMoatEntity(EntityType<? extends MudMoatEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MudMoatEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.MUD_MOAT_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public MudMoatEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.MUD_MOAT_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.MUD_MOAT_JUTSU.get();
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult) result).getEntity();
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 40, 0));
            }
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) 4);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "mud_moat";
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
