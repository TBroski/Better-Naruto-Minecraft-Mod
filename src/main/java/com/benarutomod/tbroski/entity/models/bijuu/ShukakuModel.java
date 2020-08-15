package com.benarutomod.tbroski.entity.models.bijuu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ShukakuModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {

    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer curve1;
    private final ModelRenderer curve2;
    private final ModelRenderer neck;
    private final ModelRenderer head;
    private final ModelRenderer chin;
    private final ModelRenderer rightEar;
    private final ModelRenderer leftEar;
    private final ModelRenderer leftLeg;
    private final ModelRenderer leftArm;
    private final ModelRenderer leftElbow;
    private final ModelRenderer hand;
    private final ModelRenderer rightArm;
    private final ModelRenderer rightElbow;
    private final ModelRenderer hand2;
    private final ModelRenderer rightLeg;

    public ShukakuModel() {
        textureWidth = 512;
        textureHeight = 512;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 11.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-25.0F, -30.0F, -11.0F, 50.0F, 37.0F, 24.0F, 0.0F, false);
        body.setTextureOffset(140, 61).addBox(-21.0F, -18.0F, -16.0F, 42.0F, 22.0F, 5.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 0.0F, 13.0F);
        body.addChild(tail);
        setRotationAngle(tail, 0.9599F, 0.0F, -0.0873F);
        tail.setTextureOffset(142, 158).addBox(-4.6101F, -6.0002F, -5.7122F, 10.0F, 10.0F, 28.0F, 0.0F, false);

        curve1 = new ModelRenderer(this);
        curve1.setRotationPoint(0.0F, 0.0F, 28.0F);
        tail.addChild(curve1);
        setRotationAngle(curve1, 0.4363F, 0.0F, 0.0F);
        curve1.setTextureOffset(100, 100).addBox(-5.6101F, -9.7891F, -6.8674F, 12.0F, 12.0F, 40.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(0.0F, 0.0F, 40.0F);
        curve1.addChild(curve2);
        setRotationAngle(curve2, 0.2618F, 0.0F, 0.0F);
        curve2.setTextureOffset(63, 107).addBox(-4.6101F, -10.6078F, -6.9468F, 10.0F, 10.0F, 7.0F, 0.0F, false);
        curve2.setTextureOffset(0, 151).addBox(-3.6101F, -9.6078F, 0.0532F, 8.0F, 8.0F, 6.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, -39.6F, -3.4F);
        body.addChild(neck);
        setRotationAngle(neck, 0.4363F, 0.0F, 0.0F);
        neck.setTextureOffset(0, 61).addBox(-23.0F, -6.6558F, -13.4583F, 46.0F, 22.0F, 24.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -26.0F, -3.0F);
        head.setTextureOffset(0, 107).addBox(-10.0F, -11.0F, -21.0F, 20.0F, 21.0F, 23.0F, 0.0F, false);
        head.setTextureOffset(140, 88).addBox(-5.0F, -2.0F, -27.0F, 10.0F, 5.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(63, 124).addBox(-4.0F, -2.5F, -26.0F, 8.0F, 1.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(107, 123).addBox(-4.5F, 3.0F, -26.5F, 9.0F, 3.0F, 6.0F, 0.0F, false);

        chin = new ModelRenderer(this);
        chin.setRotationPoint(0.0F, 3.0F, -20.0F);
        head.addChild(chin);
        setRotationAngle(chin, -0.9599F, 0.0F, 0.0F);
        chin.setTextureOffset(86, 152).addBox(-9.0F, -7.9552F, 6.1156F, 18.0F, 10.0F, 24.0F, 0.0F, false);

        rightEar = new ModelRenderer(this);
        rightEar.setRotationPoint(-10.0F, -18.0F, -7.0F);
        head.addChild(rightEar);
        setRotationAngle(rightEar, 0.0F, 0.0F, 0.6981F);
        rightEar.setTextureOffset(90, 107).addBox(1.1498F, 2.4014F, 0.0F, 7.0F, 6.0F, 1.0F, 0.0F, false);

        leftEar = new ModelRenderer(this);
        leftEar.setRotationPoint(10.0F, -18.0F, -7.0F);
        head.addChild(leftEar);
        setRotationAngle(leftEar, 0.0F, 0.0F, -0.6981F);
        leftEar.setTextureOffset(0, 107).addBox(-8.1498F, 2.4014F, 0.0F, 7.0F, 6.0F, 1.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(-25.0F, 11.0F, 3.0F);
        setRotationAngle(leftLeg, 0.0F, -0.2618F, 0.0F);
        leftLeg.setTextureOffset(0, 151).addBox(29.5F, 7.0F, -33.0F, 15.0F, 6.0F, 28.0F, 0.0F, false);
        leftLeg.setTextureOffset(76, 142).addBox(39.5F, 7.0F, -43.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);
        leftLeg.setTextureOffset(148, 34).addBox(35.0F, 7.0F, -43.0F, 4.0F, 6.0F, 10.0F, 0.0F, false);
        leftLeg.setTextureOffset(124, 0).addBox(29.5F, 7.0F, -43.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(22.0F, -39.0F, -8.0F);
        setRotationAngle(leftArm, 0.9599F, -0.2618F, 0.0F);
        leftArm.setTextureOffset(0, 185).addBox(1.0F, 6.015F, -32.7341F, 10.0F, 12.0F, 27.0F, 0.0F, false);

        leftElbow = new ModelRenderer(this);
        leftElbow.setRotationPoint(6.0F, 13.0F, -27.0F);
        leftArm.addChild(leftElbow);
        setRotationAngle(leftElbow, 0.1745F, 0.3491F, 0.0F);
        leftElbow.setTextureOffset(190, 127).addBox(-3.0388F, -7.9816F, -29.0036F, 10.0F, 12.0F, 26.0F, 0.0F, false);

        hand = new ModelRenderer(this);
        hand.setRotationPoint(-1.9929F, -14.8361F, 31.329F);
        leftElbow.addChild(hand);
        hand.setTextureOffset(0, 12).addBox(5.9541F, 6.8545F, -69.3326F, 3.0F, 3.0F, 9.0F, 0.0F, false);
        hand.setTextureOffset(116, 61).addBox(5.9541F, 15.8545F, -64.3326F, 3.0F, 3.0F, 4.0F, 0.0F, false);
        hand.setTextureOffset(74, 158).addBox(2.4541F, 6.8545F, -70.3326F, 3.0F, 3.0F, 10.0F, 0.0F, false);
        hand.setTextureOffset(0, 72).addBox(-1.0459F, 6.8545F, -68.3326F, 3.0F, 3.0F, 8.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-22.0F, -39.0F, -8.0F);
        setRotationAngle(rightArm, 0.9599F, 0.2618F, 0.0F);
        rightArm.setTextureOffset(164, 88).addBox(-11.0F, 6.015F, -32.7341F, 10.0F, 12.0F, 27.0F, 0.0F, false);

        rightElbow = new ModelRenderer(this);
        rightElbow.setRotationPoint(-6.0F, 13.0F, -27.0F);
        rightArm.addChild(rightElbow);
        setRotationAngle(rightElbow, 0.1745F, -0.3491F, 0.0F);
        rightElbow.setTextureOffset(74, 186).addBox(-6.9612F, -7.9816F, -29.0036F, 10.0F, 12.0F, 26.0F, 0.0F, false);

        hand2 = new ModelRenderer(this);
        hand2.setRotationPoint(1.9929F, -14.8361F, 31.329F);
        rightElbow.addChild(hand2);
        hand2.setTextureOffset(0, 0).addBox(-8.9541F, 6.8545F, -69.3326F, 3.0F, 3.0F, 9.0F, 0.0F, false);
        hand2.setTextureOffset(0, 114).addBox(-8.9541F, 15.8545F, -64.3326F, 3.0F, 3.0F, 4.0F, 0.0F, false);
        hand2.setTextureOffset(58, 151).addBox(-5.4541F, 6.8545F, -70.3326F, 3.0F, 3.0F, 10.0F, 0.0F, false);
        hand2.setTextureOffset(0, 61).addBox(-1.9541F, 6.8545F, -68.3326F, 3.0F, 3.0F, 8.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(25.0F, 11.0F, 3.0F);
        setRotationAngle(rightLeg, 0.0F, 0.2618F, 0.0F);
        rightLeg.setTextureOffset(148, 0).addBox(-44.5F, 7.0F, -33.0F, 15.0F, 6.0F, 28.0F, 0.0F, false);
        rightLeg.setTextureOffset(87, 123).addBox(-44.5F, 7.0F, -43.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);
        rightLeg.setTextureOffset(144, 6).addBox(-39.0F, 7.0F, -43.0F, 4.0F, 6.0F, 10.0F, 0.0F, false);
        rightLeg.setTextureOffset(97, 107).addBox(-34.5F, 7.0F, -43.0F, 5.0F, 6.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.rightArm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F) + 0.8F;
        this.leftArm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F) + 0.8F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.tail.rotateAngleX = ageInTicks;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        float scaleFactor = 4.0F;
        matrixStack.push();
        matrixStack.translate(0F, 1.5F - 1.5F * scaleFactor, 0F);
        matrixStack.scale(scaleFactor, scaleFactor, scaleFactor);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        matrixStack.pop();
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

    @Override
    public ModelRenderer getModelHead() {
        return this.head;
    }
}
