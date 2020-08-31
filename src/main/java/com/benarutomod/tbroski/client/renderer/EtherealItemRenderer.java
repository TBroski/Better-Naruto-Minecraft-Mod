package com.benarutomod.tbroski.client.renderer;

import com.benarutomod.tbroski.api.internal.dojutsu.BeNMSharingan;
import com.benarutomod.tbroski.api.item.EtherealItem;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;

import java.awt.*;

public class EtherealItemRenderer extends ItemRenderer {

    public EtherealItemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
        this.shadowSize = 0.0F;
        this.shadowOpaque = 0.0F;
    }

    @Override
    public void render(ItemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        PlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if ((playerc.returnBodyInfusionToggled()) && ((playerc.returnPlayerLeftDojutsu() instanceof BeNMSharingan && playerc.returnPlayerLeftDojutsu() != DojutsuInit.SHARINGAN) || ((playerc.returnPlayerRightDojutsu() instanceof BeNMSharingan && playerc.returnPlayerRightDojutsu() != DojutsuInit.SHARINGAN)))) {
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            if (entityIn.getItem().getItem() instanceof EtherealItem && ((EtherealItem) entityIn.getItem().getItem()).doesRenderOnFire()) {
                TextureAtlasSprite textureatlassprite = ModelBakery.LOCATION_FIRE_0.getSprite();
                TextureAtlasSprite textureatlassprite1 = ModelBakery.LOCATION_FIRE_1.getSprite();
                matrixStackIn.push();
                float f = ((EtherealItem) entityIn.getItem().getItem()).getWidth() * 1.4F;
                matrixStackIn.scale(f, f, f);
                float f1 = 0.5F;
                float f2 = 0.0F;
                float f3 = ((EtherealItem) entityIn.getItem().getItem()).getHeight() / f;
                float f4 = 0.0F;
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getYaw()));
                matrixStackIn.translate(0.0D, 0.0D, (double)(-0.3F + (float)((int)f3) * 0.02F));
                float f5 = 0.0F;
                int i = 0;
                IVertexBuilder ivertexbuilder = bufferIn.getBuffer(Atlases.getCutoutBlockType());

                for(MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast(); f3 > 0.0F; ++i) {
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

                    fireVertex(matrixstack$entry, ivertexbuilder, f1 - 0.0F, 0.0F - f4, f5, f8, f9,((EtherealItem) entityIn.getItem().getItem()).getColor());
                    fireVertex(matrixstack$entry, ivertexbuilder, -f1 - 0.0F, 0.0F - f4, f5, f6, f9, ((EtherealItem) entityIn.getItem().getItem()).getColor());
                    fireVertex(matrixstack$entry, ivertexbuilder, -f1 - 0.0F, 1.4F - f4, f5, f6, f7, ((EtherealItem) entityIn.getItem().getItem()).getColor());
                    fireVertex(matrixstack$entry, ivertexbuilder, f1 - 0.0F, 1.4F - f4, f5, f8, f7, ((EtherealItem) entityIn.getItem().getItem()).getColor());
                    f3 -= 0.45F;
                    f4 -= 0.45F;
                    f1 *= 0.9F;
                    f5 += 0.03F;
                }

                matrixStackIn.pop();
            }
        }
    }

    private static void fireVertex(MatrixStack.Entry matrixEntryIn, IVertexBuilder bufferIn, float x, float y, float z, float texU, float texV, Color color) {
        bufferIn.pos(matrixEntryIn.getMatrix(), x, y, z).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).tex(texU, texV).overlay(0, 10).lightmap(240).normal(matrixEntryIn.getNormal(), 0.0F, 1.0F, 0.0F).endVertex();
    }
}
