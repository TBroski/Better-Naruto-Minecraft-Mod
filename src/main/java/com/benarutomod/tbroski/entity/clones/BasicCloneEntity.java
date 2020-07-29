package com.benarutomod.tbroski.entity.clones;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BasicCloneEntity extends AbstractCloneEntity {

    private double speed = 0.2D;
    private double baseHealth = this.getBaseHealth();

    public BasicCloneEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.experienceValue = 0;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (cause.getTrueSource() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getTrueSource();
            World world = player.world;
            world.addParticle(ParticleTypes.SMOKE, player.getPosX(), player.getPosY(), player.getPosZ(), 0, 0, 0);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, speed));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(baseHealth);
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return true;
    }

    @Override
    public int getPoofTimer() {
        return 600;
    }

    @Override
    public double getBaseHealth() {
        return 0.1D;
    }


    @Override
    protected boolean canDropLoot() {
        return false;
    }
}
