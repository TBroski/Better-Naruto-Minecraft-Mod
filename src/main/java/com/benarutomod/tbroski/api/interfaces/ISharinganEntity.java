package com.benarutomod.tbroski.api.interfaces;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public interface ISharinganEntity extends IDojutsuEntity {
    float ticksBeforeConnection();
    boolean canUseGenjutsuOnPlayer(PlayerEntity playerIn);
    void attackEntityWithGenjutsuAttack(LivingEntity entityIn);
}
