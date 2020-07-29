package com.benarutomod.tbroski.items.models;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelAnbu extends BipedModel<LivingEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public ModelAnbu(float size) {
		super(size, 0, 0,0);
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 24).addBox(-3.0F, -25.1F, -4.25F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(33, 7).addBox(-3.9F, -25.1F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(30, 33).addBox(-3.9F, -31.9F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(9, 33).addBox(2.9F, -31.9F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(5, 33).addBox(2.9F, -25.1F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 22).addBox(-3.0F, -31.9F, -4.25F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(24, 0).addBox(-1.0F, -28.0F, -4.35F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(20, 18).addBox(-3.0F, -31.0F, -4.35F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(6, 26).addBox(-3.9F, -27.0F, -4.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 31).addBox(-3.0F, -27.0F, -4.35F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(30, 25).addBox(1.0F, -27.0F, -4.35F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 26).addBox(2.9F, -27.0F, -4.25F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(24, 24).addBox(-3.9F, -31.0F, -4.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(18, 0).addBox(2.9F, -31.0F, -4.25F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(27, 32).addBox(2.0F, -28.0F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(23, 32).addBox(2.9F, -28.0F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(19, 32).addBox(-3.0F, -28.0F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(15, 32).addBox(-3.9F, -28.0F, -4.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(10, 26).addBox(1.0F, -28.0F, -4.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(24, 22).addBox(-2.0F, -28.0F, -4.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone2.setTextureOffset(28, 0).addBox(3.4F, -24.2F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(6, 28).addBox(-4.6F, -24.2F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(30, 22).addBox(-4.6F, -24.0F, -2.2F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(28, 11).addBox(-4.6F, -24.0F, 1.2F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(30, 30).addBox(2.4F, -24.0F, -2.2F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(28, 8).addBox(-2.6F, -16.0F, -2.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(28, 5).addBox(0.4F, -16.0F, -2.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(18, 16).addBox(-4.0F, -13.0F, -2.5F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(18, 14).addBox(-4.0F, -13.0F, 1.5F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(0, 26).addBox(3.5F, -13.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(24, 24).addBox(-4.5F, -13.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(24, 29).addBox(2.4F, -24.0F, 1.2F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(0, 11).addBox(-4.0F, -22.0F, -2.2F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(18, 0).addBox(3.2F, -22.0F, -2.0F, 1.0F, 10.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(14, 18).addBox(-4.2F, -22.0F, -2.0F, 1.0F, 10.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(0, 0).addBox(-4.0F, -22.0F, 1.2F, 8.0F, 10.0F, 1.0F, 0.0F, false);

		this.bipedHeadwear.addChild(bone);
		this.bipedBody.addChild(bone2);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
/*		bone.render(matrixStack, buffer, packedLight, packedOverlay);
		bone2.render(matrixStack, buffer, packedLight, packedOverlay);*/
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}