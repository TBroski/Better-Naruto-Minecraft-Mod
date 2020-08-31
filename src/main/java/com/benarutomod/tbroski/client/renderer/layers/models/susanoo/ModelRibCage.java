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

public class ModelRibCage<T extends LivingEntity> extends ModelSusanoo<T> {

    private static final ResourceLocation TEMPLATE = new ResourceLocation(Main.MODID, "textures/entity/layer/susanoo/stage1/template.png");

    private final ModelRenderer spine;
    private final ModelRenderer curve7;
    private final ModelRenderer curve8;
    private final ModelRenderer rib1;
    private final ModelRenderer curve;
    private final ModelRenderer curve2;
    private final ModelRenderer curve3;
    private final ModelRenderer curve4;
    private final ModelRenderer curve5;
    private final ModelRenderer curve6;
    private final ModelRenderer rib5;
    private final ModelRenderer curve27;
    private final ModelRenderer curve28;
    private final ModelRenderer curve29;
    private final ModelRenderer curve30;
    private final ModelRenderer curve31;
    private final ModelRenderer curve32;
    private final ModelRenderer rib6;
    private final ModelRenderer curve33;
    private final ModelRenderer curve34;
    private final ModelRenderer curve35;
    private final ModelRenderer curve36;
    private final ModelRenderer curve37;
    private final ModelRenderer curve38;
    private final ModelRenderer rib3;
    private final ModelRenderer curve15;
    private final ModelRenderer curve16;
    private final ModelRenderer curve17;
    private final ModelRenderer curve18;
    private final ModelRenderer curve19;
    private final ModelRenderer curve20;
    private final ModelRenderer rib7;
    private final ModelRenderer curve39;
    private final ModelRenderer curve40;
    private final ModelRenderer curve41;
    private final ModelRenderer curve42;
    private final ModelRenderer curve43;
    private final ModelRenderer curve44;
    private final ModelRenderer rib4;
    private final ModelRenderer curve21;
    private final ModelRenderer curve22;
    private final ModelRenderer curve23;
    private final ModelRenderer curve24;
    private final ModelRenderer curve25;
    private final ModelRenderer curve26;
    private final ModelRenderer rib8;
    private final ModelRenderer curve45;
    private final ModelRenderer curve46;
    private final ModelRenderer curve47;
    private final ModelRenderer curve48;
    private final ModelRenderer curve49;
    private final ModelRenderer curve50;
    private final ModelRenderer rib2;
    private final ModelRenderer curve9;
    private final ModelRenderer curve10;
    private final ModelRenderer curve11;
    private final ModelRenderer curve12;
    private final ModelRenderer curve13;
    private final ModelRenderer curve14;

