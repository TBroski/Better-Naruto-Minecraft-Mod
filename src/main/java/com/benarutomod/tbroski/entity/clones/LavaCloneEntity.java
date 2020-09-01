package com.benarutomod.tbroski.entity.clones;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LavaCloneEntity extends AbstractCloneEntity {

    public LavaCloneEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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
        return 800;
    }

    @Override
    public double getBaseHealth() {
        return 2;
    }

    @Override
    public double getSpeed() {
        return 0.2;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.world.isRemote && this.world.isAirBlock(this.getPosition())) {
            this.world.setBlockState(this.getPosition(), Fluids.LAVA.getDefaultState().getBlockState());
        }
    }
}
