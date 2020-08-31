package com.benarutomod.tbroski.client.renderer.projectile.jutsu.fire;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractFireJutsuEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import java.awt.*;

public class FireJutsuRenderer extends SpriteRenderer<AbstractFireJutsuEntity> {

    public FireJutsuRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    @Override
    public void render(AbstractFireJutsuEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.isBlueFire()) {
            TextureAtlasSprite textureatlassprite = ModelBakery.LOCATION_FIRE_0.getSprite();
            TextureAtlasSprite textureatlassprite1 = ModelBakery.LOCATION_FIRE_1.getSprite();
            matrixStackIn.push();
            float f = entityIn.getType().getWidth() * 1.4F;
            matrixStackIn.scale(f, f, f);
            float f1 = 0.5F;
            float f2 = 0.0F;
            float f3 = entityIn.getType().getHeight() / f;
            float f4 = 0.0F;
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getYaw()));
            matrixStackIn.translate(0.0D, 0.0D, (double) (-0.3F + (float) ((int) f3) * 0.02F));
            float f5 = 0.0F;
            int i = 0;
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(Atlases.getCutoutBlockType());

            for (MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast(); f3 > 0.0F; ++i) {
                TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
                float f6 = textureatlassprite2.getMinU();
                float f7 = textureatlassprite2.getMinV();
                float f8 = textureatlassprite2.getMaxU();
                float f9 = textureatlassprite2.getMaxV();
                if (i / 2 % 2 == 0) {
                    float f10 = f8;
                    f8 = f6;
                    f6 = f10;
                }

                fireVertex(matrixstack$entry, ivertexbuilder, f1 - 0.0F, 0.0F - f4, f5, f8, f9);
                fireVertex(matrixstack$entry, ivertexbuilder, -f1 - 0.0F, 0.0F - f4, f5, f6, f9);
                fireVertex(matrixstack$entry, ivertexbuilder, -f1 - 0.0F, 1.4F - f4, f5, f6, f7);
                fireVertex(matrixstack$entry, ivertexbuilder, f1 - 0.0F, 1.4F - f4, f5, f8, f7);
                f3 -= 0.45F;
                f4 -= 0.45F;
                f1 *= 0.9F;
                f5 += 0.03F;
            }

            matrixStackIn.pop();
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    private static void fireVertex(MatrixStack.Entry matrixEntryIn, IVertexBuilder bufferIn, float x, float y, float z, float texU, float texV) {
         bufferIn.pos(matrixEntryIn.getMatrix(), x, y, z).color(Color.BLUE.getRed(), Color.BLUE.getGreen(), Color.BLUE.getBlue(), Color.BLUE.getAlpha()).tex(texU, texV).overlay(0, 10).lightmap(240).normal(matrixEntryIn.getNormal(), 0.0F, 1.0F, 0.0F).endVertex();
    }
    public static class Factory implements IRenderFactory<AbstractFireJutsuEntity> {

        @Override
        public EntityRenderer<? super AbstractFireJutsuEntity> createRenderFor(EntityRendererManager manager) {
            return new FireJutsuRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
