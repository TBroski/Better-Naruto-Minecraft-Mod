package com.benarutomod.tbroski.entity.mobs;

import com.benarutomod.tbroski.entity.ai.CurseMarkMeleeAttackGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class SnakeEntity extends CreatureEntity {

    private static final int chance = 7;
    private static final DataParameter<Boolean> CURSED = EntityDataManager.createKey(SnakeEntity.class, DataSerializers.BOOLEAN);

    public SnakeEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(CURSED, false);
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.dataManager.set(CURSED, new Random().nextInt(7) == 0);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean getCursed() {
        return this.dataManager.get(CURSED);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(2, new CurseMarkMeleeAttackGoal(this, this.getSpeed(), false, chance, 1F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    public double getSpeed() {
        return 0.25D;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3D);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("cursed", this.dataManager.get(CURSED));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(CURSED, compound.getBoolean("cursed"));
    }
}
