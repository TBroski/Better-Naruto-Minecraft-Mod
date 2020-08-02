package com.benarutomod.tbroski.client.renderer.layers.models.bodymode;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ModelCurseMarkWings<T extends LivingEntity> extends AgeableModel<T> implements IFaceBodyMode{

    private float swimAnimation;

    private final ModelRenderer leftWing;
    private final ModelRenderer handConnector;
    private final ModelRenderer bottom;
    private final ModelRenderer top;
    private final ModelRenderer webbing;
    private final ModelRenderer littleWebbing;
    private final ModelRenderer curveWebbing;
    private final ModelRenderer indexWebbing;
    private final ModelRenderer curveWebbing4;
    private final ModelRenderer middleWebbing;
    private final ModelRenderer curveWebbing3;
    private final ModelRenderer ringWebbing;
    private final ModelRenderer curveWebbing2;
    private final ModelRenderer index;
    private final ModelRenderer curve;
    private final ModelRenderer ring;
    private final ModelRenderer curve3;
    private final ModelRenderer little;
    private final ModelRenderer curve4;
    private final ModelRenderer middle;
    private final ModelRenderer curve2;
    private final ModelRenderer thumb;
    private final ModelRenderer thumbExtension;
    private final ModelRenderer rightWing;
    private final ModelRenderer handConnector2;
    private final ModelRenderer bottom2;
    private final ModelRenderer top2;
    private final ModelRenderer webbing2;
    private final ModelRenderer littleWebbing2;
    private final ModelRenderer curveWebbing5;
    private final ModelRenderer indexWebbing2;
    private final ModelRenderer curveWebbing6;
    private final ModelRenderer middleWebbing2;
    private final ModelRenderer curveWebbing7;
    private final ModelRenderer ringWebbing2;
    private final ModelRenderer curveWebbing8;
    private final ModelRenderer index2;
    private final ModelRenderer curve5;
    private final ModelRenderer ring2;
    private final ModelRenderer curve6;
    private final ModelRenderer little2;
    private final ModelRenderer curve7;
    private final ModelRenderer middle2;
    private final ModelRenderer curve8;
    private final ModelRenderer thumb2;
    private final ModelRenderer thumbExtension2;
    private final ModelRenderer connection;
    private final ModelRenderer face;

    public ModelCurseMarkWings() {
        textureWidth = 64;
        textureHeight = 64;

        leftWing = new ModelRenderer(this);
        leftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(leftWing, 0.0F, 0.0F, 0.2618F);
        leftWing.setTextureOffset(22, 4).addBox(-8.0F, 3.0F, 2.0F, 9.0F, 3.0F, 1.0F, 0.0F, false);
        leftWing.setTextureOffset(22, 17).addBox(-8.5944F, 3.4208F, 2.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);
        leftWing.setTextureOffset(0, 11).addBox(-18.0F, -1.0F, 2.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
        leftWing.setTextureOffset(0, 22).addBox(-17.0F, 0.0F, 2.5F, 9.0F, 8.0F, 1.0F, 0.0F, false);

        handConnector = new ModelRenderer(this);
        handConnector.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftWing.addChild(handConnector);


        bottom = new ModelRenderer(this);
        bottom.setRotationPoint(-7.4602F, 7.5249F, 2.5F);
        handConnector.addChild(bottom);
        setRotationAngle(bottom, 0.0F, 0.0F, 0.6109F);
        bottom.setTextureOffset(0, 42).addBox(-2.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, 0.0F, false);

        top = new ModelRenderer(this);
        top.setRotationPoint(-9.6255F, 1.3758F, 2.5F);
        handConnector.addChild(top);
        setRotationAngle(top, 0.0F, 0.0F, 2.7053F);
        top.setTextureOffset(41, 19).addBox(-2.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, 0.0F, false);

        webbing = new ModelRenderer(this);
        webbing.setRotationPoint(-21.1312F, 8.3226F, 1.5F);
        leftWing.addChild(webbing);
        setRotationAngle(webbing, -0.0873F, -0.4363F, 0.0F);


        littleWebbing = new ModelRenderer(this);
        littleWebbing.setRotationPoint(0.2113F, -0.0395F, -0.0486F);
        webbing.addChild(littleWebbing);
        setRotationAngle(littleWebbing, -0.3491F, 0.0F, 0.0F);
        littleWebbing.setTextureOffset(16, 46).addBox(-2.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing = new ModelRenderer(this);
        curveWebbing.setRotationPoint(2.1263F, -0.6861F, -0.4381F);
        littleWebbing.addChild(curveWebbing);
        setRotationAngle(curveWebbing, 0.0F, 0.4363F, -0.2618F);
        curveWebbing.setTextureOffset(48, 35).addBox(-0.9918F, -0.5833F, 0.0351F, 3.0F, 2.0F, 0.0F, 0.0F, false);

        indexWebbing = new ModelRenderer(this);
        indexWebbing.setRotationPoint(-0.8765F, -9.0354F, 0.0612F);
        webbing.addChild(indexWebbing);
        indexWebbing.setTextureOffset(24, 43).addBox(-0.5F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing4 = new ModelRenderer(this);
        curveWebbing4.setRotationPoint(2.0447F, -0.6861F, -0.4082F);
        indexWebbing.addChild(curveWebbing4);
        setRotationAngle(curveWebbing4, 0.0F, 0.3491F, -0.0873F);
        curveWebbing4.setTextureOffset(48, 18).addBox(-0.9918F, -0.5833F, 0.0351F, 4.0F, 2.0F, 0.0F, 0.0F, false);

        middleWebbing = new ModelRenderer(this);
        middleWebbing.setRotationPoint(-1.3134F, -6.3561F, 0.1125F);
        webbing.addChild(middleWebbing);
        setRotationAngle(middleWebbing, 0.3491F, 0.0F, 0.0F);
        middleWebbing.setTextureOffset(8, 43).addBox(-2.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing3 = new ModelRenderer(this);
        curveWebbing3.setRotationPoint(2.0012F, -0.8279F, -0.2861F);
        middleWebbing.addChild(curveWebbing3);
        setRotationAngle(curveWebbing3, -0.0873F, 0.4363F, -0.1745F);
        curveWebbing3.setTextureOffset(32, 30).addBox(-0.9918F, 0.4167F, 0.0351F, 4.0F, 1.0F, 0.0F, 0.0F, false);

        ringWebbing = new ModelRenderer(this);
        ringWebbing.setRotationPoint(-1.3678F, -2.7326F, 0.455F);
        webbing.addChild(ringWebbing);
        setRotationAngle(ringWebbing, -0.0873F, 0.0F, 0.0F);
        ringWebbing.setTextureOffset(8, 46).addBox(-2.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing2 = new ModelRenderer(this);
        curveWebbing2.setRotationPoint(2.1263F, -0.6861F, -0.4381F);
        ringWebbing.addChild(curveWebbing2);
        setRotationAngle(curveWebbing2, 0.0F, 0.4363F, -0.2618F);
        curveWebbing2.setTextureOffset(16, 43).addBox(-0.9918F, -0.5833F, 0.0351F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        index = new ModelRenderer(this);
        index.setRotationPoint(0.0F, 4.0F, 2.0F);
        leftWing.addChild(index);
        index.setTextureOffset(47, 38).addBox(-21.0F, -4.3F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve = new ModelRenderer(this);
        curve.setRotationPoint(-20.5617F, -3.2915F, 0.755F);
        index.addChild(curve);
        setRotationAngle(curve, 0.0F, -0.5236F, 0.0F);
        curve.setTextureOffset(0, 39).addBox(-6.4383F, -1.0085F, -0.755F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve.setTextureOffset(14, 36).addBox(-7.5617F, -0.4915F, 0.255F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        ring = new ModelRenderer(this);
        ring.setRotationPoint(0.0393F, 9.9423F, 2.0F);
        leftWing.addChild(ring);
        ring.setTextureOffset(35, 47).addBox(-21.0F, -4.3F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(-20.5617F, -3.2915F, 0.755F);
        ring.addChild(curve3);
        setRotationAngle(curve3, 0.0F, -0.4363F, 0.0F);
        curve3.setTextureOffset(13, 37).addBox(-6.4383F, -1.0085F, -0.755F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve3.setTextureOffset(8, 34).addBox(-7.5617F, -0.4915F, 0.255F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        little = new ModelRenderer(this);
        little.setRotationPoint(-14.4443F, 7.9936F, 1.25F);
        leftWing.addChild(little);
        setRotationAngle(little, 0.0F, 0.0F, -0.2618F);
        little.setTextureOffset(46, 32).addBox(-3.5839F, -1.0665F, 0.75F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(-2.4038F, 0.7786F, 1.505F);
        little.addChild(curve4);
        setRotationAngle(curve4, 0.0F, -0.4363F, 0.0F);
        curve4.setTextureOffset(26, 35).addBox(-7.1106F, -1.845F, -0.4415F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve4.setTextureOffset(4, 34).addBox(-8.234F, -1.328F, 0.5685F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        middle = new ModelRenderer(this);
        middle.setRotationPoint(-0.0127F, 6.8504F, 2.0F);
        leftWing.addChild(middle);
        middle.setTextureOffset(43, 47).addBox(-21.0F, -4.3F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(-20.5617F, -3.2915F, 0.755F);
        middle.addChild(curve2);
        setRotationAngle(curve2, 0.0F, -0.3491F, 0.0F);
        curve2.setTextureOffset(27, 38).addBox(-6.4383F, -1.0085F, -0.755F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve2.setTextureOffset(27, 34).addBox(-7.5617F, -0.4915F, 0.255F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        thumb = new ModelRenderer(this);
        thumb.setRotationPoint(-9.2412F, -0.5341F, 2.25F);
        leftWing.addChild(thumb);
        thumb.setTextureOffset(14, 40).addBox(-14.3065F, -2.578F, -0.5F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        thumb.setTextureOffset(12, 31).addBox(-16.3368F, -2.5959F, -0.5F, 3.0F, 0.0F, 1.0F, 0.0F, false);

        thumbExtension = new ModelRenderer(this);
        thumbExtension.setRotationPoint(0.0F, 0.0F, 0.0F);
        thumb.addChild(thumbExtension);
        setRotationAngle(thumbExtension, 0.0F, 0.0F, 0.2618F);
        thumbExtension.setTextureOffset(22, 14).addBox(-8.9503F, -0.2976F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);

        rightWing = new ModelRenderer(this);
        rightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(rightWing, 0.0F, 0.0F, -0.2618F);
        rightWing.setTextureOffset(22, 0).addBox(-1.0F, 3.0F, 2.0F, 9.0F, 3.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(22, 11).addBox(-0.4056F, 3.4208F, 2.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(0, 0).addBox(8.0F, -1.0F, 2.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
        rightWing.setTextureOffset(21, 21).addBox(8.0F, 0.0F, 2.5F, 9.0F, 8.0F, 1.0F, 0.0F, false);

        handConnector2 = new ModelRenderer(this);
        handConnector2.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightWing.addChild(handConnector2);


        bottom2 = new ModelRenderer(this);
        bottom2.setRotationPoint(7.4602F, 7.5249F, 2.5F);
        handConnector2.addChild(bottom2);
        setRotationAngle(bottom2, 0.0F, 0.0F, -0.6109F);
        bottom2.setTextureOffset(28, 41).addBox(-0.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, 0.0F, false);

        top2 = new ModelRenderer(this);
        top2.setRotationPoint(9.6255F, 1.3758F, 2.5F);
        handConnector2.addChild(top2);
        setRotationAngle(top2, 0.0F, 0.0F, -2.7053F);
        top2.setTextureOffset(40, 40).addBox(-0.5F, -4.5F, -0.5F, 3.0F, 6.0F, 1.0F, 0.0F, false);

        webbing2 = new ModelRenderer(this);
        webbing2.setRotationPoint(21.1312F, 8.3226F, 1.5F);
        rightWing.addChild(webbing2);
        setRotationAngle(webbing2, -0.0873F, 0.4363F, 0.0F);


        littleWebbing2 = new ModelRenderer(this);
        littleWebbing2.setRotationPoint(-0.2113F, -0.0395F, -0.0486F);
        webbing2.addChild(littleWebbing2);
        setRotationAngle(littleWebbing2, -0.3491F, 0.0F, 0.0F);
        littleWebbing2.setTextureOffset(42, 15).addBox(-1.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing5 = new ModelRenderer(this);
        curveWebbing5.setRotationPoint(-2.1263F, -0.6861F, -0.4381F);
        littleWebbing2.addChild(curveWebbing5);
        setRotationAngle(curveWebbing5, 0.0F, -0.4363F, 0.2618F);
        curveWebbing5.setTextureOffset(48, 29).addBox(-2.0082F, -0.5833F, 0.0351F, 3.0F, 2.0F, 0.0F, 0.0F, false);

        indexWebbing2 = new ModelRenderer(this);
        indexWebbing2.setRotationPoint(0.8765F, -9.0354F, 0.0612F);
        webbing2.addChild(indexWebbing2);
        indexWebbing2.setTextureOffset(36, 41).addBox(-1.5F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing6 = new ModelRenderer(this);
        curveWebbing6.setRotationPoint(-2.0447F, -0.6861F, -0.4082F);
        indexWebbing2.addChild(curveWebbing6);
        setRotationAngle(curveWebbing6, 0.0F, -0.3491F, 0.0873F);
        curveWebbing6.setTextureOffset(24, 48).addBox(-3.0082F, -0.5833F, 0.0351F, 4.0F, 2.0F, 0.0F, 0.0F, false);

        middleWebbing2 = new ModelRenderer(this);
        middleWebbing2.setRotationPoint(1.3134F, -6.3561F, 0.1125F);
        webbing2.addChild(middleWebbing2);
        setRotationAngle(middleWebbing2, 0.3491F, 0.0F, 0.0F);
        middleWebbing2.setTextureOffset(42, 12).addBox(-1.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing7 = new ModelRenderer(this);
        curveWebbing7.setRotationPoint(-2.0012F, -0.8279F, -0.2861F);
        middleWebbing2.addChild(curveWebbing7);
        setRotationAngle(curveWebbing7, -0.0873F, -0.4363F, 0.1745F);
        curveWebbing7.setTextureOffset(28, 20).addBox(-3.0082F, 0.4167F, 0.0351F, 4.0F, 1.0F, 0.0F, 0.0F, false);

        ringWebbing2 = new ModelRenderer(this);
        ringWebbing2.setRotationPoint(1.3678F, -2.7326F, 0.455F);
        webbing2.addChild(ringWebbing2);
        setRotationAngle(ringWebbing2, -0.0873F, 0.0F, 0.0F);
        ringWebbing2.setTextureOffset(42, 9).addBox(-1.5F, -1.5F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        curveWebbing8 = new ModelRenderer(this);
        curveWebbing8.setRotationPoint(-2.1263F, -0.6861F, -0.4381F);
        ringWebbing2.addChild(curveWebbing8);
        setRotationAngle(curveWebbing8, 0.0F, -0.4363F, 0.2618F);
        curveWebbing8.setTextureOffset(42, 6).addBox(-3.0082F, -0.5833F, 0.0351F, 4.0F, 3.0F, 0.0F, 0.0F, false);

        index2 = new ModelRenderer(this);
        index2.setRotationPoint(0.0F, 4.0F, 2.0F);
        rightWing.addChild(index2);
        index2.setTextureOffset(42, 3).addBox(18.0F, -4.3F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve5 = new ModelRenderer(this);
        curve5.setRotationPoint(20.5617F, -3.2915F, 0.755F);
        index2.addChild(curve5);
        setRotationAngle(curve5, 0.0F, 0.5236F, 0.0F);
        curve5.setTextureOffset(0, 35).addBox(0.4383F, -1.0085F, -0.755F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve5.setTextureOffset(0, 34).addBox(5.5617F, -0.4915F, 0.255F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        ring2 = new ModelRenderer(this);
        ring2.setRotationPoint(-0.0393F, 9.9423F, 2.0F);
        rightWing.addChild(ring2);
        ring2.setTextureOffset(42, 0).addBox(18.0F, -4.3F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve6 = new ModelRenderer(this);
        curve6.setRotationPoint(20.5617F, -3.2915F, 0.755F);
        ring2.addChild(curve6);
        setRotationAngle(curve6, 0.0F, 0.4363F, 0.0F);
        curve6.setTextureOffset(13, 33).addBox(0.4383F, -1.0085F, -0.755F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve6.setTextureOffset(33, 31).addBox(5.5617F, -0.4915F, 0.255F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        little2 = new ModelRenderer(this);
        little2.setRotationPoint(14.4443F, 7.9936F, 1.25F);
        rightWing.addChild(little2);
        setRotationAngle(little2, 0.0F, 0.0F, 0.2618F);
        little2.setTextureOffset(41, 26).addBox(0.5839F, -1.0665F, 0.75F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve7 = new ModelRenderer(this);
        curve7.setRotationPoint(2.4038F, 0.7786F, 1.505F);
        little2.addChild(curve7);
        setRotationAngle(curve7, 0.0F, 0.4363F, 0.0F);
        curve7.setTextureOffset(32, 32).addBox(1.1106F, -1.845F, -0.4415F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve7.setTextureOffset(26, 33).addBox(6.234F, -1.328F, 0.5685F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        middle2 = new ModelRenderer(this);
        middle2.setRotationPoint(0.0127F, 6.8504F, 2.0F);
        rightWing.addChild(middle2);
        middle2.setTextureOffset(40, 29).addBox(18.0F, -4.3F, 0.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        curve8 = new ModelRenderer(this);
        curve8.setRotationPoint(20.5617F, -3.2915F, 0.755F);
        middle2.addChild(curve8);
        setRotationAngle(curve8, 0.0F, 0.3491F, 0.0F);
        curve8.setTextureOffset(0, 31).addBox(0.4383F, -1.0085F, -0.755F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        curve8.setTextureOffset(14, 32).addBox(5.5617F, -0.4915F, 0.255F, 2.0F, 1.0F, 0.0F, 0.0F, false);

        thumb2 = new ModelRenderer(this);
        thumb2.setRotationPoint(9.2412F, -0.5341F, 2.25F);
        rightWing.addChild(thumb2);
        thumb2.setTextureOffset(19, 30).addBox(8.3065F, -2.578F, -0.5F, 6.0F, 2.0F, 1.0F, 0.0F, false);
        thumb2.setTextureOffset(21, 20).addBox(13.3368F, -2.5959F, -0.5F, 3.0F, 0.0F, 1.0F, 0.0F, false);

        thumbExtension2 = new ModelRenderer(this);
        thumbExtension2.setRotationPoint(0.0F, 0.0F, 0.0F);
        thumb2.addChild(thumbExtension2);
        setRotationAngle(thumbExtension2, 0.0F, 0.0F, -0.2618F);
        thumbExtension2.setTextureOffset(22, 8).addBox(-0.0497F, -0.2976F, -0.5F, 9.0F, 2.0F, 1.0F, 0.0F, false);

        connection = new ModelRenderer(this);
        connection.setRotationPoint(0.0F, 0.0F, 0.0F);
        connection.setTextureOffset(40, 35).addBox(-1.5F, 3.25F, 2.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);

        face = new ModelRenderer(this);
        face.setRotationPoint(0.0F, 0.0F, 0.0F);
        face.setTextureOffset(60, 61).addBox(-0.5F, -1.0F, -4.1F, 1.0F, -4.0F, 1.0F, 0.0F, false);
        face.setTextureOffset(58, 62).addBox(-1.0F, -0.75F, -4.1F, 2.0F, -5.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
        this.face.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

        this.face.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        if (entity instanceof PlayerEntity) {
            if (entity.isCrouching()) {
                this.face.rotationPointY = 4.2F;
            } else {
                this.face.rotationPointY = 0.0F;
            }
        }

        boolean flag = entity.getTicksElytraFlying() > 4;
        boolean flag1 = entity.isActualySwimming();
        if (flag) {
            this.face.rotateAngleX = (-(float) Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                this.face.rotateAngleX = this.rotLerpRad(this.face.rotateAngleX, (-(float) Math.PI / 4F), this.swimAnimation);
            } else {
                this.face.rotateAngleX = this.rotLerpRad(this.face.rotateAngleX, headPitch * ((float) Math.PI / 180F), this.swimAnimation);
            }
        } else {
            this.face.rotateAngleX = headPitch * ((float) Math.PI / 180F);
        }

        if (entity.isCrouching()) {
            this.leftWing.rotateAngleX = 0.5F;
            this.leftWing.rotationPointY = 3.2F;
            this.rightWing.rotateAngleX = 0.5F;
            this.rightWing.rotationPointY = 3.2F;
            this.connection.rotateAngleX = 0.5F;
            this.connection.rotationPointY = 3.2F;
        } else {
            this.leftWing.rotateAngleX = 0.0F;
            this.leftWing.rotationPointY = 0.0F;
            this.rightWing.rotateAngleX = 0.0F;
            this.rightWing.rotationPointY = 0.0F;
            this.connection.rotateAngleX = 0.0F;
            this.connection.rotationPointY = 0.0F;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
        connection.render(matrixStack, buffer, packedLight, packedOverlay);
        face.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.leftWing, this.rightWing, this.connection);
    }

    @Override
    public void setPartialTick(LivingEntity entityIn, float partialTick) {
        this.swimAnimation = entityIn.getSwimAnimation(partialTick);
    }
}
