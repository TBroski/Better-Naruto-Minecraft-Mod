package com.benarutomod.tbroski.client.renderer.layers.models.dojutsu;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ModelLeftEye<T extends LivingEntity> extends AgeableModel<T> {

	private float swimAnimation;

	private final ModelRenderer leftEye;

	public ModelLeftEye(float size, float translate) {
		textureWidth = 16;
		textureHeight = 16;

		leftEye = new ModelRenderer(this);
		leftEye.setRotationPoint(0.0F, 0.0F, 0.0F); // wi = 2, x = -3
		leftEye.setTextureOffset(0, 2).addBox(-2.0F - size, (translate - 8.0F), -4.02F, 1.0F + size, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.leftEye);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of();
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.leftEye.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

		this.leftEye.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		if (entityIn instanceof PlayerEntity) {
			if (entityIn.isCrouching()) {
				this.leftEye.rotationPointY = 4.2F;
			} else {
				this.leftEye.rotationPointY = 0.0F;
			}
		}

		boolean flag = entityIn.getTicksElytraFlying() > 4;
		boolean flag1 = entityIn.isActualySwimming();
		if (flag) {
			this.leftEye.rotateAngleX = (-(float) Math.PI / 4F);
		} else if (this.swimAnimation > 0.0F) {
			if (flag1) {
				this.leftEye.rotateAngleX = this.rotLerpRad(this.leftEye.rotateAngleX, (-(float) Math.PI / 4F), this.swimAnimation);
			} else {
				this.leftEye.rotateAngleX = this.rotLerpRad(this.leftEye.rotateAngleX, headPitch * ((float) Math.PI / 180F), this.swimAnimation);
			}
		} else {
			this.leftEye.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		}
	}

	protected float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
		float f = (maxAngleIn - angleIn) % ((float) Math.PI * 2F);
		if (f < -(float) Math.PI) {
			f += ((float) Math.PI * 2F);
		}

		if (f >= (float) Math.PI) {
			f -= ((float) Math.PI * 2F);
		}

		return angleIn + mulIn * f;
	}

	public void setPartialTick(T entityIn, float partialTick) {
		this.swimAnimation = entityIn.getSwimAnimation(partialTick);
	}
}