    public ModelRibCage() {
        textureWidth = 128;
        textureHeight = 128;

        spine = new ModelRenderer(this);
        spine.setRotationPoint(0.0F, 12.0F, 0.0F);
        spine.setTextureOffset(0, 0).addBox(-2.0F, -25.0F, 10.0F, 4.0F, 18.0F, 4.0F, 0.0F, false);

        curve7 = new ModelRenderer(this);
        curve7.setRotationPoint(0.0F, -7.0F, 14.0F);
        spine.addChild(curve7);
        setRotationAngle(curve7, -0.2618F, 0.0F, 0.0F);
        curve7.setTextureOffset(16, 0).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

        curve8 = new ModelRenderer(this);
        curve8.setRotationPoint(0.0F, 5.0F, -4.0F);
        curve7.addChild(curve8);
        setRotationAngle(curve8, 0.2618F, 0.0F, 0.0F);
        curve8.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        rib1 = new ModelRenderer(this);
        rib1.setRotationPoint(-2.0F, -12.0F, 13.0F);
        spine.addChild(rib1);
        setRotationAngle(rib1, 0.0F, -0.1745F, 0.0F);
        rib1.setTextureOffset(0, 71).addBox(-6.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve = new ModelRenderer(this);
        curve.setRotationPoint(-6.0F, -0.5F, 0.5F);
        rib1.addChild(curve);
        setRotationAngle(curve, 0.0F, -0.5236F, 0.0F);
        curve.setTextureOffset(51, 69).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve2 = new ModelRenderer(this);
        curve2.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve.addChild(curve2);
        setRotationAngle(curve2, 0.0F, -0.4363F, 0.0F);
        curve2.setTextureOffset(66, 60).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve3 = new ModelRenderer(this);
        curve3.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve2.addChild(curve3);
        setRotationAngle(curve3, 0.0F, -0.4363F, 0.0F);
        curve3.setTextureOffset(66, 54).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve4 = new ModelRenderer(this);
        curve4.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve3.addChild(curve4);
        setRotationAngle(curve4, 0.0F, -0.4363F, 0.0F);
        curve4.setTextureOffset(66, 48).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve5 = new ModelRenderer(this);
        curve5.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve4.addChild(curve5);
        setRotationAngle(curve5, 0.0F, -0.3491F, 0.0F);
        curve5.setTextureOffset(66, 42).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve6 = new ModelRenderer(this);
        curve6.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve5.addChild(curve6);
        setRotationAngle(curve6, 0.0F, -0.3491F, 0.0F);
        curve6.setTextureOffset(55, 75).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib5 = new ModelRenderer(this);
        rib5.setRotationPoint(-2.0F, -20.0F, 13.0F);
        spine.addChild(rib5);
        setRotationAngle(rib5, 0.0F, -0.1745F, 0.0F);
        rib5.setTextureOffset(51, 45).addBox(-6.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve27 = new ModelRenderer(this);
        curve27.setRotationPoint(-6.0F, -0.5F, 0.5F);
        rib5.addChild(curve27);
        setRotationAngle(curve27, 0.0F, -0.5236F, 0.0F);
        curve27.setTextureOffset(51, 39).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve28 = new ModelRenderer(this);
        curve28.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve27.addChild(curve28);
        setRotationAngle(curve28, 0.0F, -0.4363F, 0.0F);
        curve28.setTextureOffset(51, 51).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve29 = new ModelRenderer(this);
        curve29.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve28.addChild(curve29);
        setRotationAngle(curve29, 0.0F, -0.4363F, 0.0F);
        curve29.setTextureOffset(36, 48).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve30 = new ModelRenderer(this);
        curve30.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve29.addChild(curve30);
        setRotationAngle(curve30, 0.0F, -0.4363F, 0.0F);
        curve30.setTextureOffset(47, 30).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve31 = new ModelRenderer(this);
        curve31.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve30.addChild(curve31);
        setRotationAngle(curve31, 0.0F, -0.3491F, 0.0F);
        curve31.setTextureOffset(47, 15).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve32 = new ModelRenderer(this);
        curve32.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve31.addChild(curve32);
        setRotationAngle(curve32, 0.0F, -0.3491F, 0.0F);
        curve32.setTextureOffset(38, 72).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib6 = new ModelRenderer(this);
        rib6.setRotationPoint(2.0F, -20.0F, 13.0F);
        spine.addChild(rib6);
        setRotationAngle(rib6, 0.0F, 0.1745F, 0.0F);
        rib6.setTextureOffset(47, 3).addBox(0.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve33 = new ModelRenderer(this);
        curve33.setRotationPoint(6.0F, -0.5F, 0.5F);
        rib6.addChild(curve33);
        setRotationAngle(curve33, 0.0F, 0.5236F, 0.0F);
        curve33.setTextureOffset(18, 47).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve34 = new ModelRenderer(this);
        curve34.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve33.addChild(curve34);
        setRotationAngle(curve34, 0.0F, 0.4363F, 0.0F);
        curve34.setTextureOffset(0, 47).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve35 = new ModelRenderer(this);
        curve35.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve34.addChild(curve35);
        setRotationAngle(curve35, 0.0F, 0.4363F, 0.0F);
        curve35.setTextureOffset(46, 9).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve36 = new ModelRenderer(this);
        curve36.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve35.addChild(curve36);
        setRotationAngle(curve36, 0.0F, 0.4363F, 0.0F);
        curve36.setTextureOffset(44, 24).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve37 = new ModelRenderer(this);
        curve37.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve36.addChild(curve37);
        setRotationAngle(curve37, 0.0F, 0.3491F, 0.0F);
        curve37.setTextureOffset(36, 42).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve38 = new ModelRenderer(this);
        curve38.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve37.addChild(curve38);
        setRotationAngle(curve38, 0.0F, 0.3491F, 0.0F);
        curve38.setTextureOffset(28, 71).addBox(0.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib3 = new ModelRenderer(this);
        rib3.setRotationPoint(-2.0F, -5.0F, 12.0F);
        spine.addChild(rib3);
        setRotationAngle(rib3, 0.0F, -0.1745F, 0.0F);
        rib3.setTextureOffset(62, 27).addBox(-6.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve15 = new ModelRenderer(this);
        curve15.setRotationPoint(-6.0F, -0.5F, 0.5F);
        rib3.addChild(curve15);
        setRotationAngle(curve15, 0.0F, -0.5236F, 0.0F);
        curve15.setTextureOffset(62, 12).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve16 = new ModelRenderer(this);
        curve16.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve15.addChild(curve16);
        setRotationAngle(curve16, 0.0F, -0.4363F, 0.0F);
        curve16.setTextureOffset(62, 6).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve17 = new ModelRenderer(this);
        curve17.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve16.addChild(curve17);
        setRotationAngle(curve17, 0.0F, -0.4363F, 0.0F);
        curve17.setTextureOffset(62, 0).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve18 = new ModelRenderer(this);
        curve18.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve17.addChild(curve18);
        setRotationAngle(curve18, 0.0F, -0.4363F, 0.0F);
        curve18.setTextureOffset(36, 60).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve19 = new ModelRenderer(this);
        curve19.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve18.addChild(curve19);
        setRotationAngle(curve19, 0.0F, -0.3491F, 0.0F);
        curve19.setTextureOffset(59, 21).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve20 = new ModelRenderer(this);
        curve20.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve19.addChild(curve20);
        setRotationAngle(curve20, 0.0F, -0.3491F, 0.0F);
        curve20.setTextureOffset(74, 18).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib7 = new ModelRenderer(this);
        rib7.setRotationPoint(2.0F, -5.0F, 12.0F);
        spine.addChild(rib7);
        setRotationAngle(rib7, 0.0F, 0.1745F, 0.0F);
        rib7.setTextureOffset(18, 41).addBox(0.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve39 = new ModelRenderer(this);
        curve39.setRotationPoint(6.0F, -0.5F, 0.5F);
        rib7.addChild(curve39);
        setRotationAngle(curve39, 0.0F, 0.5236F, 0.0F);
        curve39.setTextureOffset(0, 41).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve40 = new ModelRenderer(this);
        curve40.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve39.addChild(curve40);
        setRotationAngle(curve40, 0.0F, 0.4363F, 0.0F);
        curve40.setTextureOffset(36, 36).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve41 = new ModelRenderer(this);
        curve41.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve40.addChild(curve41);
        setRotationAngle(curve41, 0.0F, 0.4363F, 0.0F);
        curve41.setTextureOffset(18, 35).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve42 = new ModelRenderer(this);
        curve42.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve41.addChild(curve42);
        setRotationAngle(curve42, 0.0F, 0.4363F, 0.0F);
        curve42.setTextureOffset(0, 35).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve43 = new ModelRenderer(this);
        curve43.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve42.addChild(curve43);
        setRotationAngle(curve43, 0.0F, 0.3491F, 0.0F);
        curve43.setTextureOffset(32, 18).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve44 = new ModelRenderer(this);
        curve44.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve43.addChild(curve44);
        setRotationAngle(curve44, 0.0F, 0.3491F, 0.0F);
        curve44.setTextureOffset(18, 71).addBox(0.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib4 = new ModelRenderer(this);
        rib4.setRotationPoint(-2.0F, 3.0F, 11.75F);
        spine.addChild(rib4);
        setRotationAngle(rib4, 0.0F, -0.1745F, 0.0F);
        rib4.setTextureOffset(18, 59).addBox(-6.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve21 = new ModelRenderer(this);
        curve21.setRotationPoint(-6.0F, -0.5F, 0.5F);
        rib4.addChild(curve21);
        setRotationAngle(curve21, 0.0F, -0.5236F, 0.0F);
        curve21.setTextureOffset(0, 59).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve22 = new ModelRenderer(this);
        curve22.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve21.addChild(curve22);
        setRotationAngle(curve22, 0.0F, -0.4363F, 0.0F);
        curve22.setTextureOffset(51, 57).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve23 = new ModelRenderer(this);
        curve23.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve22.addChild(curve23);
        setRotationAngle(curve23, 0.0F, -0.4363F, 0.0F);
        curve23.setTextureOffset(36, 54).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve24 = new ModelRenderer(this);
        curve24.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve23.addChild(curve24);
        setRotationAngle(curve24, 0.0F, -0.4363F, 0.0F);
        curve24.setTextureOffset(18, 53).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve25 = new ModelRenderer(this);
        curve25.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve24.addChild(curve25);
        setRotationAngle(curve25, 0.0F, -0.3491F, 0.0F);
        curve25.setTextureOffset(0, 53).addBox(-6.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve26 = new ModelRenderer(this);
        curve26.setRotationPoint(-6.0F, 0.0F, 0.0F);
        curve25.addChild(curve26);
        setRotationAngle(curve26, 0.0F, -0.3491F, 0.0F);
        curve26.setTextureOffset(66, 72).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib8 = new ModelRenderer(this);
        rib8.setRotationPoint(2.0F, 3.0F, 11.75F);
        spine.addChild(rib8);
        setRotationAngle(rib8, 0.0F, 0.1745F, 0.0F);
        rib8.setTextureOffset(32, 0).addBox(0.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve45 = new ModelRenderer(this);
        curve45.setRotationPoint(6.0F, -0.5F, 0.5F);
        rib8.addChild(curve45);
        setRotationAngle(curve45, 0.0F, 0.5236F, 0.0F);
        curve45.setTextureOffset(31, 12).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve46 = new ModelRenderer(this);
        curve46.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve45.addChild(curve46);
        setRotationAngle(curve46, 0.0F, 0.4363F, 0.0F);
        curve46.setTextureOffset(31, 6).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve47 = new ModelRenderer(this);
        curve47.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve46.addChild(curve47);
        setRotationAngle(curve47, 0.0F, 0.4363F, 0.0F);
        curve47.setTextureOffset(29, 29).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve48 = new ModelRenderer(this);
        curve48.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve47.addChild(curve48);
        setRotationAngle(curve48, 0.0F, 0.4363F, 0.0F);
        curve48.setTextureOffset(0, 29).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve49 = new ModelRenderer(this);
        curve49.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve48.addChild(curve49);
        setRotationAngle(curve49, 0.0F, 0.3491F, 0.0F);
        curve49.setTextureOffset(16, 9).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve50 = new ModelRenderer(this);
        curve50.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve49.addChild(curve50);
        setRotationAngle(curve50, 0.0F, 0.3491F, 0.0F);
        curve50.setTextureOffset(0, 22).addBox(0.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

        rib2 = new ModelRenderer(this);
        rib2.setRotationPoint(2.0F, -12.0F, 13.0F);
        spine.addChild(rib2);
        setRotationAngle(rib2, 0.0F, 0.1745F, 0.0F);
        rib2.setTextureOffset(66, 66).addBox(0.0F, -1.5F, -2.5F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve9 = new ModelRenderer(this);
        curve9.setRotationPoint(6.0F, -0.5F, 0.5F);
        rib2.addChild(curve9);
        setRotationAngle(curve9, 0.0F, 0.5236F, 0.0F);
        curve9.setTextureOffset(36, 66).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve10 = new ModelRenderer(this);
        curve10.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve9.addChild(curve10);
        setRotationAngle(curve10, 0.0F, 0.4363F, 0.0F);
        curve10.setTextureOffset(18, 65).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve11 = new ModelRenderer(this);
        curve11.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve10.addChild(curve11);
        setRotationAngle(curve11, 0.0F, 0.4363F, 0.0F);
        curve11.setTextureOffset(0, 65).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve12 = new ModelRenderer(this);
        curve12.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve11.addChild(curve12);
        setRotationAngle(curve12, 0.0F, 0.4363F, 0.0F);
        curve12.setTextureOffset(51, 63).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve13 = new ModelRenderer(this);
        curve13.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve12.addChild(curve13);
        setRotationAngle(curve13, 0.0F, 0.3491F, 0.0F);
        curve13.setTextureOffset(62, 33).addBox(0.0F, -1.0F, -3.0F, 6.0F, 3.0F, 3.0F, 0.0F, false);

        curve14 = new ModelRenderer(this);
        curve14.setRotationPoint(6.0F, 0.0F, 0.0F);
        curve13.addChild(curve14);
        setRotationAngle(curve14, 0.0F, 0.3491F, 0.0F);
        curve14.setTextureOffset(45, 75).addBox(0.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityIn.isCrouching()) {
            this.spine.rotateAngleX = 0.5F;
            this.spine.rotationPointY = 3.2F;
        }
        else {
            this.spine.rotateAngleX = 0.0F;
            this.spine.rotationPointY = 0.0F;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        spine.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.spine);
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
