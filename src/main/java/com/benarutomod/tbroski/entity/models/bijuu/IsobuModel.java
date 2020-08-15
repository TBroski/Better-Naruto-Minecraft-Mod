package com.benarutomod.tbroski.entity.models.bijuu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class IsobuModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm {

    private final ModelRenderer body;
    private final ModelRenderer rightCurve;
    private final ModelRenderer leftCurve;
    private final ModelRenderer tail1;
    private final ModelRenderer curve;
    private final ModelRenderer curve2;
    private final ModelRenderer web1;
    private final ModelRenderer web2;
    private final ModelRenderer web3;
    private final ModelRenderer web4;
    private final ModelRenderer tail3;
    private final ModelRenderer curve5;
    private final ModelRenderer curve6;
    private final ModelRenderer web9;
    private final ModelRenderer web10;
    private final ModelRenderer web11;
    private final ModelRenderer web12;
    private final ModelRenderer tail2;
    private final ModelRenderer curve3;
    private final ModelRenderer curve4;
    private final ModelRenderer web5;
    private final ModelRenderer web6;
    private final ModelRenderer web7;
    private final ModelRenderer web8;
    private final ModelRenderer head;
    private final ModelRenderer spike;
    private final ModelRenderer spike3;
    private final ModelRenderer spike4;
    private final ModelRenderer spike5;
    private final ModelRenderer spike6;
    private final ModelRenderer spike2;
    private final ModelRenderer bottomSpike;
    private final ModelRenderer bottomSpike3;
    private final ModelRenderer bottomSpike5;
    private final ModelRenderer bottomSpike6;
    private final ModelRenderer bottomSpike4;
    private final ModelRenderer bottomSpike2;
    private final ModelRenderer middleSpike;
    private final ModelRenderer middleSpike3;
    private final ModelRenderer middleSpike5;
    private final ModelRenderer middleSpike6;
    private final ModelRenderer middleSpike4;
    private final ModelRenderer middleSpike2;
    private final ModelRenderer rightArm;
    private final ModelRenderer armCurve;
    private final ModelRenderer leftArm;
    private final ModelRenderer armCurve2;

    public IsobuModel() {
        textureWidth = 512;
        textureHeight = 512;

        body = new ModelRenderer(this);
        body.setRotationPoint(5.0F, 9.0F, 5.0F);
        body.setTextureOffset(0, 54).addBox(-22.5F, -21.0F, -26.0F, 35.0F, 21.0F, 45.0F, 0.0F, false);
        body.setTextureOffset(54, 120).addBox(-21.5F, -19.0F, 19.0F, 33.0F, 11.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-22.5F, -23.0F, -29.0F, 35.0F, 2.0F, 52.0F, 0.0F, false);
        body.setTextureOffset(115, 54).addBox(-21.5F, 0.0F, -24.0F, 33.0F, 2.0F, 41.0F, 0.0F, false);

        rightCurve = new ModelRenderer(this);
        rightCurve.setRotationPoint(-23.0F, -20.5F, -3.0F);
        body.addChild(rightCurve);
        setRotationAngle(rightCurve, 0.0F, 0.0F, 0.4363F);
        rightCurve.setTextureOffset(0, 120).addBox(-0.6813F, -2.4155F, -26.0F, 1.0F, 7.0F, 52.0F, 0.0F, false);

        leftCurve = new ModelRenderer(this);
        leftCurve.setRotationPoint(13.0F, -20.5F, -3.0F);
        body.addChild(leftCurve);
        setRotationAngle(leftCurve, 0.0F, 0.0F, -0.4363F);
        leftCurve.setTextureOffset(108, 108).addBox(-0.3187F, -2.4155F, -26.0F, 1.0F, 7.0F, 52.0F, 0.0F, false);

        tail1 = new ModelRenderer(this);
        tail1.setRotationPoint(-14.0F, -14.0F, 23.0F);
        body.addChild(tail1);
        tail1.setTextureOffset(0, 88).addBox(-2.0F, -1.0F, -2.0F, 5.0F, 2.0F, 8.0F, 0.0F, false);

        curve = new ModelRenderer(this);
        curve.setRotationPoint(0.0F, 0.0F, 6.0F);
        tail1.addChild(curve);
        setRotationAngle(curve, -0.0873F, 0.0F, 0.0F);
        curve.setTextureOffset(115, 69).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 2.0F, 13.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(0.0F, 0.0F, 13.0F);
        curve.addChild(curve2);
        setRotationAngle(curve2, 0.6981F, 0.0F, 0.0F);
        curve2.setTextureOffset(0, 54).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 2.0F, 17.0F, 0.0F, false);

        web1 = new ModelRenderer(this);
        web1.setRotationPoint(3.3F, 0.0F, 18.0F);
        curve2.addChild(web1);
        setRotationAngle(web1, 0.0F, 0.4363F, 0.0F);
        web1.setTextureOffset(0, 60).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web2 = new ModelRenderer(this);
        web2.setRotationPoint(-2.4F, 0.0F, 18.0F);
        curve2.addChild(web2);
        setRotationAngle(web2, 0.0F, -0.4363F, 0.0F);
        web2.setTextureOffset(33, 56).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web3 = new ModelRenderer(this);
        web3.setRotationPoint(-0.5F, -0.3F, 18.7F);
        curve2.addChild(web3);
        setRotationAngle(web3, 0.1745F, -0.0873F, 0.0F);
        web3.setTextureOffset(6, 56).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web4 = new ModelRenderer(this);
        web4.setRotationPoint(1.5F, -0.3F, 18.7F);
        curve2.addChild(web4);
        setRotationAngle(web4, 0.1745F, 0.0873F, 0.0F);
        web4.setTextureOffset(27, 54).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        tail3 = new ModelRenderer(this);
        tail3.setRotationPoint(4.0F, -13.0F, 23.0F);
        body.addChild(tail3);
        tail3.setTextureOffset(0, 38).addBox(-2.0F, -1.0F, -2.0F, 5.0F, 2.0F, 8.0F, 0.0F, false);

        curve5 = new ModelRenderer(this);
        curve5.setRotationPoint(0.0F, 0.0F, 6.0F);
        tail3.addChild(curve5);
        setRotationAngle(curve5, 0.2618F, 0.0F, 0.0F);
        curve5.setTextureOffset(0, 73).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 2.0F, 13.0F, 0.0F, false);

        curve6 = new ModelRenderer(this);
        curve6.setRotationPoint(0.0F, 0.0F, 13.0F);
        curve5.addChild(curve6);
        setRotationAngle(curve6, 0.3491F, 0.0F, 0.0F);
        curve6.setTextureOffset(0, 0).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 2.0F, 17.0F, 0.0F, false);

        web9 = new ModelRenderer(this);
        web9.setRotationPoint(3.3F, 0.0F, 18.0F);
        curve6.addChild(web9);
        setRotationAngle(web9, 0.0F, 0.4363F, 0.0F);
        web9.setTextureOffset(6, 27).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web10 = new ModelRenderer(this);
        web10.setRotationPoint(-2.4F, 0.0F, 18.0F);
        curve6.addChild(web10);
        setRotationAngle(web10, 0.0F, -0.4363F, 0.0F);
        web10.setTextureOffset(0, 25).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web11 = new ModelRenderer(this);
        web11.setRotationPoint(-0.5F, -0.3F, 18.7F);
        curve6.addChild(web11);
        setRotationAngle(web11, 0.1745F, -0.0873F, 0.0F);
        web11.setTextureOffset(6, 21).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web12 = new ModelRenderer(this);
        web12.setRotationPoint(1.5F, -0.3F, 18.7F);
        curve6.addChild(web12);
        setRotationAngle(web12, 0.1745F, 0.0873F, 0.0F);
        web12.setTextureOffset(0, 19).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(-5.0F, -16.0F, 23.0F);
        body.addChild(tail2);
        tail2.setTextureOffset(26, 38).addBox(-2.0F, -1.0F, -2.0F, 5.0F, 2.0F, 8.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(0.0F, 0.0F, 6.0F);
        tail2.addChild(curve3);
        setRotationAngle(curve3, -0.2618F, 0.0F, 0.0F);
        curve3.setTextureOffset(115, 54).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 2.0F, 13.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(0.0F, 0.0F, 13.0F);
        curve3.addChild(curve4);
        setRotationAngle(curve4, -0.4363F, 0.0F, 0.0F);
        curve4.setTextureOffset(0, 19).addBox(-2.0F, -1.0F, 0.0F, 5.0F, 2.0F, 17.0F, 0.0F, false);

        web5 = new ModelRenderer(this);
        web5.setRotationPoint(3.3F, 0.0F, 18.0F);
        curve4.addChild(web5);
        setRotationAngle(web5, 0.0F, 0.4363F, 0.0F);
        web5.setTextureOffset(0, 54).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web6 = new ModelRenderer(this);
        web6.setRotationPoint(-2.4F, 0.0F, 18.0F);
        curve4.addChild(web6);
        setRotationAngle(web6, 0.0F, -0.4363F, 0.0F);
        web6.setTextureOffset(42, 15).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web7 = new ModelRenderer(this);
        web7.setRotationPoint(-0.5F, -0.3F, 18.7F);
        curve4.addChild(web7);
        setRotationAngle(web7, 0.1745F, -0.0873F, 0.0F);
        web7.setTextureOffset(24, 40).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        web8 = new ModelRenderer(this);
        web8.setRotationPoint(1.5F, -0.3F, 18.7F);
        curve4.addChild(web8);
        setRotationAngle(web8, 0.1745F, 0.0873F, 0.0F);
        web8.setTextureOffset(18, 38).addBox(-0.5F, -1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 4.0F, -21.0F);
        head.setTextureOffset(27, 0).addBox(-4.5F, -10.0F, -1.5F, 9.0F, 12.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(18, 88).addBox(-5.5F, -11.0F, -2.0F, 11.0F, 4.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(115, 84).addBox(-5.5F, 0.5F, -2.75F, 11.0F, 5.0F, 3.0F, 0.0F, false);

        spike = new ModelRenderer(this);
        spike.setRotationPoint(5.5F, -8.25F, -2.0F);
        head.addChild(spike);
        setRotationAngle(spike, -0.8727F, -0.5236F, 0.0F);
        spike.setTextureOffset(30, 38).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        spike3 = new ModelRenderer(this);
        spike3.setRotationPoint(4.0F, -7.25F, -3.0F);
        head.addChild(spike3);
        setRotationAngle(spike3, -0.7854F, -0.0873F, 0.0F);
        spike3.setTextureOffset(12, 19).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        spike4 = new ModelRenderer(this);
        spike4.setRotationPoint(-4.0F, -7.25F, -3.0F);
        head.addChild(spike4);
        setRotationAngle(spike4, -0.7854F, 0.0873F, 0.0F);
        spike4.setTextureOffset(13, 11).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        spike5 = new ModelRenderer(this);
        spike5.setRotationPoint(-1.5F, -7.25F, -3.0F);
        head.addChild(spike5);
        setRotationAngle(spike5, -0.6981F, 0.0873F, 0.0F);
        spike5.setTextureOffset(0, 38).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        spike6 = new ModelRenderer(this);
        spike6.setRotationPoint(1.5F, -7.25F, -3.0F);
        head.addChild(spike6);
        setRotationAngle(spike6, -0.6981F, -0.0873F, 0.0F);
        spike6.setTextureOffset(0, 31).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        spike2 = new ModelRenderer(this);
        spike2.setRotationPoint(-5.5F, -8.25F, -2.0F);
        head.addChild(spike2);
        setRotationAngle(spike2, -0.8727F, 0.5236F, 0.0F);
        spike2.setTextureOffset(4, 38).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        bottomSpike = new ModelRenderer(this);
        bottomSpike.setRotationPoint(-6.0F, 5.25F, -2.75F);
        head.addChild(bottomSpike);
        setRotationAngle(bottomSpike, -0.4363F, 0.6109F, 0.6109F);
        bottomSpike.setTextureOffset(27, 14).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        bottomSpike3 = new ModelRenderer(this);
        bottomSpike3.setRotationPoint(-3.25F, 5.25F, -2.75F);
        head.addChild(bottomSpike3);
        setRotationAngle(bottomSpike3, -0.4363F, 0.0F, 0.0F);
        bottomSpike3.setTextureOffset(24, 38).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        bottomSpike5 = new ModelRenderer(this);
        bottomSpike5.setRotationPoint(-1.25F, 5.25F, -2.75F);
        head.addChild(bottomSpike5);
        setRotationAngle(bottomSpike5, -0.4363F, 0.0F, 0.0F);
        bottomSpike5.setTextureOffset(27, 19).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        bottomSpike6 = new ModelRenderer(this);
        bottomSpike6.setRotationPoint(1.25F, 5.25F, -2.75F);
        head.addChild(bottomSpike6);
        setRotationAngle(bottomSpike6, -0.4363F, 0.0F, 0.0F);
        bottomSpike6.setTextureOffset(8, 12).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        bottomSpike4 = new ModelRenderer(this);
        bottomSpike4.setRotationPoint(3.25F, 5.25F, -2.75F);
        head.addChild(bottomSpike4);
        setRotationAngle(bottomSpike4, -0.4363F, 0.0F, 0.0F);
        bottomSpike4.setTextureOffset(18, 38).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        bottomSpike2 = new ModelRenderer(this);
        bottomSpike2.setRotationPoint(6.0F, 5.25F, -2.75F);
        head.addChild(bottomSpike2);
        setRotationAngle(bottomSpike2, -0.4363F, -0.6109F, -0.6109F);
        bottomSpike2.setTextureOffset(13, 5).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        middleSpike = new ModelRenderer(this);
        middleSpike.setRotationPoint(5.25F, 0.0F, -2.5F);
        head.addChild(middleSpike);
        setRotationAngle(middleSpike, 0.2618F, 0.0F, 0.2618F);
        middleSpike.setTextureOffset(12, 27).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        middleSpike3 = new ModelRenderer(this);
        middleSpike3.setRotationPoint(3.25F, 0.0F, -2.5F);
        head.addChild(middleSpike3);
        setRotationAngle(middleSpike3, 0.2618F, 0.0F, 0.0F);
        middleSpike3.setTextureOffset(4, 12).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        middleSpike5 = new ModelRenderer(this);
        middleSpike5.setRotationPoint(1.5F, 0.0F, -2.5F);
        head.addChild(middleSpike5);
        setRotationAngle(middleSpike5, 0.2618F, 0.0F, 0.0F);
        middleSpike5.setTextureOffset(6, 19).addBox(-0.75F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        middleSpike6 = new ModelRenderer(this);
        middleSpike6.setRotationPoint(-1.25F, 0.0F, -2.5F);
        head.addChild(middleSpike6);
        setRotationAngle(middleSpike6, 0.2618F, 0.0F, 0.0F);
        middleSpike6.setTextureOffset(0, 19).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        middleSpike4 = new ModelRenderer(this);
        middleSpike4.setRotationPoint(-3.25F, 0.0F, -2.5F);
        head.addChild(middleSpike4);
        setRotationAngle(middleSpike4, 0.2618F, 0.0F, 0.0F);
        middleSpike4.setTextureOffset(0, 12).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        middleSpike2 = new ModelRenderer(this);
        middleSpike2.setRotationPoint(-5.25F, 0.0F, -2.5F);
        head.addChild(middleSpike2);
        setRotationAngle(middleSpike2, 0.2618F, 0.0F, -0.2618F);
        middleSpike2.setTextureOffset(0, 25).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-14.0F, 11.0F, -12.0F);
        rightArm.setTextureOffset(122, 0).addBox(-3.0F, -2.0F, -3.0F, 5.0F, 10.0F, 5.0F, 0.0F, false);

        armCurve = new ModelRenderer(this);
        armCurve.setRotationPoint(-1.0F, 8.0F, -1.0F);
        rightArm.addChild(armCurve);
        setRotationAngle(armCurve, 0.5236F, 0.0F, 0.0F);
        armCurve.setTextureOffset(20, 120).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 10.0F, 5.0F, 0.0F, false);
        armCurve.setTextureOffset(0, 6).addBox(-2.0F, 10.0F, -2.0F, 5.0F, 4.0F, 2.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(15.0F, 11.0F, -14.0F);
        leftArm.setTextureOffset(0, 120).addBox(-3.0F, -2.0F, -1.0F, 5.0F, 10.0F, 5.0F, 0.0F, false);

        armCurve2 = new ModelRenderer(this);
        armCurve2.setRotationPoint(0.0F, 8.0F, 1.0F);
        leftArm.addChild(armCurve2);
        setRotationAngle(armCurve2, 0.5236F, 0.0F, 0.0F);
        armCurve2.setTextureOffset(27, 19).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 10.0F, 5.0F, 0.0F, false);
        armCurve2.setTextureOffset(0, 0).addBox(-3.0F, 10.0F, -2.0F, 5.0F, 4.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
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
        float scaleFactor = 5.0F;
        matrixStack.push();
        matrixStack.translate(0F, 1.5F - 1.5F * scaleFactor, 0F);
        matrixStack.scale(scaleFactor, scaleFactor, scaleFactor);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        matrixStack.pop();
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
