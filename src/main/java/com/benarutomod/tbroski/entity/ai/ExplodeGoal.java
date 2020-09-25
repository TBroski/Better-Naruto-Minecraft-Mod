package com.benarutomod.tbroski.entity.ai;

import com.benarutomod.tbroski.entity.ClayEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.Explosion;

import java.util.EnumSet;

public class ExplodeGoal extends Goal {

    private ClayEntity clayEntity;

    public ExplodeGoal(ClayEntity clayEntity) {
        this.clayEntity = clayEntity;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity livingentity = this.clayEntity.getAttackTarget();
        return livingentity != null && this.clayEntity.getDistanceSq(livingentity) < 9.0D;
    }

    @Override
    public boolean shouldContinueExecuting() {
        LivingEntity livingentity = this.clayEntity.getAttackTarget();
        return livingentity != null && this.clayEntity.getDistanceSq(livingentity) < 9.0D;
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity livingentity = this.clayEntity.getAttackTarget();
        if (this.clayEntity.getDistanceSq(livingentity) < 49.0D && this.clayEntity.canEntityBeSeen(livingentity)) {
            this.clayEntity.world.createExplosion(this.clayEntity, this.clayEntity.getPosX(), this.clayEntity.getPosY(), this.clayEntity.getPosZ(), this.clayEntity.getExplosionLevel() * 2, Explosion.Mode.DESTROY);
        }
    }
}
