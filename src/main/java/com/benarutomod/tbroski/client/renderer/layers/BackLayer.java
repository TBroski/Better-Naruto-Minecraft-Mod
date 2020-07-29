package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BackLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {


    public BackLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn instanceof PlayerEntity) {
            ItemStack stack = Main.getBackpackStack((PlayerEntity) entitylivingbaseIn);
            if (!stack.isEmpty()) {
                matrixStackIn.push();
                if (entitylivingbaseIn.isCrouching()) {
                    Quaternion quaternion = new Quaternion(0.5F, 0F,0F, false);
                    matrixStackIn.translate(0D, 0.25D, 0D);
                    matrixStackIn.rotate(quaternion);
                }
                else {
                    matrixStackIn.translate(0D, 0D, 0D);
                    Quaternion quaternion = new Quaternion(0.0F, 0F,0F, false);
                    matrixStackIn.rotate(quaternion);
                }
                if (stack.getItem() == ItemInit.KATANA.get()) {
                    matrixStackIn.scale(1F, 1F, 1.4F);
                    matrixStackIn.translate(0D, 0.59D, 0.12D);
                    Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
                }
                matrixStackIn.pop();
            }
        }
    }
}
