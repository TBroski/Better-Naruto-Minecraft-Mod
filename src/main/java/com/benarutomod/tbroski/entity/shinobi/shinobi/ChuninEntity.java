package com.benarutomod.tbroski.entity.shinobi.shinobi;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.entity.projectile.KunaiEntity;
import com.benarutomod.tbroski.entity.shinobi.AbstractShinobiEntity;
import com.benarutomod.tbroski.init.ClanInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Random;

public class ChuninEntity extends AbstractShinobiEntity {
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(ChuninEntity.class, DataSerializers.VARINT);
    public static Random rand = new Random();

    @Override
    public BeNMClan getClan() {
        return ClanInit.NULL;
    }

    @Override
    public double getSpeed() {
        return 0.4D;
    }

    @Override
    public int deathBeNMPoints() {
        return 2;
    }

    public ChuninEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Chunin"));
        this.dataManager.register(VARIANT, Integer.valueOf(rand.nextInt(2)));
    }

    public int getVariant()
    {
        return this.dataManager.get(VARIANT);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, this.getSpeed(), 45, 10F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        KunaiEntity kunai = new KunaiEntity(this.world, this);
        double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
        double d1 = target.getPosX() - this.getPosX();
        double d3 = target.getPosZ() - this.getPosZ();
        kunai.shoot(d1, d0 - kunai.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.85F, 5.0F);
        this.world.addEntity(kunai);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
    }
}
