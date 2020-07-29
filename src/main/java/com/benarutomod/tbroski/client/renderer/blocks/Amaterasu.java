package com.benarutomod.tbroski.client.renderer.blocks;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.BlockInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Amaterasu {

    public static final Material LOCATION_FIRE_0 = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Main.MODID, "blocks/amaterasu_0"));
    public static final Material LOCATION_FIRE_1 = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation(Main.MODID, "blocks/amaterasu_1"));

    @SubscribeEvent
    public void renderOverlay(TickEvent.PlayerTickEvent event) {
        if (event.player.getPersistentData().getBoolean("amaterasufire") && event.player.world.isRemote) {
            event.player.getPersistentData().putInt("amaterasuframe", event.player.getPersistentData().getInt("amaterasuframe") + 1);
            if (event.player.getPersistentData().getInt("amaterasuframe") >= 31) {
                event.player.getPersistentData().putInt("amaterasuframe", -1);
            }
        }
    }

    @SubscribeEvent
    public void renderAmaterasu(RenderHandEvent event) {
        if (Minecraft.getInstance().player.world.isRemote && Minecraft.getInstance().player.getPersistentData().getBoolean("amaterasufire") && Minecraft.getInstance().gameSettings.thirdPersonView == 0) {
            this.renderAmaterasuFireOverlay(Minecraft.getInstance(), event.getMatrixStack());
        }
    }

    @SubscribeEvent
    public void renderAmaterasuPlayer(RenderLivingEvent event) {
        if (event.getEntity().getPersistentData().getBoolean("amaterasufire")) {
            this.renderAmaterasuFire(event.getMatrixStack(), event.getBuffers(), event.getEntity());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean renderAmaterasuOverlay(PlayerEntity player, MatrixStack mat)
    {
        return renderBlockOverlay(player, mat, RenderBlockOverlayEvent.OverlayType.FIRE, BlockInit.AMATERASU.get().getDefaultState(), new BlockPos(player));
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean renderBlockOverlay(PlayerEntity player, MatrixStack mat, RenderBlockOverlayEvent.OverlayType type, BlockState block, BlockPos pos)
    {
        return MinecraftForge.EVENT_BUS.post(new RenderBlockOverlayEvent(player, mat, type, block, pos));
    }

    public static void renderAmaterasuFire(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, Entity entityIn) {
        TextureAtlasSprite textureatlassprite = LOCATION_FIRE_0.getSprite();
        TextureAtlasSprite textureatlassprite1 = LOCATION_FIRE_1.getSprite();
        matrixStackIn.push();
        float f = entityIn.getWidth() * 1.4F;
        matrixStackIn.scale(f, f, f);
        float f1 = 0.5F;
        float f2 = 0.0F;
        float f3 = entityIn.getHeight() / f;
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

    private static void fireVertex(MatrixStack.Entry matrixEntryIn, IVertexBuilder bufferIn, float x, float y, float z, float texU, float texV) {
        bufferIn.pos(matrixEntryIn.getMatrix(), x, y, z).color(255, 255, 255, 255).tex(texU, texV).overlay(0, 10).lightmap(240).normal(matrixEntryIn.getNormal(), 0.0F, 1.0F, 0.0F).endVertex();
    }


    private void renderAmaterasuFireOverlay(Minecraft minecraftIn, MatrixStack matrixStackIn) {
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.depthFunc(519);
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        TextureAtlasSprite textureatlassprite = this.LOCATION_FIRE_1.getSprite();
        minecraftIn.getTextureManager().bindTexture(textureatlassprite.getAtlasTexture().getTextureLocation());
        float f = textureatlassprite.getMinU();
        float f1 = textureatlassprite.getMaxU();
        float f2 = (f + f1) / 2.0F;
        float f3 = textureatlassprite.getMinV();
        float f4 = textureatlassprite.getMaxV();
        float f5 = (f3 + f4) / 2.0F;
        float f6 = textureatlassprite.getUvShrinkRatio();
        float f7 = MathHelper.lerp(f6, f, f2);
        float f8 = MathHelper.lerp(f6, f1, f2);
        float f9 = MathHelper.lerp(f6, f3, f5);
        float f10 = MathHelper.lerp(f6, f4, f5);
        float f11 = 1.0F;

        for(int i = 0; i < 2; ++i) {
            matrixStackIn.push();
            float f12 = -0.5F;
            float f13 = 0.5F;
            float f14 = -0.5F;
            float f15 = 0.5F;
            float f16 = -0.5F;
            matrixStackIn.translate((double)((float)(-(i * 2 - 1)) * 0.24F), (double)-0.3F, 0.0D);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees((float)(i * 2 - 1) * 10.0F));
            Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR_TEX);
            bufferbuilder.pos(matrix4f, -0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).tex(f8, f10).endVertex();
            bufferbuilder.pos(matrix4f, 0.5F, -0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).tex(f7, f10).endVertex();
            bufferbuilder.pos(matrix4f, 0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).tex(f7, f9).endVertex();
            bufferbuilder.pos(matrix4f, -0.5F, 0.5F, -0.5F).color(1.0F, 1.0F, 1.0F, 0.9F).tex(f8, f9).endVertex();
            bufferbuilder.finishDrawing();
            WorldVertexBufferUploader.draw(bufferbuilder);
            matrixStackIn.pop();
        }

        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.depthFunc(515);
    }
}
