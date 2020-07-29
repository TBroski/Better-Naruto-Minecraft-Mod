package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class ClayLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation resourcelocation = new ResourceLocation(Main.MODID, "textures/entity/layer/clay.png");
    private EntityModel model;

    public ClayLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
        this.model = entityRendererIn.getEntityModel();
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entitylivingbaseIn.getEntity().getPersistentData().getInt("affiliatedclayplayer") != 0) //entitylivingbaseIn.getEntity().getPersistentData().getInt("affiliatedplayer") != 0
        {
            matrixStackIn.push();
            this.getEntityModel().copyModelAttributesTo(this.model);
            this.model.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(bufferIn, this.model.getRenderType(resourcelocation), false, false);
            this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.pop();
        }
    }
}
