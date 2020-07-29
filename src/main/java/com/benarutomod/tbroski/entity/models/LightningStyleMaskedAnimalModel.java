package com.benarutomod.tbroski.entity.models;


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

public class LightningStyleMaskedAnimalModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm {

    private final ModelRenderer head;
    private final ModelRenderer leftHead;
    private final ModelRenderer rightHead;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;

    public LightningStyleMaskedAnimalModel() {
        textureWidth = 256;
        textureHeight = 256;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -26.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-25.0F, -2.0F, -4.0F, 50.0F, 2.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 19).addBox(-21.0F, 0.0F, -4.0F, 43.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 10).addBox(-21.0F, -3.0F, -4.0F, 43.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 37).addBox(-19.0F, 1.0F, -4.0F, 39.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 28).addBox(-19.0F, -4.0F, -4.0F, 39.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 46).addBox(-17.0F, 2.0F, -4.0F, 35.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 73).addBox(-11.0F, 5.0F, -4.0F, 22.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 82).addBox(-9.0F, 6.0F, -4.0F, 18.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 64).addBox(-13.0F, 4.0F, -4.0F, 26.0F, 1.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(0, 55).addBox(-15.0F, 3.0F, -4.0F, 31.0F, 1.0F, 8.0F, 0.0F, false);

        leftHead = new ModelRenderer(this);
        leftHead.setRotationPoint(-16.3175F, 2.9362F, 0.0F);
        head.addChild(leftHead);
        setRotationAngle(leftHead, 0.0F, 0.0F, 0.4363F);
        leftHead.setTextureOffset(78, 47).addBox(-8.0F, -0.5F, -4.0F, 22.0F, 1.0F, 8.0F, 0.0F, false);

        rightHead = new ModelRenderer(this);
        rightHead.setRotationPoint(16.6825F, 2.9362F, 0.0F);
        head.addChild(rightHead);
        setRotationAngle(rightHead, 0.0F, 0.0F, -0.4363F);
        rightHead.setTextureOffset(70, 56).addBox(-15.0F, -0.5F, -4.0F, 23.0F, 1.0F, 8.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(0.0F, 5.0F, 0.0F);
        leftLeg.setTextureOffset(96, 96).addBox(-7.0F, 0.0F, -3.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(0.0F, 5.0F, 0.0F);
        rightLeg.setTextureOffset(95, 21).addBox(0.0F, 0.0F, -3.0F, 7.0F, 19.0F, 7.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 5.0F, 0.0F);
        body.setTextureOffset(61, 66).addBox(-7.0F, -24.0F, -3.0F, 14.0F, 24.0F, 7.0F, 0.0F, false);
        body.setTextureOffset(56, 97).addBox(-4.0F, -33.0F, -4.75F, 8.0F, 9.0F, 1.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(7.0F, -16.0F, 0.0F);
        setRotationAngle(leftArm, 0.0F, 0.0F, 0.2618F);
        leftArm.setTextureOffset(28, 91).addBox(-19.9647F, 0.8637F, -3.0F, 7.0F, 23.0F, 7.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-7.0F, -16.0F, 0.0F);
        setRotationAngle(rightArm, 0.0F, 0.0F, -0.2618F);
        rightArm.setTextureOffset(0, 91).addBox(12.9647F, 0.8637F, -3.0F, 7.0F, 23.0F, 7.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.body);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
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
