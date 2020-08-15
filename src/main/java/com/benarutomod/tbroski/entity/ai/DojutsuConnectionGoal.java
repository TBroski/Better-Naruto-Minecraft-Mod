package com.benarutomod.tbroski.entity.ai;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.internal.BeNMDojutsu;
import com.benarutomod.tbroski.api.interfaces.ISharinganEntity;
import com.benarutomod.tbroski.init.DamageInit;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

public class DojutsuConnectionGoal<T extends MobEntity & ISharinganEntity> extends Goal {

    private T dojutsuEntity;
    private BeNMDojutsu eye;
    private PlayerEntity playerEntity;
    private float distance;
    private int cooldown;

    public DojutsuConnectionGoal(T dojutsuEntityIn, boolean rightEye, float distance) {
        this.dojutsuEntity = dojutsuEntityIn;
        this.distance = distance;
        if (rightEye) {
            this.eye = dojutsuEntityIn.rightDojustsu();
        }
        else {
            this.eye  = dojutsuEntityIn.leftDojustsu();
        }
    }

    public DojutsuConnectionGoal() {
    }

    @Override
    public boolean shouldExecute() {
        this.findPlayers();
        return this.playerEntity != null && this.playerEntity.isAlive() && this.dojutsuEntity.canUseGenjutsuOnPlayer(this.playerEntity) && this.dojutsuEntity.getPersistentData().getInt("connectedentity") == 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.playerEntity != null && this.playerEntity.isAlive() && this.dojutsuEntity.canUseGenjutsuOnPlayer(playerEntity) && this.dojutsuEntity.getDistance(this.playerEntity) <= this.distance && this.dojutsuEntity.getPersistentData().getInt("connectedentity") == 0;
    }

    @Override
    public void tick() {
        this.cooldown += 1;
        if (this.cooldown >= this.dojutsuEntity.ticksBeforeConnection()) {
            this.playerEntity.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("dojutsu." + Main.MODID + ".uponmessage").getString() + this.eye.getString().substring(0, 1).toUpperCase() + this.eye.getString().substring(1)), true);
            this.playerEntity.attackEntityFrom(DamageInit.DOJUTSU,0.1F);
            this.dojutsuEntity.getPersistentData().putInt("connectedentity", this.playerEntity.getEntityId());
        }
    }

    @Override
    public void resetTask() {
        this.cooldown = 0;
        this.dojutsuEntity.getPersistentData().putInt("dojutsutracked", 0);
    }

    @Nullable
    private void findPlayers() {
        List<PlayerEntity> players = this.dojutsuEntity.world.getEntitiesWithinAABB(PlayerEntity.class, this.dojutsuEntity.getBoundingBox().grow(distance * 2), playerEntity -> playerEntity == this.dojutsuEntity.getAttackTarget());
        if (players.size() > 0) {
            this.playerEntity = players.stream().min(Comparator.comparing(this.dojutsuEntity::getDistance)).get();
        }
        else {
            this.dojutsuEntity.getPersistentData().putInt("connectedentity", 0);
        }
    }
}
