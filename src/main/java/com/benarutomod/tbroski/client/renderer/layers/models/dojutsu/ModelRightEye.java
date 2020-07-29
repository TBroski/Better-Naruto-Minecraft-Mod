package com.benarutomod.tbroski.client.renderer.layers.models.dojutsu;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class ModelRightEye<T extends LivingEntity> extends AgeableModel<T> {

	private float swimAnimation;

	private final ModelRenderer rightEye;


	public ModelRightEye(float size, float translate) {
		textureWidth = 16;
		textureHeight = 16;

		rightEye = new ModelRenderer(this);
		rightEye.setRotationPoint(0.0F, 0.0F, 0.0F);
		rightEye.setTextureOffset(0, 0).addBox(1.0F, (translate - 8.0F), -4.02F, 1.0F + size, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.rightEye);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of();
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightEye.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);

		this.rightEye.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		if (entityIn instanceof PlayerEntity) {
			if (entityIn.isCrouching()) {
				this.rightEye.rotationPointY = 4.2F;
			} else {
				this.rightEye.rotationPointY = 0.0F;
			}
		}

		boolean flag = entityIn.getTicksElytraFlying() > 4;
		boolean flag1 = entityIn.isActualySwimming();
		if (flag) {
			this.rightEye.rotateAngleX = (-(float) Math.PI / 4F);
		} else if (this.swimAnimation > 0.0F) {
			if (flag1) {
				this.rightEye.rotateAngleX = this.rotLerpRad(this.rightEye.rotateAngleX, (-(float) Math.PI / 4F), this.swimAnimation);
			} else {
				this.rightEye.rotateAngleX = this.rotLerpRad(this.rightEye.rotateAngleX, headPitch * ((float) Math.PI / 180F), this.swimAnimation);
			}
		} else {
			this.rightEye.rotateAngleX = headPitch * ((float) Math.PI / 180F);
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