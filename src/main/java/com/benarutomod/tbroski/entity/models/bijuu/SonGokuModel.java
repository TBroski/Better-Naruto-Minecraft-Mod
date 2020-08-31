package com.benarutomod.tbroski.entity.models.bijuu;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

public class SonGokuModel<T extends LivingEntity> extends AgeableModel<T> implements IHasArm, IHasHead {

    private final ModelRenderer body;
    private final ModelRenderer tail1;
    private final ModelRenderer curve3;
    private final ModelRenderer curve4;
    private final ModelRenderer curve5;
    private final ModelRenderer curve6;
    private final ModelRenderer curve7;
    private final ModelRenderer tail2;
    private final ModelRenderer curve8;
    private final ModelRenderer curve9;
    private final ModelRenderer curve10;
    private final ModelRenderer curve11;
    private final ModelRenderer curve12;
    private final ModelRenderer tail3;
    private final ModelRenderer curve13;
    private final ModelRenderer curve14;
    private final ModelRenderer curve15;
    private final ModelRenderer curve16;
    private final ModelRenderer curve17;
    private final ModelRenderer tail4;
    private final ModelRenderer curve18;
    private final ModelRenderer curve19;
    private final ModelRenderer curve20;
    private final ModelRenderer curve21;
    private final ModelRenderer curve22;
    private final ModelRenderer head;
    private final ModelRenderer lCrown;
    private final ModelRenderer rCrown;
    private final ModelRenderer tCrown;
    private final ModelRenderer tooth1;
    private final ModelRenderer curve;
    private final ModelRenderer tooth2;
    private final ModelRenderer curve2;
    private final ModelRenderer beard;
    private final ModelRenderer triangle;
    private final ModelRenderer triangle2;
    private final ModelRenderer leftLeg;
    private final ModelRenderer knee;
    private final ModelRenderer foot;
    private final ModelRenderer leftArm;
    private final ModelRenderer leftElbow;
    private final ModelRenderer leftHand;
    private final ModelRenderer rightLeg;
    private final ModelRenderer knee2;
    private final ModelRenderer foot2;
    private final ModelRenderer rightArm;
    private final ModelRenderer rightElbow;
    private final ModelRenderer rightHand;

    public SonGokuModel() {
        textureWidth = 256;
        textureHeight = 256;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 8.0F, 12.0F);
        setRotationAngle(body, 0.6109F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-14.0F, -38.0F, -11.0F, 28.0F, 18.0F, 19.0F, 0.0F, false);
        body.setTextureOffset(0, 37).addBox(-12.0F, -20.0F, -8.0F, 24.0F, 19.0F, 15.0F, 0.0F, false);

