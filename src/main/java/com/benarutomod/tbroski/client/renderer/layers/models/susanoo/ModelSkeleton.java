package com.benarutomod.tbroski.client.renderer.layers.models.susanoo;

import com.benarutomod.tbroski.Main;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;

public class ModelSkeleton<T extends LivingEntity> extends ModelSusanoo<T> {

    private static final ResourceLocation TEMPLATE = new ResourceLocation(Main.MODID, "textures/entity/layer/susanoo/stage2/template.png");

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer nose;
    private final ModelRenderer horn;
    private final ModelRenderer horn2;
    private final ModelRenderer rib1;
    private final ModelRenderer curve2;
    private final ModelRenderer curve3;
    private final ModelRenderer curve4;
    private final ModelRenderer curve5;
    private final ModelRenderer curve6;
    private final ModelRenderer curve9;
    private final ModelRenderer curve10;
    private final ModelRenderer rib5;
    private final ModelRenderer curve32;
    private final ModelRenderer curve33;
    private final ModelRenderer curve34;
    private final ModelRenderer curve35;
    private final ModelRenderer curve36;
    private final ModelRenderer curve37;
    private final ModelRenderer curve38;
    private final ModelRenderer rib2;
    private final ModelRenderer curve11;
    private final ModelRenderer curve12;
    private final ModelRenderer curve13;
    private final ModelRenderer curve14;
    private final ModelRenderer curve15;
    private final ModelRenderer curve16;
    private final ModelRenderer curve17;
    private final ModelRenderer rib6;
    private final ModelRenderer curve39;
    private final ModelRenderer curve40;
    private final ModelRenderer curve41;
    private final ModelRenderer curve42;
    private final ModelRenderer curve43;
    private final ModelRenderer curve44;
    private final ModelRenderer curve45;
    private final ModelRenderer rib3;
    private final ModelRenderer curve18;
    private final ModelRenderer curve19;
    private final ModelRenderer curve20;
    private final ModelRenderer curve21;
    private final ModelRenderer curve22;
    private final ModelRenderer curve23;
    private final ModelRenderer curve24;
    private final ModelRenderer rib7;
    private final ModelRenderer curve46;
    private final ModelRenderer curve47;
    private final ModelRenderer curve48;
    private final ModelRenderer curve49;
    private final ModelRenderer curve50;
    private final ModelRenderer curve51;
    private final ModelRenderer curve52;
    private final ModelRenderer rib4;
    private final ModelRenderer curve25;
    private final ModelRenderer curve26;
    private final ModelRenderer curve27;
    private final ModelRenderer curve28;
    private final ModelRenderer curve29;
    private final ModelRenderer curve30;
    private final ModelRenderer curve31;
    private final ModelRenderer rib8;
    private final ModelRenderer curve53;
    private final ModelRenderer curve54;
    private final ModelRenderer curve55;
    private final ModelRenderer curve56;
    private final ModelRenderer curve57;
    private final ModelRenderer curve58;
    private final ModelRenderer curve59;
    private final ModelRenderer spine;
    private final ModelRenderer neck;
    private final ModelRenderer curve;
    private final ModelRenderer curve7;
    private final ModelRenderer curve8;
    private final ModelRenderer leftCollar;
    private final ModelRenderer turn;
    private final ModelRenderer rightCollar;
    private final ModelRenderer turn2;
    private final ModelRenderer leftArm;
    private final ModelRenderer bicep;
    private final ModelRenderer collar;
    private final ModelRenderer rightArm;
    private final ModelRenderer bicep2;
    private final ModelRenderer elbow;
    private final ModelRenderer collar2;

