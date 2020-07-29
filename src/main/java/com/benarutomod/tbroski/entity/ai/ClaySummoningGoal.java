package com.benarutomod.tbroski.entity.ai;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class ClaySummoningGoal extends Goal {

    private MobEntity mobEntityIn;
    private float numberOfRoundsIn;
    private float summoningSpeed;
    private int delayCounter;
    private int entitiesPerSummon;
    private EntityType[] entitiesIn;

    public ClaySummoningGoal(MobEntity mobEntityIn, float summoningSpeed, EntityType[] entitiesIn, int entitiesPerSummon)
    {
        this.mobEntityIn = mobEntityIn;
        this.numberOfRoundsIn = entitiesIn.length;
        this.entitiesIn = entitiesIn;
        this.summoningSpeed = summoningSpeed;
        this.entitiesPerSummon = entitiesPerSummon;
        this.setMutexFlags(EnumSet.of(Flag.LOOK));
    }
    @Override
    public boolean shouldExecute() {
        LivingEntity attackTarget = this.mobEntityIn.getAttackTarget();
        return attackTarget != null && attackTarget.isAlive() && this.entitiesIn != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        LivingEntity attackTarget = mobEntityIn.getAttackTarget();
        return attackTarget != null && attackTarget.isAlive();
    }

    public void startExecuting() {
        this.mobEntityIn.setAggroed(true);
        this.delayCounter = 0;
    }

    @Override
    public void tick() {
        LivingEntity attackTarget = this.mobEntityIn.getAttackTarget();
        if (attackTarget != null && attackTarget.isAlive()) {
            this.delayCounter++;
            if (this.delayCounter >= this.summoningSpeed) {
                this.delayCounter = 0;
                float ratio = this.mobEntityIn.getMaxHealth() / this.numberOfRoundsIn;
                int i = 1;
                while (this.mobEntityIn.getHealth() <= (this.mobEntityIn.getMaxHealth() - (ratio * i))) {
                    i++;
                }
                int d = i - 1;
                EntityType entity = this.entitiesIn[d];
                for (int e = 0; e < this.entitiesPerSummon; e++) {
                    Entity spawnedEntity = entity.spawn(this.mobEntityIn.world, null, null, this.mobEntityIn.getPosition(), SpawnReason.MOB_SUMMONED, true, false);
                    ((MobEntity) spawnedEntity).setAttackTarget(attackTarget);
                    spawnedEntity.getEntity().getPersistentData().putInt("affiliatedclayplayer", this.mobEntityIn.getEntityId());
                    ((MobEntity) spawnedEntity).setAggroed(true);
                }
            }
        }
    }
}
