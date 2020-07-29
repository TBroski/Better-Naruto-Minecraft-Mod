package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.mobs.FrogEntity;
import com.benarutomod.tbroski.entity.models.FrogModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class HoodLayer extends LayerRenderer<FrogEntity, FrogModel<FrogEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/frog/hood.png");
    private final FrogModel<FrogEntity> frogModel = new FrogModel<>(0.5F);

    public HoodLayer(IEntityRenderer<FrogEntity, FrogModel<FrogEntity>> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, FrogEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getClothed()) {
            this.getEntityModel().copyModelAttributesTo(this.frogModel);
            this.frogModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.frogModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
            this.frogModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
