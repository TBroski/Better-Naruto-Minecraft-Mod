package com.benarutomod.tbroski.entity.projectile.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.event.PlayerEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public abstract class AbstractGenjutsuProjectileEntity extends AbstractJutsuEntity{
    public AbstractGenjutsuProjectileEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractGenjutsuProjectileEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractGenjutsuProjectileEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (entity instanceof PlayerEntity && this.getThrower() instanceof PlayerEntity) {
                PlayerEntity thrower = (PlayerEntity) this.getThrower();
                LazyOptional<IPlayerHandler> throwerc = thrower.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler thrower_cap = throwerc.orElse(new PlayerCapability());

                PlayerEntity hitPlayer = (PlayerEntity) entity;
                LazyOptional<IPlayerHandler> hitPlayerc = hitPlayer.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler hitPlayer_cap = hitPlayerc.orElse(new PlayerCapability());

                if (hitPlayer_cap.returnGenjutsu() > thrower_cap.returnGenjutsu()) {
                    return;
                }
            }
        }
    }
}