    public ModelSkeleton() {
        textureWidth = 512;
        textureHeight = 512;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 12.0F, 0.0F);


        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -14.0F, 16.0F);
        body.addChild(head);
        head.setTextureOffset(0, 0).addBox(-15.0F, -150.0F, -25.0F, 30.0F, 41.0F, 26.0F, 0.0F, false);
        head.setTextureOffset(174, 0).addBox(-15.0F, -150.0F, -25.5F, 30.0F, 28.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(104, 67).addBox(-15.0F, -122.0F, -25.5F, 2.0F, 13.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(16, 0).addBox(13.0F, -122.0F, -25.5F, 2.0F, 13.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 106).addBox(-13.0F, -115.0F, -25.5F, 26.0F, 6.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(90, 109).addBox(-12.6F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(93, 106).addBox(-12.6F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(84, 109).addBox(-10.5F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(87, 106).addBox(-10.5F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(78, 109).addBox(-8.4F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(81, 106).addBox(-8.4F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(56, 106).addBox(-6.3F, -122.0F, -25.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(75, 106).addBox(-6.3F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(72, 109).addBox(-4.2F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(92, 75).addBox(-4.2F, -122.0F, -25.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        head.setTextureOffset(86, 12).addBox(-2.1F, -122.75F, -25.25F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(69, 106).addBox(-2.1F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(86, 0).addBox(0.0F, -122.75F, -25.25F, 2.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(63, 106).addBox(0.0F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(66, 109).addBox(2.1F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(104, 99).addBox(2.1F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(106, 102).addBox(4.2F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(104, 96).addBox(4.2F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(106, 87).addBox(6.3F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(104, 84).addBox(6.3F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(106, 15).addBox(8.4F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(104, 81).addBox(8.4F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(106, 12).addBox(10.5F, -117.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(97, 67).addBox(10.5F, -122.0F, -25.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(254, 254).addBox(15.0F, -146.0F, -18.0F, 6.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(112, 143).addBox(-21.0F, -146.0F, -18.0F, 6.0F, 8.0F, 8.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setRotationPoint(0.0F, -125.0F, -26.0F);
        head.addChild(nose);
        setRotationAngle(nose, -0.2618F, 0.0F, 0.0F);
        nose.setTextureOffset(16, 16).addBox(-1.5F, -7.0F, -2.0F, 3.0F, 6.0F, 2.0F, 0.0F, false);

        horn = new ModelRenderer(this);
        horn.setRotationPoint(21.0F, -138.0F, -12.0F);
        head.addChild(horn);
        setRotationAngle(horn, 0.0F, 0.0F, -1.0472F);
        horn.setTextureOffset(174, 102).addBox(0.0F, -8.0F, -6.0F, 17.0F, 8.0F, 8.0F, 0.0F, false);

        horn2 = new ModelRenderer(this);
        horn2.setRotationPoint(-21.0F, -138.0F, -12.0F);
        head.addChild(horn2);
        setRotationAngle(horn2, 0.0F, 0.0F, 1.0472F);
        horn2.setTextureOffset(174, 29).addBox(-17.0F, -8.0F, -6.0F, 17.0F, 8.0F, 8.0F, 0.0F, false);

        rib1 = new ModelRenderer(this);
        rib1.setRotationPoint(-4.0F, -74.0F, 28.0F);
        body.addChild(rib1);
        rib1.setTextureOffset(0, 253).addBox(-14.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(-14.0F, 1.0F, 0.0F);
        rib1.addChild(curve2);
        setRotationAngle(curve2, 0.0F, -0.4363F, 0.0F);
        curve2.setTextureOffset(252, 148).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve2.addChild(curve3);
        setRotationAngle(curve3, 0.0F, -0.4363F, 0.0F);
        curve3.setTextureOffset(252, 134).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve3.addChild(curve4);
        setRotationAngle(curve4, 0.0F, -0.4363F, 0.0F);
        curve4.setTextureOffset(250, 92).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve5 = new ModelRenderer(this);
        curve5.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve4.addChild(curve5);
        setRotationAngle(curve5, 0.0F, -0.4363F, 0.0F);
        curve5.setTextureOffset(222, 250).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve6 = new ModelRenderer(this);
        curve6.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve5.addChild(curve6);
        setRotationAngle(curve6, 0.0F, -0.4363F, 0.0F);
        curve6.setTextureOffset(148, 249).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve9 = new ModelRenderer(this);
        curve9.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve6.addChild(curve9);
        setRotationAngle(curve9, 0.0F, -0.4363F, 0.0F);
        curve9.setTextureOffset(80, 249).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve10 = new ModelRenderer(this);
        curve10.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve9.addChild(curve10);
        setRotationAngle(curve10, 0.0F, -0.4363F, 0.0F);
        curve10.setTextureOffset(192, 155).addBox(-15.0F, -3.0F, -6.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

        rib5 = new ModelRenderer(this);
        rib5.setRotationPoint(4.0F, -74.0F, 28.0F);
        body.addChild(rib5);
        rib5.setTextureOffset(0, 229).addBox(0.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve32 = new ModelRenderer(this);
        curve32.setRotationPoint(14.0F, 1.0F, 0.0F);
        rib5.addChild(curve32);
        setRotationAngle(curve32, 0.0F, 0.4363F, 0.0F);
        curve32.setTextureOffset(204, 228).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve33 = new ModelRenderer(this);
        curve33.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve32.addChild(curve33);
        setRotationAngle(curve33, 0.0F, 0.4363F, 0.0F);
        curve33.setTextureOffset(226, 51).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve34 = new ModelRenderer(this);
        curve34.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve33.addChild(curve34);
        setRotationAngle(curve34, 0.0F, 0.4363F, 0.0F);
        curve34.setTextureOffset(164, 225).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve35 = new ModelRenderer(this);
        curve35.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve34.addChild(curve35);
        setRotationAngle(curve35, 0.0F, 0.4363F, 0.0F);
        curve35.setTextureOffset(80, 225).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve36 = new ModelRenderer(this);
        curve36.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve35.addChild(curve36);
        setRotationAngle(curve36, 0.0F, 0.4363F, 0.0F);
        curve36.setTextureOffset(40, 220).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve37 = new ModelRenderer(this);
        curve37.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve36.addChild(curve37);
        setRotationAngle(curve37, 0.0F, 0.4363F, 0.0F);
        curve37.setTextureOffset(130, 219).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve38 = new ModelRenderer(this);
        curve38.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve37.addChild(curve38);
        setRotationAngle(curve38, 0.0F, 0.4363F, 0.0F);
        curve38.setTextureOffset(176, 143).addBox(0.0F, -3.0F, -6.0F, 15.0F, 6.0F, 6.0F, 0.0F, false);

        rib2 = new ModelRenderer(this);
        rib2.setRotationPoint(-4.0F, -56.0F, 27.0F);
        body.addChild(rib2);
        rib2.setTextureOffset(246, 80).addBox(-14.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve11 = new ModelRenderer(this);
        curve11.setRotationPoint(-14.0F, 1.0F, 0.0F);
        rib2.addChild(curve11);
        setRotationAngle(curve11, 0.0F, -0.5236F, 0.0F);
        curve11.setTextureOffset(246, 63).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve12 = new ModelRenderer(this);
        curve12.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve11.addChild(curve12);
        setRotationAngle(curve12, 0.0F, -0.4363F, 0.0F);
        curve12.setTextureOffset(244, 122).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve13 = new ModelRenderer(this);
        curve13.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve12.addChild(curve13);
        setRotationAngle(curve13, 0.0F, -0.4363F, 0.0F);
        curve13.setTextureOffset(40, 244).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve14 = new ModelRenderer(this);
        curve14.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve13.addChild(curve14);
        setRotationAngle(curve14, 0.0F, -0.5236F, 0.0F);
        curve14.setTextureOffset(114, 243).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve15 = new ModelRenderer(this);
        curve15.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve14.addChild(curve15);
        setRotationAngle(curve15, 0.0F, -0.4363F, 0.0F);
        curve15.setTextureOffset(0, 241).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve16 = new ModelRenderer(this);
        curve16.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve15.addChild(curve16);
        setRotationAngle(curve16, 0.0F, -0.4363F, 0.0F);
        curve16.setTextureOffset(240, 185).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve17 = new ModelRenderer(this);
        curve17.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve16.addChild(curve17);
        setRotationAngle(curve17, 0.0F, -0.4363F, 0.0F);
        curve17.setTextureOffset(258, 35).addBox(-9.0F, -3.0F, -6.0F, 9.0F, 6.0F, 6.0F, 0.0F, false);

        rib6 = new ModelRenderer(this);
        rib6.setRotationPoint(4.0F, -56.0F, 27.0F);
        body.addChild(rib6);
        rib6.setTextureOffset(218, 142).addBox(0.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve39 = new ModelRenderer(this);
        curve39.setRotationPoint(14.0F, 1.0F, 0.0F);
        rib6.addChild(curve39);
        setRotationAngle(curve39, 0.0F, 0.5236F, 0.0F);
        curve39.setTextureOffset(218, 39).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve40 = new ModelRenderer(this);
        curve40.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve39.addChild(curve40);
        setRotationAngle(curve40, 0.0F, 0.4363F, 0.0F);
        curve40.setTextureOffset(0, 217).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve41 = new ModelRenderer(this);
        curve41.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve40.addChild(curve41);
        setRotationAngle(curve41, 0.0F, 0.4363F, 0.0F);
        curve41.setTextureOffset(216, 98).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve42 = new ModelRenderer(this);
        curve42.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve41.addChild(curve42);
        setRotationAngle(curve42, 0.0F, 0.5236F, 0.0F);
        curve42.setTextureOffset(204, 216).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve43 = new ModelRenderer(this);
        curve43.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve42.addChild(curve43);
        setRotationAngle(curve43, 0.0F, 0.4363F, 0.0F);
        curve43.setTextureOffset(164, 213).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve44 = new ModelRenderer(this);
        curve44.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve43.addChild(curve44);
        setRotationAngle(curve44, 0.0F, 0.4363F, 0.0F);
        curve44.setTextureOffset(96, 213).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve45 = new ModelRenderer(this);
        curve45.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve44.addChild(curve45);
        setRotationAngle(curve45, 0.0F, 0.4363F, 0.0F);
        curve45.setTextureOffset(40, 256).addBox(0.0F, -3.0F, -6.0F, 9.0F, 6.0F, 6.0F, 0.0F, false);

        rib3 = new ModelRenderer(this);
        rib3.setRotationPoint(-4.0F, -38.0F, 24.0F);
        body.addChild(rib3);
        rib3.setTextureOffset(240, 173).addBox(-14.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve18 = new ModelRenderer(this);
        curve18.setRotationPoint(-14.0F, 1.0F, 0.0F);
        rib3.addChild(curve18);
        setRotationAngle(curve18, 0.0F, -0.5236F, 0.0F);
        curve18.setTextureOffset(188, 240).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve19 = new ModelRenderer(this);
        curve19.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve18.addChild(curve19);
        setRotationAngle(curve19, 0.0F, -0.4363F, 0.0F);
        curve19.setTextureOffset(238, 222).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve20 = new ModelRenderer(this);
        curve20.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve19.addChild(curve20);
        setRotationAngle(curve20, 0.0F, -0.5236F, 0.0F);
        curve20.setTextureOffset(238, 210).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve21 = new ModelRenderer(this);
        curve21.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve20.addChild(curve21);
        setRotationAngle(curve21, 0.0F, -0.4363F, 0.0F);
        curve21.setTextureOffset(238, 238).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve22 = new ModelRenderer(this);
        curve22.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve21.addChild(curve22);
        setRotationAngle(curve22, 0.0F, -0.4363F, 0.0F);
        curve22.setTextureOffset(148, 237).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve23 = new ModelRenderer(this);
        curve23.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve22.addChild(curve23);
        setRotationAngle(curve23, 0.0F, -0.4363F, 0.0F);
        curve23.setTextureOffset(80, 237).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve24 = new ModelRenderer(this);
        curve24.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve23.addChild(curve24);
        setRotationAngle(curve24, 0.0F, -0.4363F, 0.0F);
        curve24.setTextureOffset(132, 261).addBox(-8.0F, -3.0F, -6.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);

        rib7 = new ModelRenderer(this);
        rib7.setRotationPoint(4.0F, -38.0F, 24.0F);
        body.addChild(rib7);
        rib7.setTextureOffset(212, 86).addBox(0.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve46 = new ModelRenderer(this);
        curve46.setRotationPoint(14.0F, 1.0F, 0.0F);
        rib7.addChild(curve46);
        setRotationAngle(curve46, 0.0F, 0.5236F, 0.0F);
        curve46.setTextureOffset(212, 74).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve47 = new ModelRenderer(this);
        curve47.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve46.addChild(curve47);
        setRotationAngle(curve47, 0.0F, 0.4363F, 0.0F);
        curve47.setTextureOffset(210, 130).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve48 = new ModelRenderer(this);
        curve48.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve47.addChild(curve48);
        setRotationAngle(curve48, 0.0F, 0.5236F, 0.0F);
        curve48.setTextureOffset(130, 207).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve49 = new ModelRenderer(this);
        curve49.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve48.addChild(curve49);
        setRotationAngle(curve49, 0.0F, 0.4363F, 0.0F);
        curve49.setTextureOffset(206, 179).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve50 = new ModelRenderer(this);
        curve50.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve49.addChild(curve50);
        setRotationAngle(curve50, 0.0F, 0.4363F, 0.0F);
        curve50.setTextureOffset(0, 205).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve51 = new ModelRenderer(this);
        curve51.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve50.addChild(curve51);
        setRotationAngle(curve51, 0.0F, 0.4363F, 0.0F);
        curve51.setTextureOffset(198, 204).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve52 = new ModelRenderer(this);
        curve52.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve51.addChild(curve52);
        setRotationAngle(curve52, 0.0F, 0.4363F, 0.0F);
        curve52.setTextureOffset(70, 261).addBox(0.0F, -3.0F, -6.0F, 8.0F, 6.0F, 6.0F, 0.0F, false);

        rib4 = new ModelRenderer(this);
        rib4.setRotationPoint(-4.0F, -20.0F, 23.0F);
        body.addChild(rib4);
        rib4.setTextureOffset(236, 0).addBox(-14.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve25 = new ModelRenderer(this);
        curve25.setRotationPoint(-14.0F, 1.0F, 0.0F);
        rib4.addChild(curve25);
        setRotationAngle(curve25, 0.0F, -0.5236F, 0.0F);
        curve25.setTextureOffset(234, 198).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve26 = new ModelRenderer(this);
        curve26.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve25.addChild(curve26);
        setRotationAngle(curve26, 0.0F, -0.4363F, 0.0F);
        curve26.setTextureOffset(232, 161).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve27 = new ModelRenderer(this);
        curve27.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve26.addChild(curve27);
        setRotationAngle(curve27, 0.0F, -0.5236F, 0.0F);
        curve27.setTextureOffset(232, 110).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve28 = new ModelRenderer(this);
        curve28.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve27.addChild(curve28);
        setRotationAngle(curve28, 0.0F, -0.4363F, 0.0F);
        curve28.setTextureOffset(40, 232).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve29 = new ModelRenderer(this);
        curve29.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve28.addChild(curve29);
        setRotationAngle(curve29, 0.0F, -0.5236F, 0.0F);
        curve29.setTextureOffset(114, 231).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve30 = new ModelRenderer(this);
        curve30.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve29.addChild(curve30);
        setRotationAngle(curve30, 0.0F, -0.4363F, 0.0F);
        curve30.setTextureOffset(230, 23).addBox(-14.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve31 = new ModelRenderer(this);
        curve31.setRotationPoint(-14.0F, 0.0F, 0.0F);
        curve30.addChild(curve31);
        setRotationAngle(curve31, 0.0F, -0.4363F, 0.0F);
        curve31.setTextureOffset(160, 261).addBox(-7.0F, -3.0F, -6.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

        rib8 = new ModelRenderer(this);
        rib8.setRotationPoint(4.0F, -20.0F, 23.0F);
        body.addChild(rib8);
        rib8.setTextureOffset(96, 201).addBox(0.0F, -2.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve53 = new ModelRenderer(this);
        curve53.setRotationPoint(14.0F, 1.0F, 0.0F);
        rib8.addChild(curve53);
        setRotationAngle(curve53, 0.0F, 0.5236F, 0.0F);
        curve53.setTextureOffset(200, 192).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve54 = new ModelRenderer(this);
        curve54.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve53.addChild(curve54);
        setRotationAngle(curve54, 0.0F, 0.4363F, 0.0F);
        curve54.setTextureOffset(198, 167).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve55 = new ModelRenderer(this);
        curve55.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve54.addChild(curve55);
        setRotationAngle(curve55, 0.0F, 0.5236F, 0.0F);
        curve55.setTextureOffset(198, 118).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve56 = new ModelRenderer(this);
        curve56.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve55.addChild(curve56);
        setRotationAngle(curve56, 0.0F, 0.4363F, 0.0F);
        curve56.setTextureOffset(164, 198).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve57 = new ModelRenderer(this);
        curve57.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve56.addChild(curve57);
        setRotationAngle(curve57, 0.0F, 0.5236F, 0.0F);
        curve57.setTextureOffset(0, 193).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve58 = new ModelRenderer(this);
        curve58.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve57.addChild(curve58);
        setRotationAngle(curve58, 0.0F, 0.4363F, 0.0F);
        curve58.setTextureOffset(86, 0).addBox(0.0F, -3.0F, -6.0F, 14.0F, 6.0F, 6.0F, 0.0F, false);

        curve59 = new ModelRenderer(this);
        curve59.setRotationPoint(14.0F, 0.0F, 0.0F);
        curve58.addChild(curve59);
        setRotationAngle(curve59, 0.0F, 0.4363F, 0.0F);
        curve59.setTextureOffset(86, 12).addBox(0.0F, -3.0F, -6.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);

        spine = new ModelRenderer(this);
        spine.setRotationPoint(0.0F, -88.0F, 30.0F);
        body.addChild(spine);
        setRotationAngle(spine, 0.5236F, 0.0F, 0.0F);
        spine.setTextureOffset(112, 27).addBox(-8.0F, -17.0F, -8.0F, 17.0F, 17.0F, 8.0F, 0.0F, false);
        spine.setTextureOffset(96, 176).addBox(-6.0F, -34.0F, -8.0F, 13.0F, 17.0F, 8.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, -31.25F, -8.433F);
        spine.addChild(neck);
        setRotationAngle(neck, -0.4363F, 0.0F, 0.0F);
        neck.setTextureOffset(170, 176).addBox(-4.0F, -14.75F, -0.067F, 10.0F, 14.0F, 8.0F, 0.0F, false);

        curve = new ModelRenderer(this);
        curve.setRotationPoint(0.0F, 0.0F, 0.0F);
        spine.addChild(curve);
        setRotationAngle(curve, -0.6109F, 0.0F, 0.0F);
        curve.setTextureOffset(0, 143).addBox(-12.0F, 0.0F, -8.0F, 24.0F, 42.0F, 8.0F, 0.0F, false);
        curve.setTextureOffset(114, 255).addBox(12.0F, 1.0F, -9.0F, 3.0F, 26.0F, 6.0F, 0.0F, false);
        curve.setTextureOffset(188, 252).addBox(-15.0F, 1.0F, -9.0F, 3.0F, 26.0F, 6.0F, 0.0F, false);
        curve.setTextureOffset(206, 252).addBox(15.0F, 1.0F, -11.0F, 3.0F, 21.0F, 5.0F, 0.0F, false);
        curve.setTextureOffset(0, 0).addBox(-18.0F, 1.0F, -11.0F, 3.0F, 21.0F, 5.0F, 0.0F, false);
        curve.setTextureOffset(222, 262).addBox(18.0F, 1.0F, -14.0F, 3.0F, 15.0F, 5.0F, 0.0F, false);
        curve.setTextureOffset(98, 261).addBox(-21.0F, 1.0F, -14.0F, 3.0F, 15.0F, 5.0F, 0.0F, false);
        curve.setTextureOffset(238, 262).addBox(21.0F, 1.0F, -17.0F, 3.0F, 10.0F, 5.0F, 0.0F, false);
        curve.setTextureOffset(64, 143).addBox(-24.0F, 1.0F, -17.0F, 3.0F, 10.0F, 5.0F, 0.0F, false);

        curve7 = new ModelRenderer(this);
        curve7.setRotationPoint(0.0F, 42.0F, 0.0F);
        curve.addChild(curve7);
        setRotationAngle(curve7, -0.0873F, 0.0F, 0.0F);
        curve7.setTextureOffset(138, 176).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 19.0F, 8.0F, 0.0F, false);

        curve8 = new ModelRenderer(this);
        curve8.setRotationPoint(0.0F, 19.0F, -8.0F);
        curve7.addChild(curve8);
        setRotationAngle(curve8, 0.1745F, 0.0F, 0.0F);
        curve8.setTextureOffset(64, 176).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 36.0F, 8.0F, 0.0F, false);

        leftCollar = new ModelRenderer(this);
        leftCollar.setRotationPoint(4.0F, -86.0F, 27.0F);
        body.addChild(leftCollar);
        setRotationAngle(leftCollar, -0.3491F, 0.6109F, -0.2618F);
        leftCollar.setTextureOffset(122, 59).addBox(0.0F, -5.0F, -4.0F, 47.0F, 10.0F, 5.0F, 0.0F, false);

        turn = new ModelRenderer(this);
        turn.setRotationPoint(35.0F, 0.0F, 0.0F);
        leftCollar.addChild(turn);
        setRotationAngle(turn, -1.2217F, -1.0472F, 0.5236F);
        turn.setTextureOffset(112, 0).addBox(-4.0F, -5.0F, -4.0F, 5.0F, 7.0F, 52.0F, 0.0F, false);

        rightCollar = new ModelRenderer(this);
        rightCollar.setRotationPoint(-4.0F, -86.0F, 27.0F);
        body.addChild(rightCollar);
        setRotationAngle(rightCollar, -0.3491F, -0.6109F, 0.2618F);
        rightCollar.setTextureOffset(0, 91).addBox(-47.0F, -5.0F, -4.0F, 47.0F, 10.0F, 5.0F, 0.0F, false);

        turn2 = new ModelRenderer(this);
        turn2.setRotationPoint(-35.0F, 0.0F, 0.0F);
        rightCollar.addChild(turn2);
        setRotationAngle(turn2, -1.2217F, 1.0472F, -0.5236F);
        turn2.setTextureOffset(60, 60).addBox(-1.0F, -5.0F, -4.0F, 5.0F, 7.0F, 52.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(14.0F, 21.0F, 8.0F);
        body.addChild(leftArm);
        leftArm.setTextureOffset(128, 143).addBox(17.0F, -123.0F, -17.0F, 16.0F, 17.0F, 16.0F, 0.0F, false);

        bicep = new ModelRenderer(this);
        bicep.setRotationPoint(33.0F, -119.0F, -3.0F);
        leftArm.addChild(bicep);
        setRotationAngle(bicep, 0.1745F, 0.0873F, 1.0472F);
        bicep.setTextureOffset(0, 67).addBox(0.0F, -1.0F, -12.0F, 40.0F, 12.0F, 12.0F, 0.0F, false);

        collar = new ModelRenderer(this);
        collar.setRotationPoint(17.0F, -111.0F, -13.0F);
        leftArm.addChild(collar);
        setRotationAngle(collar, 0.1745F, -0.6109F, -0.1745F);
        collar.setTextureOffset(122, 88).addBox(-41.0F, -8.0F, -4.0F, 41.0F, 10.0F, 4.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-14.0F, 21.0F, 8.0F);
        body.addChild(rightArm);
        rightArm.setTextureOffset(64, 143).addBox(-33.0F, -123.0F, -17.0F, 16.0F, 17.0F, 16.0F, 0.0F, false);

        bicep2 = new ModelRenderer(this);
        bicep2.setRotationPoint(-33.0F, -119.0F, -3.0F);
        rightArm.addChild(bicep2);
        setRotationAngle(bicep2, 0.1745F, -0.0873F, -1.0472F);
        bicep2.setTextureOffset(114, 119).addBox(-36.0F, -1.0F, -12.0F, 36.0F, 12.0F, 12.0F, 0.0F, false);

        elbow = new ModelRenderer(this);
        elbow.setRotationPoint(-36.0F, 3.0F, 0.0F);
        bicep2.addChild(elbow);
        setRotationAngle(elbow, 0.2618F, -1.1345F, -0.2618F);
        elbow.setTextureOffset(0, 119).addBox(-45.0F, -4.0F, -12.0F, 45.0F, 12.0F, 12.0F, 0.0F, false);
        elbow.setTextureOffset(112, 0).addBox(-56.0F, -5.0F, -13.0F, 11.0F, 13.0F, 14.0F, 0.0F, false);
        elbow.setTextureOffset(28, 265).addBox(-61.0F, -5.0F, -6.0F, 4.0F, 13.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(264, 12).addBox(-61.0F, -5.0F, -1.9F, 4.0F, 13.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(51, 110).addBox(-57.0F, -5.0F, -1.9F, 1.0F, 5.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(101, 103).addBox(-57.0F, -5.0F, -6.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(101, 88).addBox(-57.0F, -5.0F, -9.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(92, 67).addBox(-57.0F, -5.0F, -13.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(14, 265).addBox(-61.0F, -5.0F, -9.5F, 4.0F, 13.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(0, 265).addBox(-61.0F, -5.0F, -13.0F, 4.0F, 13.0F, 3.0F, 0.0F, false);
        elbow.setTextureOffset(34, 194).addBox(-59.0F, 8.0F, -15.0F, 4.0F, 3.0F, 11.0F, 0.0F, false);
        elbow.setTextureOffset(0, 67).addBox(-59.0F, 0.0F, -15.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

        collar2 = new ModelRenderer(this);
        collar2.setRotationPoint(-17.0F, -111.0F, -13.0F);
        rightArm.addChild(collar2);
        setRotationAngle(collar2, 0.1745F, 0.6109F, 0.1745F);
        collar2.setTextureOffset(122, 74).addBox(0.0F, -8.0F, -4.0F, 41.0F, 10.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityIn.isCrouching()) {
            this.body.rotateAngleX = 0.5F;
            this.body.rotationPointY = 3.2F;
        }
        else {
            this.body.rotateAngleX = 0.0F;
            this.body.rotationPointY = 0.0F;
        }
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

    }
}
