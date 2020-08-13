package com.benarutomod.tbroski.entity.models.kakuzu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

public class FireWindStyleMaskedAnimalModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm {

    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer body;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer head;

    public FireWindStyleMaskedAnimalModel() {
        textureWidth = 128;
        textureHeight = 128;

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(0.0F, 5.0F, 0.0F);
        leftLeg.setTextureOffset(62, 64).addBox(-8.0F, 2.0F, -4.0F, 8.0F, 17.0F, 8.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(0.0F, 5.0F, 0.0F);
        rightLeg.setTextureOffset(0, 57).addBox(0.0F, 2.0F, -4.0F, 8.0F, 17.0F, 8.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 5.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-9.0F, -24.0F, -4.0F, 18.0F, 26.0F, 9.0F, 0.0F, false);
        body.setTextureOffset(31, 35).addBox(-4.0F, -34.0F, -5.75F, 8.0F, 9.0F, 1.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(9.0F, -19.0F, 0.0F);
        leftArm.setTextureOffset(54, 0).addBox(-25.0F, 0.0F, -3.0F, 7.0F, 23.0F, 7.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-9.0F, -19.0F, 0.0F);
        rightArm.setTextureOffset(42, 42).addBox(18.0F, 0.0F, -3.0F, 7.0F, 23.0F, 7.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, 0.0F);
        head.setTextureOffset(0, 35).addBox(-5.0F, -54.0F, -5.0F, 10.0F, 11.0F, 11.0F, 0.0F, false);
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
        return null;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
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
