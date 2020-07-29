package com.benarutomod.tbroski.client.renderer.projectile.jutsu.sharingan;

import com.benarutomod.tbroski.client.renderer.blocks.Amaterasu;
import com.benarutomod.tbroski.entity.projectile.jutsu.sharingan.AmaterasuJutsuEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class AmaterasuJutsuRenderer extends SpriteRenderer<AmaterasuJutsuEntity> {

    public AmaterasuJutsuRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    @Override
    public void render(AmaterasuJutsuEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        Amaterasu.renderAmaterasuFire(matrixStackIn, bufferIn, entityIn);
    }

    public static class Factory implements IRenderFactory<AmaterasuJutsuEntity> {

        @Override
        public EntityRenderer<? super AmaterasuJutsuEntity> createRenderFor(EntityRendererManager manager) {
            return new AmaterasuJutsuRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
