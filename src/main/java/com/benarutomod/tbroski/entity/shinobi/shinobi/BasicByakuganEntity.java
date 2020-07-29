package com.benarutomod.tbroski.entity.shinobi.shinobi;

import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.common.BeNMDojutsu;
import com.benarutomod.tbroski.entity.ai.ByakuganMeleeAttackGoal;
import com.benarutomod.tbroski.entity.ai.DojutsuAttackGoal;
import com.benarutomod.tbroski.entity.ai.DojutsuConnectionGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.entity.shinobi.AbstractShinobiEntity;
import com.benarutomod.tbroski.entity.shinobi.IByakuganEntity;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BasicByakuganEntity extends AbstractShinobiEntity implements IByakuganEntity {

    private static final DataParameter<String> LEFT_EYE = EntityDataManager.createKey(BasicByakuganEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> RIGHT_EYE = EntityDataManager.createKey(BasicByakuganEntity.class, DataSerializers.STRING);

    public BasicByakuganEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LEFT_EYE, "byakugan");
        this.dataManager.register(RIGHT_EYE, "byakugan");
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
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        this.world.setBlockState(this.getPosition(), BlockInit.DOJUTSU_SKULL.get().getDefaultState());
        TileEntity tileEntity = this.world.getTileEntity(this.getPosition());
        if (tileEntity instanceof DojutsuSkullTileEntity) {
            DojutsuSkullTileEntity dojutsuSkullTileEntity = (DojutsuSkullTileEntity) tileEntity;
            dojutsuSkullTileEntity.setLivingEntity(this);
        }
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
    public BeNMDojutsu rightDojustsu() {
        return DojutsuHelper.getDojutsuFromString(this.dataManager.get(RIGHT_EYE));
    }
    @Override
    public BeNMDojutsu leftDojustsu() {
        return DojutsuHelper.getDojutsuFromString(this.dataManager.get(LEFT_EYE));
    }
    @Override
    public void setRightDojustsu(BeNMDojutsu rightEye) {
        this.dataManager.set(RIGHT_EYE, rightEye.getString());
    }
    @Override
    public void setLeftDojustsu(BeNMDojutsu leftEye) {
        this.dataManager.set(LEFT_EYE, leftEye.getString());
    }

    @Override
    public int eyeSlot() {
        return 4;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
