package com.benarutomod.tbroski.entity.models.kakuzu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

public class WaterStyleMaskedAnimalModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm {

    private final ModelRenderer backLeftLeg;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer frontRightLeg;
    private final ModelRenderer rotatedbit;
    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer rotatedbit2;
    private final ModelRenderer body;
    private final ModelRenderer head;

    public WaterStyleMaskedAnimalModel() {
        textureWidth = 128;
        textureHeight = 128;

        backLeftLeg = new ModelRenderer(this);
        backLeftLeg.setRotationPoint(-6.0F, 9.0F, 19.0F);
        backLeftLeg.setTextureOffset(20, 55).addBox(-3.0F, -1.0F, -4.0F, 5.0F, 16.0F, 5.0F, 0.0F, false);

        backRightLeg = new ModelRenderer(this);
        backRightLeg.setRotationPoint(6.0F, 9.0F, 19.0F);
        backRightLeg.setTextureOffset(0, 55).addBox(-2.0F, -1.0F, -4.0F, 5.0F, 16.0F, 5.0F, 0.0F, false);

        frontRightLeg = new ModelRenderer(this);
        frontRightLeg.setRotationPoint(-9.0F, 5.0F, -18.0F);
        frontRightLeg.setTextureOffset(15, 16).addBox(17.0F, 3.0F, -1.7F, 5.0F, 16.0F, 5.0F, 0.0F, false);

        rotatedbit = new ModelRenderer(this);
        rotatedbit.setRotationPoint(17.5F, 1.6F, 0.8F);
        frontRightLeg.addChild(rotatedbit);
        setRotationAngle(rotatedbit, 0.0F, 0.0F, -0.6981F);
        rotatedbit.setTextureOffset(40, 55).addBox(-2.5F, -4.0F, -2.5F, 5.0F, 8.0F, 5.0F, 0.0F, false);

        frontLeftLeg = new ModelRenderer(this);
        frontLeftLeg.setRotationPoint(9.0F, 5.0F, -18.0F);
        frontLeftLeg.setTextureOffset(0, 0).addBox(-22.0F, 3.0F, -1.7F, 5.0F, 16.0F, 5.0F, 0.0F, false);

        rotatedbit2 = new ModelRenderer(this);
        rotatedbit2.setRotationPoint(-17.7F, 1.3F, 0.8F);
        frontLeftLeg.addChild(rotatedbit2);
        setRotationAngle(rotatedbit2, 0.0F, 0.0F, 0.6109F);
        rotatedbit2.setTextureOffset(20, 0).addBox(-2.5F, -4.0F, -2.5F, 5.0F, 8.0F, 5.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -0.1F, 0.8F);
        setRotationAngle(body, -0.0873F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-9.0F, -7.5F, -20.0F, 18.0F, 15.0F, 40.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -3.5F, -19.5F);
        setRotationAngle(head, -0.0873F, 0.0F, 0.0F);
        head.setTextureOffset(60, 60).addBox(-4.0F, -4.5F, -0.5F, 8.0F, 9.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        backLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        backRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        frontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        frontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
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

    @Override
    public void translateHand(HandSide sideIn, MatrixStack matrixStackIn) {

    }
}
