package com.benarutomod.tbroski.client.renderer.layers.models;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelScroll<T extends LivingEntity> extends AgeableModel<T> {

	private final ModelRenderer scroll;
	private final ModelRenderer bone;

	public ModelScroll() {
		textureWidth = 128;
		textureHeight = 128;

		scroll = new ModelRenderer(this);
		scroll.setRotationPoint(3.0F, 23.5F, -1.0F);
		scroll.setTextureOffset(0, 27).addBox(-15.0F, -17.0F, 2.5F, 24.0F, 4.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 32).addBox(-15.0F, -17.0F, 9.0F, 24.0F, 3.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 47).addBox(-15.0F, -14.0F, 3.0F, 24.0F, 2.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 36).addBox(-15.0F, -14.0F, 4.0F, 24.0F, 3.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 17).addBox(-15.0F, -18.0F, 8.0F, 24.0F, 4.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 44).addBox(-15.0F, -18.0F, 7.0F, 24.0F, 2.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 22).addBox(-15.0F, -18.0F, 3.0F, 24.0F, 4.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 40).addBox(-15.0F, -19.0F, 4.0F, 24.0F, 3.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 9).addBox(-15.0F, -12.0F, 5.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);
		scroll.setTextureOffset(0, 13).addBox(-15.0F, -19.0F, 5.0F, 24.0F, 2.0F, 2.0F, 0.0F, false);
		scroll.setTextureOffset(49, 49).addBox(-15.0F, -19.0F, 7.0F, 24.0F, 1.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 0).addBox(-15.0F, -13.0F, 5.0F, 24.0F, 1.0F, 4.0F, 0.0F, false);
		scroll.setTextureOffset(0, 5).addBox(-15.0F, -14.0F, 7.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);
		scroll.setTextureOffset(46, 51).addBox(8.0F, -16.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(50, 46).addBox(8.0F, -16.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(49, 34).addBox(8.0F, -14.0F, 5.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		scroll.setTextureOffset(8, 50).addBox(8.0F, -16.0F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		scroll.setTextureOffset(49, 25).addBox(-16.0F, -17.0F, 5.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		scroll.setTextureOffset(49, 20).addBox(-16.0F, -14.0F, 5.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		scroll.setTextureOffset(50, 43).addBox(-16.0F, -16.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(50, 40).addBox(-16.0F, -16.0F, 7.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		scroll.setTextureOffset(0, 50).addBox(-16.0F, -16.0F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		scroll.setTextureOffset(49, 30).addBox(8.0F, -17.0F, 5.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		//		bone.setRotationPoint(5.0F, 0.0F, 1.5F);
		bone.setRotationPoint(5.0F, 0.0F, 1.5F);
		bone.setTextureOffset(51, 9).addBox(-6.0F, 5.0F, 0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(16, 50).addBox(-6.0F, 6.0F, -4.2F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(50, 28).addBox(-7.0F, 8.0F, -4.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(50, 23).addBox(-8.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(50, 18).addBox(-9.0F, 10.0F, -4.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(51, 5).addBox(-5.0F, 4.0F, 0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(22, 50).addBox(-5.0F, 4.0F, -4.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(50, 37).addBox(-3.75F, 2.0F, 0.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(28, 50).addBox(-3.75F, 2.0F, -4.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(40, 50).addBox(-2.5F, 0.0F, 0.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(34, 50).addBox(-2.5F, 0.0F, -4.25F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(49, 12).addBox(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entityIn.isCrouching()) {
			this.bone.rotateAngleX = 0.45F;
			this.bone.rotationPointY = 3.2F;
			this.scroll.rotateAngleX = -0.05F;
			this.scroll.rotationPointY = 23.5F; // 3.2F
		} else {
			this.bone.rotateAngleX = 0.0F;
			this.bone.rotationPointY = 0.0F;
			this.scroll.rotateAngleX = 0.0F;
			this.scroll.rotationPointY = 23.5F;
		}
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.scroll, this.bone);
	}
}