package com.benarutomod.tbroski.entity.models;

import com.benarutomod.tbroski.entity.mobs.FrogEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.RabbitModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class FrogModel<T extends FrogEntity> extends AgeableModel<T> {

    private final ModelRenderer left_foot;
    private final ModelRenderer pointer4;
    private final ModelRenderer pointer3;
    private final ModelRenderer right_foot;
    private final ModelRenderer pointer;
    private final ModelRenderer pointer2;
    private final ModelRenderer left_thigh;
    private final ModelRenderer right_thigh;
    private final ModelRenderer body;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer head;
    private float jumpRotation;

    public FrogModel(float scale) {
        textureWidth = 64;
        textureHeight = 64;

        left_foot = new ModelRenderer(this);
        left_foot.setRotationPoint(-3.0F, 15.5F, 6.2F);
        left_foot.setTextureOffset(22, 0).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, scale);

        pointer4 = new ModelRenderer(this);
        pointer4.setRotationPoint(0.0F, 6.0F, -0.2F);
        left_foot.addChild(pointer4);
        setRotationAngle(pointer4, 0.0F, 0.7854F, 0.0F);
        pointer4.setTextureOffset(24, 15).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, scale);

        pointer3 = new ModelRenderer(this);
        pointer3.setRotationPoint(0.0F, 6.0F, -0.2F);
        left_foot.addChild(pointer3);
        setRotationAngle(pointer3, 0.0F, -0.7854F, 0.0F);
        pointer3.setTextureOffset(18, 24).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, scale);

        right_foot = new ModelRenderer(this);
        right_foot.setRotationPoint(3.0F, 15.5F, 6.2F);
        right_foot.setTextureOffset(13, 16).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, scale);

        pointer = new ModelRenderer(this);
        pointer.setRotationPoint(0.0F, 6.0F, -0.2F);
        right_foot.addChild(pointer);
        setRotationAngle(pointer, 0.0F, -0.7854F, 0.0F);
        pointer.setTextureOffset(27, 20).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, scale);

        pointer2 = new ModelRenderer(this);
        pointer2.setRotationPoint(0.0F, 6.0F, -0.2F);
        right_foot.addChild(pointer2);
        setRotationAngle(pointer2, 0.0F, 0.7854F, 0.0F);
        pointer2.setTextureOffset(26, 26).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, scale);

        left_thigh = new ModelRenderer(this);
        left_thigh.setRotationPoint(-3.0F, 17.0F, 4.5F);
        left_thigh.setTextureOffset(18, 29).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 3.0F, scale);

        right_thigh = new ModelRenderer(this);
        right_thigh.setRotationPoint(3.0F, 17.0F, 4.5F);
        right_thigh.setTextureOffset(8, 29).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 3.0F, scale);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 19.0F, 8.0F);
        body.setTextureOffset(0, 0).addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F, scale);
        setRotationAngle(body, -0.34906584F, 0.0F, 0.0F);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(-3.0F, 17.0F, -1.0F);
        left_arm.setTextureOffset(0, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, scale);
        setRotationAngle(left_arm, -0.17453292F, 0.0F, 0.0F);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(3.0F, 17.0F, -1.0F);
        right_arm.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, scale);
        setRotationAngle(right_arm, -0.17453292F, 0.0F, 0.0F);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 16.0F, -1.0F);
        head.setTextureOffset(0, 15).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 3.0F, 5.0F, scale);
        head.setTextureOffset(0, 23).addBox(-2.0F, -1.0F, -4.8F, 4.0F, 1.0F, 5.0F, scale);
        head.setTextureOffset(28, 31).addBox(1.5F, -5.0F, -3.25F, 2.0F, 2.0F, 2.0F, scale);
        head.setTextureOffset(13, 24).addBox(-3.5F, -5.0F, -3.25F, 2.0F, 2.0F, 2.0F, scale);
    }

    public FrogModel() {
        textureWidth = 64;
        textureHeight = 64;

        left_foot = new ModelRenderer(this);
        left_foot.setRotationPoint(-3.0F, 15.5F, 6.2F);
        left_foot.setTextureOffset(22, 0).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, 0.0F, false);

        pointer4 = new ModelRenderer(this);
        pointer4.setRotationPoint(0.0F, 6.0F, -0.2F);
        left_foot.addChild(pointer4);
        setRotationAngle(pointer4, 0.0F, 0.7854F, 0.0F);
        pointer4.setTextureOffset(24, 15).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        pointer3 = new ModelRenderer(this);
        pointer3.setRotationPoint(0.0F, 6.0F, -0.2F);
        left_foot.addChild(pointer3);
        setRotationAngle(pointer3, 0.0F, -0.7854F, 0.0F);
        pointer3.setTextureOffset(18, 24).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        right_foot = new ModelRenderer(this);
        right_foot.setRotationPoint(3.0F, 15.5F, 6.2F);
        right_foot.setTextureOffset(13, 16).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F, 0.0F, false);

        pointer = new ModelRenderer(this);
        pointer.setRotationPoint(0.0F, 6.0F, -0.2F);
        right_foot.addChild(pointer);
        setRotationAngle(pointer, 0.0F, -0.7854F, 0.0F);
        pointer.setTextureOffset(27, 20).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        pointer2 = new ModelRenderer(this);
        pointer2.setRotationPoint(0.0F, 6.0F, -0.2F);
        right_foot.addChild(pointer2);
        setRotationAngle(pointer2, 0.0F, 0.7854F, 0.0F);
        pointer2.setTextureOffset(26, 26).addBox(-1.0F, -0.5F, -3.5F, 2.0F, 1.0F, 4.0F, 0.0F, false);

        left_thigh = new ModelRenderer(this);
        left_thigh.setRotationPoint(-3.0F, 17.0F, 4.5F);
        left_thigh.setTextureOffset(18, 29).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
        setRotationAngle(left_thigh, -0.34906584F, 0.0F, 0.0F);

        right_thigh = new ModelRenderer(this);
        right_thigh.setRotationPoint(3.0F, 17.0F, 4.5F);
        right_thigh.setTextureOffset(8, 29).addBox(-1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
        setRotationAngle(right_thigh, -0.34906584F, 0.0F, 0.0F);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 19.0F, 8.0F);
        body.setTextureOffset(0, 0).addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F, 0.0F, false);
        setRotationAngle(body, -0.34906584F, 0.0F, 0.0F);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(-3.0F, 17.0F, -1.0F);
        left_arm.setTextureOffset(0, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
        setRotationAngle(left_arm, -0.17453292F, 0.0F, 0.0F);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(3.0F, 17.0F, -1.0F);
        right_arm.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
        setRotationAngle(right_arm, -0.17453292F, 0.0F, 0.0F);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 16.0F, -1.0F);
        head.setTextureOffset(0, 15).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 3.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(0, 23).addBox(-2.0F, -1.0F, -4.8F, 4.0F, 1.0F, 5.0F, 0.0F, false);
        head.setTextureOffset(28, 31).addBox(1.5F, -5.0F, -3.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(13, 24).addBox(-3.5F, -5.0F, -3.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
        float f = ageInTicks - (float)entityIn.ticksExisted;
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float)Math.PI);
        this.left_thigh.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
        this.right_thigh.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
        this.left_foot.rotateAngleX = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
        this.right_foot.rotateAngleX = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
        this.left_arm.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
        this.right_arm.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

        if (this.isChild) {
            matrixStack.push();
            matrixStack.scale(0.4F, 0.4F, 0.4F);
            matrixStack.translate(0.0D, 2.25D, 0.0D);

            left_foot.render(matrixStack, buffer, packedLight, packedOverlay);
            right_foot.render(matrixStack, buffer, packedLight, packedOverlay);
            left_thigh.render(matrixStack, buffer, packedLight, packedOverlay);
            right_thigh.render(matrixStack, buffer, packedLight, packedOverlay);
            body.render(matrixStack, buffer, packedLight, packedOverlay);
            left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
            right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
            head.render(matrixStack, buffer, packedLight, packedOverlay);

            matrixStack.pop();
        }
        else {
            matrixStack.push();
            matrixStack.scale(0.6F, 0.6F, 0.6F);
            matrixStack.translate(0.0D, 1.0D, 0.0D);

            left_foot.render(matrixStack, buffer, packedLight, packedOverlay);
            right_foot.render(matrixStack, buffer, packedLight, packedOverlay);
            left_thigh.render(matrixStack, buffer, packedLight, packedOverlay);
            right_thigh.render(matrixStack, buffer, packedLight, packedOverlay);
            body.render(matrixStack, buffer, packedLight, packedOverlay);
            left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
            right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
            head.render(matrixStack, buffer, packedLight, packedOverlay);

            matrixStack.pop();
        }
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
