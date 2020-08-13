package com.benarutomod.tbroski.client.renderer.projectile.jutsu.sixpath;

import com.benarutomod.tbroski.entity.projectile.jutsu.sixpath.MiniRocketProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MiniRocketRenderer extends SpriteRenderer<MiniRocketProjectileEntity> {

    public MiniRocketRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    @Override
    public void render(MiniRocketProjectileEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static class Factory implements IRenderFactory<MiniRocketProjectileEntity> {

        @Override
        public EntityRenderer<? super MiniRocketProjectileEntity> createRenderFor(EntityRendererManager manager) {
            return new MiniRocketRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
