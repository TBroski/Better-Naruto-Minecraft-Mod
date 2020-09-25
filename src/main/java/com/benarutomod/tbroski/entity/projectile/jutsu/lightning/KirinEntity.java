package com.benarutomod.tbroski.entity.projectile.jutsu.lightning;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractLightningJutsuEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class KirinEntity extends AbstractLightningJutsuEntity {

    public KirinEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public KirinEntity(LivingEntity livingEntityIn, World worldIn) {
        super(EntityInit.KIRIN_JUTSU.get(), livingEntityIn, worldIn);
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "kirin";
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.KIRIN_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            LightningBoltEntity lightning1 = new LightningBoltEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
            this.world.addEntity(lightning1);
            LightningBoltEntity lightning2 = new LightningBoltEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
            this.world.addEntity(lightning2);
            LightningBoltEntity lightning3 = new LightningBoltEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
            this.world.addEntity(lightning3);
            LightningBoltEntity lightning4 = new LightningBoltEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
            this.world.addEntity(lightning4);
            LightningBoltEntity lightning5 = new LightningBoltEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
            this.world.addEntity(lightning5);
            LightningBoltEntity lightning6 = new LightningBoltEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), false);
            this.world.addEntity(lightning6);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }
}