        tail1 = new ModelRenderer(this);
        tail1.setRotationPoint(7.0F, -1.0F, 3.0F);
        body.addChild(tail1);
        setRotationAngle(tail1, 0.3491F, 0.0F, 0.0F);
        tail1.setTextureOffset(0, 149).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(0.0F, 4.0F, -1.0F);
        tail1.addChild(curve3);
        setRotationAngle(curve3, 0.5236F, 0.0873F, 0.0F);
        curve3.setTextureOffset(148, 72).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(0.0F, 4.0F, 0.0F);
        curve3.addChild(curve4);
        setRotationAngle(curve4, 0.8727F, 0.0F, 0.0F);
        curve4.setTextureOffset(145, 104).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve5 = new ModelRenderer(this);
        curve5.setRotationPoint(0.0F, 5.0F, 0.0F);
        curve4.addChild(curve5);
        setRotationAngle(curve5, 0.7854F, 0.0F, -0.0873F);
        curve5.setTextureOffset(131, 40).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 12.0F, 5.0F, 0.0F, false);
        curve5.setTextureOffset(142, 129).addBox(-1.0F, 4.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve5.setTextureOffset(141, 35).addBox(-1.0F, 8.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve6 = new ModelRenderer(this);
        curve6.setRotationPoint(0.0F, 11.0F, 1.0F);
        curve5.addChild(curve6);
        setRotationAngle(curve6, -0.1745F, 0.0F, 0.0F);
        curve6.setTextureOffset(25, 102).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 14.0F, 5.0F, 0.0F, false);
        curve6.setTextureOffset(141, 27).addBox(-1.0F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve6.setTextureOffset(135, 137).addBox(-1.0F, 5.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve6.setTextureOffset(117, 137).addBox(-1.0F, 9.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve7 = new ModelRenderer(this);
        curve7.setRotationPoint(0.0F, 14.0F, 4.0F);
        curve6.addChild(curve7);
        setRotationAngle(curve7, -0.1745F, 0.0F, 0.0F);
        curve7.setTextureOffset(139, 139).addBox(-2.0F, 0.0F, -4.0F, 5.0F, 14.0F, 4.0F, 0.0F, false);
        curve7.setTextureOffset(136, 109).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve7.setTextureOffset(134, 129).addBox(-1.0F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve7.setTextureOffset(133, 35).addBox(-1.0F, 8.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(1.0F, -0.7544F, 1.6073F);
        body.addChild(tail2);
        setRotationAngle(tail2, 0.3491F, 0.0F, 0.0F);
        tail2.setTextureOffset(143, 87).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve8 = new ModelRenderer(this);
        curve8.setRotationPoint(0.0F, 4.0F, -1.0F);
        tail2.addChild(curve8);
        setRotationAngle(curve8, 0.5236F, 0.0F, 0.0F);
        curve8.setTextureOffset(147, 63).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        curve9 = new ModelRenderer(this);
        curve9.setRotationPoint(0.0F, 4.0F, 0.0F);
        curve8.addChild(curve9);
        setRotationAngle(curve9, 0.8727F, 0.0F, 0.0F);
        curve9.setTextureOffset(141, 17).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve10 = new ModelRenderer(this);
        curve10.setRotationPoint(0.0F, 5.0F, 0.0F);
        curve9.addChild(curve10);
        setRotationAngle(curve10, 0.8727F, 0.0F, 0.0F);
        curve10.setTextureOffset(128, 92).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 12.0F, 5.0F, 0.0F, false);
        curve10.setTextureOffset(103, 129).addBox(-1.0F, 4.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve10.setTextureOffset(128, 109).addBox(-1.0F, 8.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve11 = new ModelRenderer(this);
        curve11.setRotationPoint(0.0F, 11.0F, 1.0F);
        curve10.addChild(curve11);
        setRotationAngle(curve11, -0.2618F, 0.0F, 0.0F);
        curve11.setTextureOffset(0, 102).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 14.0F, 5.0F, 0.0F, false);
        curve11.setTextureOffset(122, 92).addBox(-1.0F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve11.setTextureOffset(107, 119).addBox(-1.0F, 5.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve11.setTextureOffset(117, 28).addBox(-1.0F, 9.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve12 = new ModelRenderer(this);
        curve12.setRotationPoint(0.0F, 14.0F, 4.0F);
        curve11.addChild(curve12);
        setRotationAngle(curve12, -0.1745F, 0.0F, 0.0F);
        curve12.setTextureOffset(121, 137).addBox(-2.0F, 0.0F, -4.0F, 5.0F, 14.0F, 4.0F, 0.0F, false);
        curve12.setTextureOffset(114, 72).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve12.setTextureOffset(114, 68).addBox(-1.0F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve12.setTextureOffset(114, 62).addBox(-1.0F, 8.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        tail3 = new ModelRenderer(this);
        tail3.setRotationPoint(-5.0F, -1.0F, 3.0F);
        body.addChild(tail3);
        setRotationAngle(tail3, 0.3491F, 0.0F, 0.0F);
        tail3.setTextureOffset(134, 119).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve13 = new ModelRenderer(this);
        curve13.setRotationPoint(0.0F, 4.0F, -1.0F);
        tail3.addChild(curve13);
        setRotationAngle(curve13, 0.3491F, 0.0F, 0.0F);
        curve13.setTextureOffset(146, 35).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        curve14 = new ModelRenderer(this);
        curve14.setRotationPoint(0.0F, 4.0F, 0.0F);
        curve13.addChild(curve14);
        setRotationAngle(curve14, 0.8727F, -0.0873F, 0.0F);
        curve14.setTextureOffset(92, 119).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve15 = new ModelRenderer(this);
        curve15.setRotationPoint(0.0F, 5.0F, 0.0F);
        curve14.addChild(curve15);
        setRotationAngle(curve15, 0.9599F, 0.0F, 0.0F);
        curve15.setTextureOffset(106, 45).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 12.0F, 5.0F, 0.0F, false);
        curve15.setTextureOffset(110, 25).addBox(-1.0F, 4.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve15.setTextureOffset(110, 21).addBox(-1.0F, 8.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve16 = new ModelRenderer(this);
        curve16.setRotationPoint(0.0F, 11.0F, 1.0F);
        curve15.addChild(curve16);
        setRotationAngle(curve16, -0.3491F, 0.0F, 0.0F);
        curve16.setTextureOffset(39, 71).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 14.0F, 5.0F, 0.0F, false);
        curve16.setTextureOffset(110, 17).addBox(-1.0F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve16.setTextureOffset(98, 109).addBox(-1.0F, 5.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve16.setTextureOffset(53, 109).addBox(-1.0F, 9.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve17 = new ModelRenderer(this);
        curve17.setRotationPoint(0.0F, 14.0F, 4.0F);
        curve16.addChild(curve17);
        setRotationAngle(curve17, -0.3491F, 0.0F, 0.0F);
        curve17.setTextureOffset(103, 137).addBox(-2.0F, 0.0F, -4.0F, 5.0F, 14.0F, 4.0F, 0.0F, false);
        curve17.setTextureOffset(45, 109).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve17.setTextureOffset(106, 72).addBox(-1.0F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve17.setTextureOffset(106, 68).addBox(-1.0F, 8.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        tail4 = new ModelRenderer(this);
        tail4.setRotationPoint(-10.0F, -0.1808F, 2.4264F);
        body.addChild(tail4);
        setRotationAngle(tail4, 0.3491F, 0.0F, 0.0F);
        tail4.setTextureOffset(58, 119).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve18 = new ModelRenderer(this);
        curve18.setRotationPoint(0.0F, 4.0F, -1.0F);
        tail4.addChild(curve18);
        setRotationAngle(curve18, 0.3491F, -0.1745F, 0.0F);
        curve18.setTextureOffset(146, 0).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        curve19 = new ModelRenderer(this);
        curve19.setRotationPoint(0.0F, 4.0F, 0.0F);
        curve18.addChild(curve19);
        setRotationAngle(curve19, 0.8727F, -0.0873F, 0.0F);
        curve19.setTextureOffset(103, 0).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        curve20 = new ModelRenderer(this);
        curve20.setRotationPoint(0.0F, 5.0F, 0.0F);
        curve19.addChild(curve20);
        setRotationAngle(curve20, 1.0472F, 0.0F, 0.0F);
        curve20.setTextureOffset(78, 96).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 12.0F, 5.0F, 0.0F, false);
        curve20.setTextureOffset(106, 62).addBox(-1.0F, 4.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve20.setTextureOffset(98, 105).addBox(-1.0F, 8.0F, 5.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve21 = new ModelRenderer(this);
        curve21.setRotationPoint(0.0F, 11.0F, 1.0F);
        curve20.addChild(curve21);
        setRotationAngle(curve21, -0.2618F, 0.0F, 0.0F);
        curve21.setTextureOffset(0, 71).addBox(-2.0F, 0.0F, -1.0F, 5.0F, 14.0F, 5.0F, 0.0F, false);
        curve21.setTextureOffset(53, 105).addBox(-1.0F, 1.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve21.setTextureOffset(45, 105).addBox(-1.0F, 5.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve21.setTextureOffset(98, 101).addBox(-1.0F, 9.0F, 4.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        curve22 = new ModelRenderer(this);
        curve22.setRotationPoint(0.0F, 14.0F, 4.0F);
        curve21.addChild(curve22);
        setRotationAngle(curve22, -0.2618F, 0.0F, 0.0F);
        curve22.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -4.0F, 5.0F, 14.0F, 4.0F, 0.0F, false);
        curve22.setTextureOffset(100, 45).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve22.setTextureOffset(97, 0).addBox(-1.0F, 4.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        curve22.setTextureOffset(15, 71).addBox(-1.0F, 8.0F, 0.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -19.0F, -5.0F);
        head.setTextureOffset(62, 62).addBox(-7.0F, -14.0F, -16.0F, 14.0F, 18.0F, 16.0F, 0.0F, false);
        head.setTextureOffset(110, 0).addBox(-5.5F, 4.0F, -14.0F, 11.0F, 3.0F, 14.0F, 0.0F, false);
        head.setTextureOffset(78, 55).addBox(-5.0F, -4.0F, -20.0F, 10.0F, 2.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(0, 90).addBox(-4.0F, -5.0F, -19.0F, 8.0F, 1.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(67, 50).addBox(-3.2F, -2.0F, -19.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(11, 41).addBox(2.2F, -2.0F, -19.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(66, 43).addBox(-1.9F, -2.0F, -19.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(63, 42).addBox(0.9F, -2.0F, -19.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(63, 50).addBox(-0.5F, -2.0F, -19.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(19, 125).addBox(-5.0F, 1.0F, -18.0F, 10.0F, 1.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(75, 4).addBox(-4.9F, 0.0F, -17.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 71).addBox(3.9F, 0.0F, -17.2F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(75, 2).addBox(-3.8F, 0.0F, -17.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(39, 71).addBox(2.8F, 0.0F, -17.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(75, 0).addBox(-2.5F, 0.0F, -17.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(71, 50).addBox(1.5F, 0.0F, -17.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(39, 73).addBox(-1.2F, 0.0F, -17.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 73).addBox(0.2F, 0.0F, -17.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        lCrown = new ModelRenderer(this);
        lCrown.setRotationPoint(0.0F, -11.0F, -16.0F);
        head.addChild(lCrown);
        setRotationAngle(lCrown, 0.0F, 0.0F, 0.3491F);
        lCrown.setTextureOffset(101, 10).addBox(-8.1278F, 0.1107F, -2.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        rCrown = new ModelRenderer(this);
        rCrown.setRotationPoint(0.0F, -11.0F, -16.0F);
        head.addChild(rCrown);
        setRotationAngle(rCrown, 0.0F, 0.0F, -0.3491F);
        rCrown.setTextureOffset(39, 90).addBox(-0.8722F, 0.1107F, -2.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);

        tCrown = new ModelRenderer(this);
        tCrown.setRotationPoint(0.0F, -12.2F, -16.0F);
        head.addChild(tCrown);
        setRotationAngle(tCrown, 0.1745F, 0.0F, 0.0F);
        tCrown.setTextureOffset(106, 45).addBox(7.0F, -1.75F, -2.0F, 2.0F, 2.0F, 21.0F, 0.0F, false);
        tCrown.setTextureOffset(0, 102).addBox(-9.0F, -1.75F, -2.0F, 2.0F, 2.0F, 21.0F, 0.0F, false);

        tooth1 = new ModelRenderer(this);
        tooth1.setRotationPoint(-4.0F, -2.0F, -19.0F);
        head.addChild(tooth1);
        setRotationAngle(tooth1, -0.2618F, 0.0873F, 0.0F);
        tooth1.setTextureOffset(14, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        curve = new ModelRenderer(this);
        curve.setRotationPoint(0.0F, 2.0F, -0.5F);
        tooth1.addChild(curve);
        setRotationAngle(curve, 0.7854F, 0.0F, 0.0F);
        curve.setTextureOffset(8, 49).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        tooth2 = new ModelRenderer(this);
        tooth2.setRotationPoint(4.0F, -2.0F, -19.0F);
        head.addChild(tooth2);
        setRotationAngle(tooth2, -0.2618F, -0.0873F, 0.0F);
        tooth2.setTextureOffset(0, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(0.0F, 2.0F, -0.5F);
        tooth2.addChild(curve2);
        setRotationAngle(curve2, 0.7854F, 0.0F, 0.0F);
        curve2.setTextureOffset(9, 37).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        beard = new ModelRenderer(this);
        beard.setRotationPoint(0.0F, 4.0F, -14.8F);
        head.addChild(beard);
        setRotationAngle(beard, 0.1745F, 0.0F, 0.0F);
        beard.setTextureOffset(131, 57).addBox(-5.5F, 0.0F, -1.0F, 11.0F, 3.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(75, 10).addBox(-6.0F, -1.25F, -1.0F, 12.0F, 3.0F, 1.0F, 0.0F, false);
        beard.setTextureOffset(54, 71).addBox(-4.5F, 3.0F, -1.0F, 9.0F, 2.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(40, 102).addBox(-4.0F, 4.5F, -1.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
        beard.setTextureOffset(63, 45).addBox(-3.5F, 5.0F, -1.0F, 7.0F, 2.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(93, 96).addBox(-2.5F, 7.0F, -1.0F, 5.0F, 2.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(75, 14).addBox(-2.75F, 6.0F, -1.0F, 6.0F, 2.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(0, 37).addBox(-1.75F, 9.0F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(63, 37).addBox(0.75F, 9.0F, -1.0F, 1.0F, 2.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(0, 46).addBox(-0.65F, 10.65F, -1.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(0, 42).addBox(-0.9F, 10.65F, -1.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        beard.setTextureOffset(7, 43).addBox(-0.35F, 10.65F, -1.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);

        triangle = new ModelRenderer(this);
        triangle.setRotationPoint(7.0F, 0.0F, -1.0F);
        beard.addChild(triangle);
        setRotationAngle(triangle, 0.0F, 0.0F, 0.4363F);
        triangle.setTextureOffset(23, 146).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 15.0F, 3.0F, 0.0F, false);

        triangle2 = new ModelRenderer(this);
        triangle2.setRotationPoint(-7.0F, 0.0F, -1.0F);
        beard.addChild(triangle2);
        setRotationAngle(triangle2, 0.0F, 0.0F, -0.4363F);
        triangle2.setTextureOffset(26, 128).addBox(0.0F, 0.0F, 0.0F, 1.0F, 15.0F, 3.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(11.0F, 5.5F, 8.5F);
        setRotationAngle(leftLeg, -1.2217F, -0.0873F, 0.0F);
        leftLeg.setTextureOffset(69, 119).addBox(-3.0F, 0.5F, -6.5F, 6.0F, 20.0F, 11.0F, 0.0F, false);

        knee = new ModelRenderer(this);
        knee.setRotationPoint(0.0F, 12.5F, -3.5F);
        leftLeg.addChild(knee);
        setRotationAngle(knee, 1.5708F, 0.0F, 0.0F);
        knee.setTextureOffset(0, 125).addBox(-3.0F, -2.2913F, -8.231F, 6.0F, 17.0F, 7.0F, 0.0F, false);

        foot = new ModelRenderer(this);
        foot.setRotationPoint(0.0F, 17.0F, 7.0F);
        knee.addChild(foot);
        setRotationAngle(foot, -0.3491F, 0.0F, 0.0F);
        foot.setTextureOffset(103, 119).addBox(-4.0F, 0.662F, -23.5183F, 8.0F, 3.0F, 15.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(14.0F, -16.0F, -8.0F);
        setRotationAngle(leftArm, 0.9599F, -0.1745F, 0.0F);
        leftArm.setTextureOffset(71, 14).addBox(0.0F, -4.0F, -18.0F, 8.0F, 8.0F, 23.0F, 0.0F, false);

        leftElbow = new ModelRenderer(this);
        leftElbow.setRotationPoint(8.0F, 0.0F, -18.0F);
        leftArm.addChild(leftElbow);
        setRotationAngle(leftElbow, 0.0873F, 0.6109F, 0.0F);
        leftElbow.setTextureOffset(95, 96).addBox(-8.0F, -3.0F, -17.0F, 8.0F, 6.0F, 17.0F, 0.0F, false);

        leftHand = new ModelRenderer(this);
        leftHand.setRotationPoint(-22.0F, 40.0F, 31.0F);
        leftElbow.addChild(leftHand);
        leftHand.setTextureOffset(78, 45).addBox(14.0F, -43.0F, -54.0F, 8.0F, 4.0F, 6.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(-11.0F, 5.5F, 9.5F);
        setRotationAngle(rightLeg, -1.2217F, 0.0873F, 0.0F);
        rightLeg.setTextureOffset(35, 119).addBox(-3.0F, 0.5F, -6.5F, 6.0F, 20.0F, 11.0F, 0.0F, false);

        knee2 = new ModelRenderer(this);
        knee2.setRotationPoint(0.0F, 12.5F, -3.5F);
        rightLeg.addChild(knee2);
        setRotationAngle(knee2, 1.5708F, 0.0F, 0.0F);
        knee2.setTextureOffset(122, 68).addBox(-3.0F, -2.2913F, -8.231F, 6.0F, 17.0F, 7.0F, 0.0F, false);

        foot2 = new ModelRenderer(this);
        foot2.setRotationPoint(0.0F, 17.0F, 7.0F);
        knee2.addChild(foot2);
        setRotationAngle(foot2, -0.3491F, 0.0F, 0.0F);
        foot2.setTextureOffset(110, 17).addBox(-4.0F, 0.662F, -23.5183F, 8.0F, 3.0F, 15.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-14.0F, -16.0F, -8.0F);
        setRotationAngle(rightArm, 0.9599F, 0.1745F, 0.0F);
        rightArm.setTextureOffset(0, 71).addBox(-8.0F, -4.0F, -18.0F, 8.0F, 8.0F, 23.0F, 0.0F, false);

        rightElbow = new ModelRenderer(this);
        rightElbow.setRotationPoint(-8.0F, 0.0F, -18.0F);
        rightArm.addChild(rightElbow);
        setRotationAngle(rightElbow, 0.0873F, -0.6109F, 0.0F);
        rightElbow.setTextureOffset(45, 96).addBox(0.0F, -3.0F, -17.0F, 8.0F, 6.0F, 17.0F, 0.0F, false);

        rightHand = new ModelRenderer(this);
        rightHand.setRotationPoint(22.0F, 40.0F, 31.0F);
        rightElbow.addChild(rightHand);
        rightHand.setTextureOffset(75, 0).addBox(-22.0F, -43.0F, -54.0F, 8.0F, 4.0F, 6.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.rightArm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F) + 1.0F;
        this.leftArm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F) + 1.0F;
        this.rightLeg.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount) - 1.2F;
        this.leftLeg.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount) - 1.2F;
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
        rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
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
