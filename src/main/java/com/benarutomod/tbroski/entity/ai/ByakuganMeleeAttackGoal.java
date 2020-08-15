package com.benarutomod.tbroski.entity.ai;

import com.benarutomod.tbroski.api.interfaces.IByakuganEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

public class ByakuganMeleeAttackGoal<T extends CreatureEntity & IByakuganEntity> extends MeleeAttackGoal {

    private T byakuganEntity;

    public ByakuganMeleeAttackGoal(T creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
        this.byakuganEntity = creature;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
            this.attackTick = 20;
            this.attacker.swingArm(Hand.MAIN_HAND);
            enemy.getPersistentData().putInt("restrictedchakra", this.byakuganEntity.ticksRestrictedPerPunch());
            this.attacker.attackEntityAsMob(enemy);
        }
    }
}
