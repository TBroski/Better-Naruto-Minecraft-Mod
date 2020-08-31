package com.benarutomod.tbroski.client.renderer.layers.models.susanoo;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class ModelSusanoo<T extends LivingEntity> extends AgeableModel<T> {
    @OnlyIn(Dist.CLIENT)
    public abstract ResourceLocation getSusanooTexture(PlayerEntity playerIn);
    @OnlyIn(Dist.CLIENT)
    public abstract void manipulateItemToRender(MatrixStack matrixStackIn, ItemStack itemToRender, Hand handIn);
}
