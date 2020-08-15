package com.benarutomod.tbroski.entity.models.bijuu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class MatatabiModel<T extends LivingEntity> extends AgeableModel<T> implements IHasHead {

    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer fLeftFoot;
    private final ModelRenderer backLeftLeg;
    private final ModelRenderer fLeftFoot2;
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer ear;
    private final ModelRenderer ear2;
    private final ModelRenderer leftTail;
    private final ModelRenderer curve;
    private final ModelRenderer curve2;
    private final ModelRenderer rightTail;
    private final ModelRenderer curve3;
    private final ModelRenderer curve4;
    private final ModelRenderer frontRightLeg;
    private final ModelRenderer fLeftFoot3;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer fLeftFoot4;

    public MatatabiModel() {
        textureWidth = 256;
        textureHeight = 256;

        frontLeftLeg = new ModelRenderer(this);
        frontLeftLeg.setRotationPoint(13.5F, -1.0F, -25.5F);
        setRotationAngle(frontLeftLeg, -0.1745F, 0.0F, 0.0F);
        frontLeftLeg.setTextureOffset(146, 177).addBox(-4.5F, -4.0F, -2.5F, 9.0F, 25.0F, 6.0F, 0.0F, false);

        fLeftFoot = new ModelRenderer(this);
        fLeftFoot.setRotationPoint(-0.5F, 24.0F, 1.5F);
        frontLeftLeg.addChild(fLeftFoot);
        setRotationAngle(fLeftFoot, 0.1745F, 0.0F, 0.0F);
        fLeftFoot.setTextureOffset(100, 177).addBox(-4.0F, -4.0F, -12.0F, 9.0F, 5.0F, 14.0F, 0.0F, false);

        backLeftLeg = new ModelRenderer(this);
        backLeftLeg.setRotationPoint(13.5F, -1.0F, 54.5F);
        setRotationAngle(backLeftLeg, -0.1745F, 0.0F, 0.0F);
        backLeftLeg.setTextureOffset(150, 97).addBox(-4.5F, -4.0F, -2.5F, 9.0F, 25.0F, 6.0F, 0.0F, false);

        fLeftFoot2 = new ModelRenderer(this);
        fLeftFoot2.setRotationPoint(-0.5F, 24.0F, 1.5F);
        backLeftLeg.addChild(fLeftFoot2);
        setRotationAngle(fLeftFoot2, 0.1745F, 0.0F, 0.0F);
        fLeftFoot2.setTextureOffset(146, 158).addBox(-4.0F, -4.0F, -12.0F, 9.0F, 5.0F, 14.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -22.0F, -31.9F);
        body.setTextureOffset(0, 0).addBox(-16.0F, -8.0F, -0.1F, 32.0F, 29.0F, 59.0F, 0.0F, false);
        body.setTextureOffset(0, 88).addBox(-16.0F, -6.0F, 58.9F, 32.0F, 27.0F, 32.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(8.5F, -8.0F, -32.0F);
        setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
        head.setTextureOffset(0, 147).addBox(-20.5F, -17.0F, -27.0F, 24.0F, 24.0F, 26.0F, 1.0F, false);
        head.setTextureOffset(0, 46).addBox(-15.5F, -6.0F, -33.0F, 14.0F, 8.0F, 4.0F, 1.0F, false);

        ear = new ModelRenderer(this);
        ear.setRotationPoint(0.0F, -18.0F, -9.0F);
        head.addChild(ear);
        setRotationAngle(ear, -0.4363F, 0.0F, 0.6109F);
        ear.setTextureOffset(74, 158).addBox(-1.0F, -11.0F, 0.0F, 9.0F, 14.0F, 1.0F, 0.0F, false);

        ear2 = new ModelRenderer(this);
        ear2.setRotationPoint(-17.0F, -18.0F, -9.0F);
        head.addChild(ear2);
        setRotationAngle(ear2, -0.4363F, 0.0F, -0.6109F);
        ear2.setTextureOffset(0, 147).addBox(-8.0F, -11.0F, 0.0F, 9.0F, 14.0F, 1.0F, 0.0F, false);

        leftTail = new ModelRenderer(this);
        leftTail.setRotationPoint(4.0F, -16.0F, 58.0F);
        setRotationAngle(leftTail, 0.2618F, 0.1745F, 0.0F);
        leftTail.setTextureOffset(0, 23).addBox(-3.0F, -10.0F, 0.0F, 7.0F, 7.0F, 16.0F, 0.0F, false);

        curve = new ModelRenderer(this);
        curve.setRotationPoint(0.0F, 0.0F, 16.0F);
        leftTail.addChild(curve);
        setRotationAngle(curve, 0.6109F, 0.0F, 0.0F);
        curve.setTextureOffset(150, 97).addBox(-4.0F, -11.0F, 0.0F, 9.0F, 9.0F, 32.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(0.0F, 0.0F, 32.0F);
        curve.addChild(curve2);
        setRotationAngle(curve2, 0.3491F, 0.0F, 0.0F);
        curve2.setTextureOffset(123, 0).addBox(-5.0F, -12.0F, 0.0F, 11.0F, 11.0F, 40.0F, 0.0F, false);
        curve2.setTextureOffset(30, 23).addBox(-4.0F, -11.0F, 40.0F, 9.0F, 9.0F, 3.0F, 0.0F, false);
        curve2.setTextureOffset(96, 107).addBox(-3.0F, -10.0F, 43.0F, 7.0F, 7.0F, 3.0F, 0.0F, false);

        rightTail = new ModelRenderer(this);
        rightTail.setRotationPoint(-4.0F, -16.0F, 58.0F);
        setRotationAngle(rightTail, 0.2618F, -0.1745F, 0.0F);
        rightTail.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, 0.0F, 7.0F, 7.0F, 16.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(0.0F, 0.0F, 16.0F);
        rightTail.addChild(curve3);
        setRotationAngle(curve3, 0.6109F, 0.0F, 0.0F);
        curve3.setTextureOffset(150, 56).addBox(-5.0F, -11.0F, 0.0F, 9.0F, 9.0F, 32.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(0.0F, 0.0F, 32.0F);
        curve3.addChild(curve4);
        setRotationAngle(curve4, 0.3491F, 0.0F, 0.0F);
        curve4.setTextureOffset(88, 107).addBox(-6.0F, -12.0F, 0.0F, 11.0F, 11.0F, 40.0F, 0.0F, false);
        curve4.setTextureOffset(30, 0).addBox(-5.0F, -11.0F, 40.0F, 9.0F, 9.0F, 3.0F, 0.0F, false);
        curve4.setTextureOffset(36, 46).addBox(-4.0F, -10.0F, 43.0F, 7.0F, 7.0F, 3.0F, 0.0F, false);

        frontRightLeg = new ModelRenderer(this);
        frontRightLeg.setRotationPoint(-13.5F, -1.0F, -25.5F);
        setRotationAngle(frontRightLeg, -0.1745F, 0.0F, 0.0F);
        frontRightLeg.setTextureOffset(123, 0).addBox(-4.5F, -4.0F, -2.5F, 9.0F, 25.0F, 6.0F, 0.0F, false);

        fLeftFoot3 = new ModelRenderer(this);
        fLeftFoot3.setRotationPoint(0.5F, 24.0F, 1.5F);
        frontRightLeg.addChild(fLeftFoot3);
        setRotationAngle(fLeftFoot3, 0.1745F, 0.0F, 0.0F);
        fLeftFoot3.setTextureOffset(100, 158).addBox(-5.0F, -4.0F, -12.0F, 9.0F, 5.0F, 14.0F, 0.0F, false);

        backRightLeg = new ModelRenderer(this);
        backRightLeg.setRotationPoint(-13.5F, -1.0F, 54.5F);
        setRotationAngle(backRightLeg, -0.1745F, 0.0F, 0.0F);
        backRightLeg.setTextureOffset(0, 88).addBox(-4.5F, -4.0F, -2.5F, 9.0F, 25.0F, 6.0F, 0.0F, false);

        fLeftFoot4 = new ModelRenderer(this);
        fLeftFoot4.setRotationPoint(0.5F, 24.0F, 1.5F);
        backRightLeg.addChild(fLeftFoot4);
        setRotationAngle(fLeftFoot4, 0.1745F, 0.0F, 0.0F);
        fLeftFoot4.setTextureOffset(96, 88).addBox(-5.0F, -4.0F, -12.0F, 9.0F, 5.0F, 14.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.backRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
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
        float scaleFactor = 4.0F;
        matrixStack.push();
        matrixStack.translate(0F, 1.5F - 1.5F * scaleFactor, 0F);
        matrixStack.scale(scaleFactor, scaleFactor, scaleFactor);
        frontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        backLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leftTail.render(matrixStack, buffer, packedLight, packedOverlay);
        rightTail.render(matrixStack, buffer, packedLight, packedOverlay);
        frontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        backRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        matrixStack.pop();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public ModelRenderer getModelHead() {
        return this.head;
    }
}
