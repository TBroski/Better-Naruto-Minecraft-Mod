package com.benarutomod.tbroski.items.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelAkatsukiCloak extends BipedModel<LivingEntity> {

	private final ModelRenderer cloakBodyBack;
	private final ModelRenderer backHood;
	private final ModelRenderer cloakArmRight;
	private final ModelRenderer cloakArmLeft;
	private final ModelRenderer cloakBodyFront;
	private final ModelRenderer cloakOpeningRight;
	private final ModelRenderer cloakOpeningLeft;
	private final ModelRenderer cloakLegRight;
	private final ModelRenderer cloakRotation;
	private final ModelRenderer cloakLegLeft;
	private final ModelRenderer cloakRotation2;

	public ModelAkatsukiCloak() {
		super(1F, 0, 0,0);
		this.textureHeight = 64;
		this.textureWidth = 64;
		cloakBodyBack = new ModelRenderer(this);
		cloakBodyBack.setRotationPoint(0.0F, 12.0F, 0.0F);
		bipedBody.addChild(cloakBodyBack);
		cloakBodyBack.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, 1.4F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		cloakBodyBack.setTextureOffset(18, 9).addBox(-4.0F, -12.0F, 1.7F, 8.0F, 5.0F, 1.0F, 0.0F, false);

		backHood = new ModelRenderer(this);
		backHood.setRotationPoint(0.0F, -11.5F, 3.5F);
		cloakBodyBack.addChild(backHood);
		backHood.setTextureOffset(56, 12).addBox(3.0F, -0.6F, -1.1F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		backHood.setTextureOffset(56, 0).addBox(-4.0F, -0.6F, -1.1F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		backHood.setTextureOffset(38, 49).addBox(-3.0F, 3.4F, -1.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		backHood.setTextureOffset(48, 26).addBox(1.0F, 3.4F, -1.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		backHood.setTextureOffset(44, 42).addBox(-1.0F, 4.4F, -1.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		backHood.setTextureOffset(40, 33).addBox(-3.0F, -0.6F, -1.4F, 6.0F, 4.0F, 1.0F, 0.0F, false);
		backHood.setTextureOffset(34, 30).addBox(-1.0F, 3.4F, -1.4F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		float y = -3;
		float x = -5;
		cloakArmRight = new ModelRenderer(this);
		cloakArmRight.setRotationPoint(5.0F, 2.0F, 0.0F);
		bipedRightArm.addChild(cloakArmRight);
		cloakArmRight.setTextureOffset(48, 17).addBox(-13.0F - x, -2.0F + y, 1.25F, 4.0F, 8.0F, 1.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(34, 37).addBox(-13.25F - x, -2.0F + y, -2.0F, 1.0F, 8.0F, 4.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(26, 52).addBox(-13.35F - x, 6.0F + y, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(38, 51).addBox(-9.65F - x, 5.0F + y, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(38, 17).addBox(-9.75F - x, -2.0F + y, -2.0F, 1.0F, 7.0F, 4.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(38, 28).addBox(-13.0F - x, -2.2F + y, -1.75F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(54, 45).addBox(-13.0F - x, -2.2F + y, -2.25F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(46, 0).addBox(-13.0F - x, -2.0F + y, -2.25F, 4.0F, 8.0F, 1.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(54, 32).addBox(-13.0F - x, 6.0F + y, -2.35F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		cloakArmRight.setTextureOffset(53, 42).addBox(-13.0F - x, 6.0F + y, 1.35F, 4.0F, 2.0F, 1.0F, 0.0F, false);

		cloakArmLeft = new ModelRenderer(this);
		cloakArmLeft.setRotationPoint(-5.0F, 2.0F, 0.0F);
		bipedLeftArm.addChild(cloakArmLeft);
		cloakArmLeft.setTextureOffset(10, 46).addBox(9.0F + x, -2.0F + y, 1.25F, 4.0F, 8.0F, 1.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(0, 38).addBox(8.75F + x, -2.0F + y, -2.0F, 1.0F, 7.0F, 4.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(50, 26).addBox(8.65F + x, 5.0F + y, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(50, 50).addBox(12.35F + x, 6.0F + y, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(20, 37).addBox(12.25F + x, -2.0F + y, -2.0F, 1.0F, 8.0F, 4.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(34, 12).addBox(9.0F + x, -2.2F + y, -1.75F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(54, 35).addBox(9.0F + x, -2.2F + y, -2.25F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(44, 44).addBox(9.0F + x, -2.0F + y, -2.25F, 4.0F, 8.0F, 1.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(52, 9).addBox(9.0F + x, 6.0F + y, -2.35F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		cloakArmLeft.setTextureOffset(22, 15).addBox(9.0F + x, 6.0F + y, 1.35F, 4.0F, 2.0F, 1.0F, 0.0F, false);

		cloakBodyFront = new ModelRenderer(this);
		cloakBodyFront.setRotationPoint(0.0F, 12.0F, 0.0F);
		bipedBody.addChild(cloakBodyFront);
		cloakBodyFront.setTextureOffset(18, 0).addBox(-4.0F, -8.0F, -2.4F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(46, 9).addBox(-4.2F, -3.0F, -2.4F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(26, 45).addBox(3.3F, -3.0F, -2.4F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(0, 18).addBox(-4.2F, -3.0F, 1.4F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(0, 13).addBox(3.3F, -3.0F, 1.4F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(34, 28).addBox(-4.0F, -9.0F, -2.4F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(34, 17).addBox(1.0F, -9.0F, -2.4F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(6, 40).addBox(3.0F, -11.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(44, 53).addBox(2.0F, -10.0F, -2.4F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(44, 19).addBox(-2.4F, -10.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(38, 19).addBox(1.5F, -10.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(55, 37).addBox(-4.0F, -10.0F, -2.4F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(44, 17).addBox(-4.0F, -11.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(30, 41).addBox(-4.0F, -12.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(0, 38).addBox(3.0F, -12.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(30, 43).addBox(-3.3F, -11.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(6, 38).addBox(2.5F, -11.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(46, 9).addBox(-1.3F, -9.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cloakBodyFront.setTextureOffset(0, 40).addBox(0.5F, -9.0F, -2.4F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cloakOpeningRight = new ModelRenderer(this);
		cloakOpeningRight.setRotationPoint(-0.5F, -8.5F, -1.9F);
		cloakBodyFront.addChild(cloakOpeningRight);
		setRotationAngle(cloakOpeningRight, 0.0F, 0.0F, 0.8727F);
		cloakOpeningRight.setTextureOffset(0, 18).addBox(-5.5F, -0.5F, -0.5F, 7.0F, 1.0F, 4.0F, 0.0F, false);
		cloakOpeningRight.setTextureOffset(44, 40).addBox(-5.5F, -0.5F, 3.3F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		cloakOpeningRight.setTextureOffset(18, 15).addBox(-5.5F, -0.5F, 4.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cloakOpeningLeft = new ModelRenderer(this);
		cloakOpeningLeft.setRotationPoint(2.5243F, -9.9222F, -1.9F);
		cloakBodyFront.addChild(cloakOpeningLeft);
		setRotationAngle(cloakOpeningLeft, 0.0F, 0.0F, 2.2689F);
		cloakOpeningLeft.setTextureOffset(0, 13).addBox(-3.0757F, -0.0757F, -0.5F, 7.0F, 1.0F, 4.0F, 0.0F, false);
		cloakOpeningLeft.setTextureOffset(40, 38).addBox(-3.0757F, -0.0757F, 3.3F, 7.0F, 1.0F, 1.0F, 0.0F, false);
		cloakOpeningLeft.setTextureOffset(24, 34).addBox(-3.0757F, -0.0757F, 4.3F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		float ly = -12;
		float lx = -2;
		cloakLegRight = new ModelRenderer(this);
		cloakLegRight.setRotationPoint(1.9F, 12.0F, 0.0F);
		bipedRightLeg.addChild(cloakLegRight);
		cloakLegRight.setTextureOffset(14, 55).addBox(-5.9F - lx, 0.0F + ly, -2.4F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(51, 56).addBox(-3.9F - lx, 0.0F + ly, -2.4F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(28, 28).addBox(-2.9F - lx, 0.0F + ly, -2.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(47, 56).addBox(-3.9F - lx, 0.0F + ly, -2.4F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(55, 56).addBox(-5.9F - lx, 5.0F + ly, -2.4F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(0, 23).addBox(-5.9F - lx, 7.0F + ly, -2.4F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(32, 49).addBox(-2.4F - lx, 0.0F + ly, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(28, 15).addBox(-2.4F - lx, 1.8F + ly, -1.9F, 1.0F, 9.0F, 4.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(0, 23).addBox(-6.1F - lx, -0.2F + ly, -2.4F, 1.0F, 11.0F, 4.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(4, 49).addBox(-6.1F - lx, -0.2F + ly, 1.4F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		cloakLegRight.setTextureOffset(36, 0).addBox(-5.9F - lx, -0.2F + ly, 1.4F, 4.0F, 11.0F, 1.0F, 0.0F, false);

		cloakRotation = new ModelRenderer(this);
		cloakRotation.setRotationPoint(-2.4F, 1.5F, -2.5F);
		cloakLegRight.addChild(cloakRotation);
		setRotationAngle(cloakRotation, 0.0F, 0.0F, 0.3491F);
		cloakRotation.setTextureOffset(14, 23).addBox(-0.406F + lx, -0.5342F + ly, 0.1F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		cloakRotation.setTextureOffset(20, 55).addBox(-1.1239F + lx, 0.6629F + ly, 0.1F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		cloakLegLeft = new ModelRenderer(this);
		cloakLegLeft.setRotationPoint(2.1F, 12.0F, 0.0F);
		bipedLeftLeg.addChild(cloakLegLeft);
		cloakLegLeft.setTextureOffset(10, 34).addBox(-2.1F + lx, -0.2F + ly, 1.4F, 4.0F, 11.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(0, 49).addBox(1.2F + lx, -0.2F + ly, 1.4F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(18, 19).addBox(1.2F + lx, -0.2F + ly, -2.4F, 1.0F, 11.0F, 4.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(8, 55).addBox(-0.1F + lx, 0.0F + ly, -2.4F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(24, 18).addBox(-1.1F + lx, 0.0F + ly, -2.4F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(6, 23).addBox(-2.1F + lx, 0.0F + ly, -2.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(54, 47).addBox(-0.1F + lx, 5.0F + ly, -2.4F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(18, 18).addBox(0.9F + lx, 7.0F + ly, -2.4F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(20, 49).addBox(-2.6F + lx, 0.0F + ly, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		cloakLegLeft.setTextureOffset(28, 28).addBox(-2.6F + lx, 1.7F + ly, -1.9F, 1.0F, 9.0F, 4.0F, 0.0F, false);

		cloakRotation2 = new ModelRenderer(this);
		cloakRotation2.setRotationPoint(-1.7F, 1.5F, -2.5F);
		cloakLegLeft.addChild(cloakRotation2);
		setRotationAngle(cloakRotation2, 0.0F, 0.0F, -0.3491F);
		cloakRotation2.setTextureOffset(10, 23).addBox(-0.406F - lx, -0.5342F + ly, 0.1F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		cloakRotation2.setTextureOffset(20, 34).addBox(0.1578F - lx,0.0652F + ly, 0.1F, 1.0F, 6.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.bipedLeftLeg.showModel = true;
		this.bipedRightLeg.showModel = true;
		super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}