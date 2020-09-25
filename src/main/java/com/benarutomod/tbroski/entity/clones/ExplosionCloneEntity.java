package com.benarutomod.tbroski.entity.clones;

import com.benarutomod.tbroski.Config;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosionCloneEntity extends AbstractCloneEntity {

    public ExplosionCloneEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, this.getSpeed(), false));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public int getPoofTimer() {
        return 600;
    }

    @Override
    public double getBaseHealth() {
        return 2;
    }

    @Override
    public double getSpeed() {
        return 0.22;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.world.isRemote) {
            if (Config.COMMON.playerWorldDamage.get())
                this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 5F, Explosion.Mode.DESTROY);
        }
    }
}
