package com.benarutomod.tbroski.client.renderer.layers.models.jutsu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelGiant<T extends LivingEntity> extends AgeableModel<T> {

    private final ModelRenderer head;
    private final ModelRenderer body;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;

    public ModelGiant() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this); // -32
        head.setRotationPoint(4.0F, -8.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-12.0F, -24.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(4.0F, -12.0F, 0.0F);
        body.setTextureOffset(0, 32).addBox(-12.0F, -12.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(8.0F, -24.0F, 0.0F);
        left_arm.setTextureOffset(72, 72).addBox(0.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-8.0F, -24.0F, 0.0F);
        right_arm.setTextureOffset(64, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(0.1F, 0.0F, 0.0F);
        left_leg.setTextureOffset(0, 64).addBox(-0.1F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-0.1F, 0.0F, 0.0F);
        right_leg.setTextureOffset(48, 48).addBox(-7.9F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);

        this.body.rotateAngleY = 0.0F;
        this.right_arm.rotationPointZ = 0.0F;
        this.right_arm.rotationPointX = -5.0F;
        this.left_arm.rotationPointZ = 0.0F;
        this.left_arm.rotationPointX = 5.0F;

        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.right_arm.rotateAngleZ = 0.0F;
        this.left_arm.rotateAngleZ = 0.0F;
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_leg.rotateAngleY = 0.0F;
        this.left_leg.rotateAngleY = 0.0F;
        this.right_leg.rotateAngleZ = 0.0F;
        this.left_leg.rotateAngleZ = 0.0F;

        if (entityIn.isCrouching()) {
            this.body.rotateAngleX = 0.5F;
            this.right_arm.rotateAngleX += 0.4F;
            this.left_arm.rotateAngleX += 0.4F;
            this.right_leg.rotationPointZ = 4.0F;
            this.left_leg.rotationPointZ = 4.0F;
            this.right_leg.rotationPointY = 12.2F;
            this.left_leg.rotationPointY = 12.2F;
            this.head.rotationPointY = 4.2F;
            this.body.rotationPointY = 3.2F;
            this.left_arm.rotationPointY = 5.2F;
            this.right_arm.rotationPointY = 5.2F;
        } else {
            this.body.rotateAngleX = 0.0F;
            this.right_leg.rotationPointZ = 0.1F;
            this.left_leg.rotationPointZ = 0.1F;
            this.right_leg.rotationPointY = 12.0F;
            this.left_leg.rotationPointY = 12.0F;
            this.head.rotationPointY = 0.0F;
            this.body.rotationPointY = 0.0F;
            this.left_arm.rotationPointY = 2.0F;
            this.right_arm.rotationPointY = 2.0F;
        }

        this.right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.body);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
