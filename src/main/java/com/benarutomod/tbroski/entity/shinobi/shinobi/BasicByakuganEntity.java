package com.benarutomod.tbroski.entity.shinobi.shinobi;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.entity.ai.ByakuganMeleeAttackGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.api.entity.AbstractShinobiEntity;
import com.benarutomod.tbroski.api.interfaces.IByakuganEntity;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class BasicByakuganEntity extends AbstractShinobiEntity implements IByakuganEntity {

    public BasicByakuganEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 15.0F));
        this.goalSelector.addGoal(2, new ByakuganMeleeAttackGoal<>(this, this.getSpeed(), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, false));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public BeNMClan getClan() {
        return ClanInit.HYUGA;
    }

    @Override
    public double getSpeed() {
        return 0.4D;
    }

    @Override
    public int deathBeNMPoints() {
        return 2;
    }

    @Override
    public int ticksRestrictedPerPunch() {
        return 100;
    }

    @Override
    public int eyeSlot() {
        return 4;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    protected BeNMDojutsu registeredLeftEye() {
        return DojutsuInit.BYAKUGAN;
    }

    @Override
    protected BeNMDojutsu registeredRightEye() {
        return DojutsuInit.BYAKUGAN;
    }
}
