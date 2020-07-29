package com.benarutomod.tbroski.client.renderer.layers.models;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelAmaterasu<T extends LivingEntity> extends AgeableModel<T> {
	private final ModelRenderer bone;

	public ModelAmaterasu(int frame) {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this); //frame * 88
		bone.setRotationPoint(0.0F, 13.0F, 0.0F);
		setRotationAngle(bone, 0.0F, -1.5708F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-4.0F, -11.0F, 0.0F, 8.0F, 22.0F, 0.0F, 0.0F, false);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.bone);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//previously the render function, render code was moved to a method below
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}