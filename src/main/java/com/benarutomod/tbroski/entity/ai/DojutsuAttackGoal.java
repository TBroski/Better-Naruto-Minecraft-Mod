package com.benarutomod.tbroski.entity.ai;

import com.benarutomod.tbroski.entity.shinobi.ISharinganEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;

public class DojutsuAttackGoal<T extends MobEntity & ISharinganEntity> extends Goal {

    private T dojutsuEntity;
    private int cooldownSave;
    private int cooldown;

    public DojutsuAttackGoal(T dojutsuEntityIn, int cooldown) {
        this.dojutsuEntity = dojutsuEntityIn;
        this.cooldownSave = cooldown;
    }

    @Override
    public void resetTask() {
        this.cooldown = 0;
    }

    @Override
    public boolean shouldExecute() {
        return this.dojutsuEntity.getPersistentData().getInt("connectedentity") != 0 && this.dojutsuEntity.world.getEntityByID(this.dojutsuEntity.getPersistentData().getInt("connectedentity")) != null && this.dojutsuEntity.world.getEntityByID(this.dojutsuEntity.getPersistentData().getInt("connectedentity")).isAlive();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.dojutsuEntity.getPersistentData().getInt("connectedentity") != 0 && this.dojutsuEntity.world.getEntityByID(this.dojutsuEntity.getPersistentData().getInt("connectedentity")) != null && this.dojutsuEntity.world.getEntityByID(this.dojutsuEntity.getPersistentData().getInt("connectedentity")).isAlive();
    }

    @Override
    public void tick() {
        this.cooldown += 1;
        if (this.cooldown >= cooldownSave) {
            this.cooldown = 0;
            this.dojutsuEntity.attackEntityWithGenjutsuAttack((LivingEntity) this.dojutsuEntity.world.getEntityByID(this.dojutsuEntity.getPersistentData().getInt("connectedentity")));
        }
    }
}
