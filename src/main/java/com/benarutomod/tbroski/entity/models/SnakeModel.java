package com.benarutomod.tbroski.entity.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class SnakeModel<T extends LivingEntity> extends AgeableModel<T> {

    private final ModelRenderer tail;
    private final ModelRenderer neck;
    private final ModelRenderer head;

    public SnakeModel() {
        textureWidth = 16;
        textureHeight = 16;

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.5F, 24.0F, -1.0F);
        tail.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, 23.85F, -1.0F);
        neck.setTextureOffset(0, 6).addBox(-0.5F, -0.8617F, -4.6472F, 1.0F, 1.0F, 5.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -0.85F, -4.6F);
        neck.addChild(head);
        head.setTextureOffset(7, 7).addBox(-0.5F, -0.0839F, -1.715F, 1.0F, 1.0F, 2.0F, 0.0F, false);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of();
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entityIn.getMotion().z == 0 && entityIn.getMotion().x == 0) {
            this.neck.rotateAngleX = -1.1345F;
            this.head.rotateAngleX = 0.9599F;
        }
        else if (entityIn.getMotion().y > 0.2) {
            this.neck.rotateAngleX = -0.6109F;
            this.head.rotateAngleX = 0.3491F;
        }
        else {
            this.neck.rotateAngleX = 0F;
            this.head.rotateAngleX = 0F;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
        neck.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
