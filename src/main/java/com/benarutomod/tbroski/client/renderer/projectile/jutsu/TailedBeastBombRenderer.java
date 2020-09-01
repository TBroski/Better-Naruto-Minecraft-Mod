package com.benarutomod.tbroski.client.renderer.projectile.jutsu;

import com.benarutomod.tbroski.entity.projectile.jutsu.TailedBeastBombEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TailedBeastBombRenderer extends SpriteRenderer<TailedBeastBombEntity> {

    public TailedBeastBombRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    @Override
    public void render(TailedBeastBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static class Factory implements IRenderFactory<TailedBeastBombEntity> {

        @Override
        public EntityRenderer<? super TailedBeastBombEntity> createRenderFor(EntityRendererManager manager) {
            return new TailedBeastBombRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
