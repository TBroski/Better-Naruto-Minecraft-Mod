package com.benarutomod.tbroski.client.renderer.layers.models.susanoo;

import com.benarutomod.tbroski.Main;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;

public class ModelSasukeSusanoo<T extends LivingEntity> extends ModelSusanoo<T> {

    private static final ResourceLocation TEMPLATE = new ResourceLocation(Main.MODID, "textures/entity/layer/susanoo/stage3/sasuke/template.png");

    private final ModelRenderer body;
    private final ModelRenderer curve3;
    private final ModelRenderer curve4;
    private final ModelRenderer shoulder;
    private final ModelRenderer curve2;
    private final ModelRenderer curve7;
    private final ModelRenderer shoulder2;
    private final ModelRenderer head;
    private final ModelRenderer horn;
    private final ModelRenderer curve5;
    private final ModelRenderer horn2;
    private final ModelRenderer curve6;
    private final ModelRenderer leftArm;
    private final ModelRenderer elbow;
    private final ModelRenderer hand;
    private final ModelRenderer rightArm;
    private final ModelRenderer elbow2;
    private final ModelRenderer hand2;

    public ModelSasukeSusanoo() {
        textureWidth = 512;
        textureHeight = 512;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 12.0F, 0.0F);
        body.setTextureOffset(0, 122).addBox(-34.0F, -63.0F, 7.0F, 68.0F, 52.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-39.0F, -151.0F, 7.0F, 78.0F, 88.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(244, 272).addBox(39.0F, -151.0F, 7.0F, 22.0F, 8.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(244, 189).addBox(-61.0F, -151.0F, 7.0F, 22.0F, 8.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(396, 283).addBox(-63.0F, -160.0F, 7.0F, 5.0F, 9.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(0, 373).addBox(58.0F, -160.0F, 7.0F, 5.0F, 9.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(382, 64).addBox(-58.0F, -158.0F, 7.0F, 6.0F, 7.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(356, 372).addBox(52.0F, -158.0F, 7.0F, 6.0F, 7.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(44, 404).addBox(-52.0F, -155.0F, 7.0F, 6.0F, 4.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(402, 402).addBox(46.0F, -155.0F, 7.0F, 6.0F, 4.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(232, 411).addBox(-46.0F, -154.0F, 7.0F, 2.0F, 3.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(408, 0).addBox(44.0F, -154.0F, 7.0F, 2.0F, 3.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(74, 295).addBox(39.0F, -143.0F, 7.0F, 19.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(294, 53).addBox(-58.0F, -143.0F, 7.0F, 19.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(146, 311).addBox(39.0F, -132.0F, 7.0F, 16.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(298, 0).addBox(-55.0F, -132.0F, 7.0F, 16.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(322, 98).addBox(39.0F, -121.0F, 7.0F, 13.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(246, 314).addBox(-52.0F, -121.0F, 7.0F, 13.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(362, 143).addBox(38.25F, -110.0F, 7.0F, 11.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(224, 359).addBox(-49.25F, -110.0F, 7.0F, 11.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(362, 238).addBox(38.25F, -99.0F, 7.0F, 8.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(362, 188).addBox(-46.25F, -99.0F, 7.0F, 8.0F, 11.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(280, 370).addBox(37.25F, -88.0F, 7.0F, 4.0F, 16.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(366, 11).addBox(-41.25F, -88.0F, 7.0F, 4.0F, 16.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(76, 340).addBox(34.0F, -63.0F, 7.0F, 3.0F, 30.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(90, 408).addBox(40.0F, -88.0F, 7.0F, 4.0F, 7.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(190, 404).addBox(-44.0F, -88.0F, 7.0F, 4.0F, 7.0F, 34.0F, 0.0F, false);
        body.setTextureOffset(150, 356).addBox(-37.0F, -63.0F, 7.0F, 3.0F, 30.0F, 34.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(-32.0F, -10.0F, 28.0F);
        body.addChild(curve3);
        setRotationAngle(curve3, 0.0F, 0.0F, -0.0873F);
        curve3.setTextureOffset(224, 0).addBox(-3.0F, -54.0F, -21.0F, 3.0F, 53.0F, 34.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(0.0F, -54.0F, 0.0F);
        curve3.addChild(curve4);
        setRotationAngle(curve4, 0.0F, 0.0F, -0.1745F);
        curve4.setTextureOffset(0, 208).addBox(-3.0F, -103.0F, -21.0F, 3.0F, 103.0F, 34.0F, 0.0F, false);

        shoulder = new ModelRenderer(this);
        shoulder.setRotationPoint(0.0F, -103.0F, -21.0F);
        curve4.addChild(shoulder);
        setRotationAngle(shoulder, 0.0F, 0.0F, 2.2689F);
        shoulder.setTextureOffset(322, 280).addBox(0.0F, -30.0F, 0.0F, 3.0F, 30.0F, 34.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(32.0F, -10.0F, 28.0F);
        body.addChild(curve2);
        setRotationAngle(curve2, 0.0F, 0.0F, 0.0873F);
        curve2.setTextureOffset(74, 208).addBox(0.0F, -54.0F, -21.0F, 3.0F, 53.0F, 34.0F, 0.0F, false);

        curve7 = new ModelRenderer(this);
        curve7.setRotationPoint(0.0F, -54.0F, 0.0F);
        curve2.addChild(curve7);
        setRotationAngle(curve7, 0.0F, 0.0F, 0.1745F);
        curve7.setTextureOffset(170, 174).addBox(0.0F, -103.0F, -21.0F, 3.0F, 103.0F, 34.0F, 0.0F, false);

        shoulder2 = new ModelRenderer(this);
        shoulder2.setRotationPoint(0.0F, -103.0F, -21.0F);
        curve7.addChild(shoulder2);
        setRotationAngle(shoulder2, 0.0F, 0.0F, -2.2689F);
        shoulder2.setTextureOffset(322, 155).addBox(-3.0F, -30.0F, 0.0F, 3.0F, 30.0F, 34.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -151.0F, 28.0F);
        body.addChild(head);
        setRotationAngle(head, 0.2618F, 0.0F, 0.0F);
        head.setTextureOffset(204, 94).addBox(-16.0F, -34.0F, -30.0F, 31.0F, 39.0F, 28.0F, 0.0F, false);
        head.setTextureOffset(190, 0).addBox(-16.0F, -34.0F, -30.25F, 31.0F, 25.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(190, 26).addBox(-16.0F, 0.0F, -30.25F, 31.0F, 5.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 16).addBox(-16.0F, -9.0F, -30.25F, 4.0F, 9.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(11.0F, -9.0F, -30.25F, 4.0F, 9.0F, 1.0F, 0.0F, false);

        horn = new ModelRenderer(this);
        horn.setRotationPoint(-16.0F, -25.0F, -15.0F);
        head.addChild(horn);
        setRotationAngle(horn, 0.0F, 0.0F, 0.3491F);
        horn.setTextureOffset(0, 224).addBox(-9.0F, -5.0F, -4.0F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        curve5 = new ModelRenderer(this);
        curve5.setRotationPoint(-9.0F, 0.0F, 0.0F);
        horn.addChild(curve5);
        setRotationAngle(curve5, 0.0F, 0.0F, 0.9599F);
        curve5.setTextureOffset(244, 231).addBox(-17.0F, -5.0F, -4.0F, 17.0F, 5.0F, 5.0F, 0.0F, false);

        horn2 = new ModelRenderer(this);
        horn2.setRotationPoint(16.0F, -25.0F, -15.0F);
        head.addChild(horn2);
        setRotationAngle(horn2, 0.0F, 0.0F, -0.3491F);
        horn2.setTextureOffset(75, 208).addBox(0.0F, -5.0F, -4.0F, 9.0F, 5.0F, 5.0F, 0.0F, false);

        curve6 = new ModelRenderer(this);
        curve6.setRotationPoint(9.0F, 0.0F, 0.0F);
        horn2.addChild(curve6);
        setRotationAngle(curve6, 0.0F, 0.0F, -0.9599F);
        curve6.setTextureOffset(210, 189).addBox(0.0F, -5.0F, -4.0F, 17.0F, 5.0F, 5.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(-60.0F, -140.0F, 25.0F);
        body.addChild(leftArm);
        setRotationAngle(leftArm, 0.0F, 0.0F, -1.3963F);
        leftArm.setTextureOffset(244, 244).addBox(-49.0F, 0.0F, -8.0F, 49.0F, 14.0F, 14.0F, 0.0F, false);

        elbow = new ModelRenderer(this);
        elbow.setRotationPoint(-49.0F, 7.0F, 6.0F);
        leftArm.addChild(elbow);
        setRotationAngle(elbow, 0.0F, -0.6981F, 0.0F);
        elbow.setTextureOffset(0, 345).addBox(-41.0F, -7.0F, -14.0F, 41.0F, 14.0F, 14.0F, 0.0F, false);

        hand = new ModelRenderer(this);
        hand.setRotationPoint(0.0F, 0.0F, 0.0F);
        elbow.addChild(hand);
        hand.setTextureOffset(114, 208).addBox(-52.0F, -7.0F, -14.0F, 11.0F, 14.0F, 13.0F, 0.0F, false);
        hand.setTextureOffset(75, 222).addBox(-54.5F, -7.0F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand.setTextureOffset(187, 138).addBox(-52.5F, -7.0F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand.setTextureOffset(187, 122).addBox(-52.5F, -3.5F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand.setTextureOffset(170, 138).addBox(-52.5F, 0.25F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand.setTextureOffset(170, 122).addBox(-52.5F, 4.0F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand.setTextureOffset(0, 208).addBox(-54.5F, -3.5F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand.setTextureOffset(170, 138).addBox(-54.5F, 0.25F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand.setTextureOffset(170, 122).addBox(-54.5F, 4.0F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand.setTextureOffset(0, 208).addBox(-53.5F, -1.0F, -2.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        hand.setTextureOffset(17, 0).addBox(-53.5F, 5.0F, -7.0F, 2.0F, 3.0F, 5.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(60.0F, -140.0F, 25.0F);
        body.addChild(rightArm);
        setRotationAngle(rightArm, 0.0F, 0.0F, 1.3963F);
        rightArm.setTextureOffset(210, 161).addBox(0.0F, 0.0F, -8.0F, 49.0F, 14.0F, 14.0F, 0.0F, false);

        elbow2 = new ModelRenderer(this);
        elbow2.setRotationPoint(49.0F, 7.0F, 6.0F);
        rightArm.addChild(elbow2);
        setRotationAngle(elbow2, 0.0F, 0.6981F, 0.0F);
        elbow2.setTextureOffset(340, 344).addBox(0.0F, -7.0F, -14.0F, 41.0F, 14.0F, 14.0F, 0.0F, false);

        hand2 = new ModelRenderer(this);
        hand2.setRotationPoint(0.0F, 0.0F, 0.0F);
        elbow2.addChild(hand2);
        hand2.setTextureOffset(40, 208).addBox(41.0F, -7.0F, -14.0F, 11.0F, 14.0F, 13.0F, 0.0F, false);
        hand2.setTextureOffset(0, 138).addBox(52.5F, -7.0F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand2.setTextureOffset(17, 138).addBox(51.5F, -7.0F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand2.setTextureOffset(0, 138).addBox(51.5F, -3.5F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand2.setTextureOffset(17, 122).addBox(51.5F, 0.25F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand2.setTextureOffset(0, 122).addBox(51.5F, 4.0F, -14.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
        hand2.setTextureOffset(0, 122).addBox(52.5F, -3.5F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand2.setTextureOffset(0, 16).addBox(52.5F, 0.25F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand2.setTextureOffset(0, 0).addBox(52.5F, 4.0F, -14.0F, 2.0F, 3.0F, 13.0F, 0.0F, false);
        hand2.setTextureOffset(204, 161).addBox(51.5F, -1.0F, -2.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);
        hand2.setTextureOffset(17, 17).addBox(51.5F, 5.0F, -7.0F, 2.0F, 3.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
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
    public ResourceLocation getSusanooTexture(PlayerEntity player) {
        return TEMPLATE;
    }

    @Override
    public void manipulateItemToRender(MatrixStack matrixStackIn, ItemStack itemToRender, Hand handIn) {
        matrixStackIn.translate(0.0D, -2.4D, 0.0D);
        matrixStackIn.scale(3F, 3F, 3F);
        switch (handIn) {
            case MAIN_HAND:
                matrixStackIn.translate(-1.25D, 0.0D, 0.0D);
                matrixStackIn.rotate(new Quaternion(-45, -90, 0, true));
                break;
            case OFF_HAND:
                matrixStackIn.translate(1.25D, 0.0D, 0.0D);
                matrixStackIn.rotate(new Quaternion(-45, 90, 0, true));
                break;
        }
    }
}
