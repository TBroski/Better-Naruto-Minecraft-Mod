package com.benarutomod.tbroski.entity.mobs;

import com.benarutomod.tbroski.init.EntityInit;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class FrogEntity extends RabbitEntity {

    private static final DataParameter<Boolean> CLOTHED = EntityDataManager.createKey(FrogEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> VARINT = EntityDataManager.createKey(FrogEntity.class, DataSerializers.VARINT);


    public FrogEntity(EntityType<? extends RabbitEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(CLOTHED, false);
        this.dataManager.register(VARINT, 0);
    }


    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = itemstack.getItem() instanceof BlockItem && ((BlockItem) itemstack.getItem()).getBlock().getDefaultState().getMaterial() == Material.WOOL;
        if (flag && !this.dataManager.get(CLOTHED)) {
            this.dataManager.set(CLOTHED, true);
            itemstack.shrink(1);
            return true;
        }
        return super.processInteract(player, hand);
    }

    @Override
    public FrogEntity createChild(AgeableEntity ageable) {
        FrogEntity frogEntity = EntityInit.FROG.get().create(this.world);

        frogEntity.setVariant(this.rand.nextInt(5));
        frogEntity.setClothed(this.getClothed());

        return frogEntity;
    }

    public void setVariant(int variant) {
        this.dataManager.set(VARINT, variant);
    }

    public void setClothed(boolean isClothed) {
        this.dataManager.set(CLOTHED, isClothed);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.SPIDER_EYE;
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.dataManager.set(CLOTHED, new Random().nextInt(7) == 0);
        this.dataManager.set(VARINT, new Random().nextInt(5));
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public boolean getClothed() {
        return this.dataManager.get(CLOTHED);
    }

    public int getVariant() {
        return this.dataManager.get(VARINT);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new FrogEntity.EvilAttackGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, SpiderEntity.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, SnakeEntity.class, true));
    }

    public double getSpeed() {
        return 0.25D;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("clothed", this.dataManager.get(CLOTHED));
        compound.putInt("varint", this.dataManager.get(VARINT));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(CLOTHED, compound.getBoolean("clothed"));
        this.dataManager.set(VARINT, compound.getInt("varint"));
    }

    static class EvilAttackGoal extends MeleeAttackGoal {
        public EvilAttackGoal(FrogEntity frog) {
            super(frog, 1.4D, true);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(4.0F + attackTarget.getWidth());
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= d0 && this.attackTick <= 0) {
                this.attackTick = 20;
                this.attacker.swingArm(Hand.MAIN_HAND);
                this.attacker.attackEntityAsMob(enemy);
                enemy.addPotionEffect(new EffectInstance(Effects.POISON, 40, 0));
            }
        }
    }
}
