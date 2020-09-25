package com.benarutomod.tbroski.entity.shinobi.akatsuki;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.entity.ai.ClaySummoningGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.api.entity.AbstractAkatsukiEntity;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DeidaraEntity extends AbstractAkatsukiEntity {

    @Override
    public BeNMClan getClan() {
        return ClanInit.NULL;
    }

    @Override
    public double getSpeed() {
        return 0.5D;
    }

    @Override
    public int deathBeNMPoints() {
        return 7;
    }

    public DeidaraEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Deidara"));
    }

    @Override
    protected void registerGoals() {
        EntityType[] mobEntities = {
                EntityType.ZOMBIE,
                EntityType.SPIDER,
                EntityType.CREEPER,
        };
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 6.0F, 1.3D, 1.5D));
        this.goalSelector.addGoal(2, new ClaySummoningGoal(this, 200.0F, mobEntities, 3));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public ItemStack getMainHandItemStack() {
        return new ItemStack(ItemInit.MOLDED_CLAY_C3.get());
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        //EMPTY
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(11D);
    }
}
