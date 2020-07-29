package com.benarutomod.tbroski.entity.shinobi;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface ISharinganEntity extends IDojutsuEntity{

    float ticksBeforeConnection();

    boolean canUseGenjutsuOnPlayer(PlayerEntity playerIn);

    void attackEntityWithGenjutsuAttack(LivingEntity livingEntityIn);
}
