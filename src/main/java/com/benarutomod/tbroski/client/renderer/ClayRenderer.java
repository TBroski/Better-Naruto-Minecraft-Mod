package com.benarutomod.tbroski.client.renderer;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.ClayEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class ClayRenderer extends LivingRenderer<ClayEntity, EntityModel<ClayEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/layer/clay.png");

    public ClayRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new EntityModel<ClayEntity>() {
            @Override
            public void setRotationAngles(ClayEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

            }

            @Override
            public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {

            }
        }, 0.1F);
    }

    @Override
    public void render(ClayEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        Minecraft.getInstance().getRenderManager().getRenderer(entityIn.getEntityToRepresent()).render(entityIn.getEntityToRepresent(), entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        System.out.println("CAlEd");
    }

    @Override
    public ResourceLocation getEntityTexture(ClayEntity entity) {
        return TEXTURE;
    }
}
