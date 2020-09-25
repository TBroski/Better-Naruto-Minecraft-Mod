package com.benarutomod.tbroski.entity.projectile.jutsu.storm;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractStormJutsuEntity;
import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.benarutomod.tbroski.init.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class DemonDragonStormEntity extends AbstractStormJutsuEntity {

    public DemonDragonStormEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public DemonDragonStormEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.DEMON_DRAGON_STORM_JUTSU.get(), livingEntityIn, worldIn);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "demon_dragon_storm";
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AIR;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.isRemote) {
            Particles.addParticles(this, ParticleTypes.LARGE_SMOKE, 5, 20);
        }
        if (!this.world.isRemote) {
            List<LivingEntity> entityList = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(5));
            for (LivingEntity entity : entityList) {
                if (entity != this.getThrower() && entity.getRNG().nextInt(4) == 0) {
                    LightningBoltEntity bolt = new LightningBoltEntity(this.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), false);
                    this.world.addEntity(bolt);
                }
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }
}
