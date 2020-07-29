package com.benarutomod.tbroski.items.models;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelHeadband extends BipedModel<LivingEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;

	public ModelHeadband(float size) {
		super(size, 0, 0, 0);
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(10, 14).addBox(-4.0F, -31.0F, -4.2F, 1.0F, 2.0F, 1.0F, 0.0F, false); //-4.25
		bone.setTextureOffset(6, 14).addBox(3.0F, -31.0F, -4.2F, 1.0F, 2.0F, 1.0F, 0.0F, false); //-4.25
		bone.setTextureOffset(0, 9).addBox(-3.0F, -31.0F, -4.45F, 6.0F, 2.0F, 1.0F, 0.0F, false); //-4.5
		bone.setTextureOffset(3, 17).addBox(2.25F, -30.75F, -4.45F, 1.0F, 1.0F, 1.0F, 0.0F, false); //-4.5
		bone.setTextureOffset(13, 16).addBox(2.25F, -30.25F, -4.45F, 1.0F, 1.0F, 1.0F, 0.0F, false); //-4.5
		bone.setTextureOffset(0, 16).addBox(-3.25F, -30.25F, -4.45F, 1.0F, 1.0F, 1.0F, 0.0F, false); //-4.5
		bone.setTextureOffset(15, 11).addBox(-3.25F, -30.75F, -4.45F, 1.0F, 1.0F, 1.0F, 0.0F, false); //-4.5

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(4.0F, -6.0F, 0.5F); //Y = -30
		setRotationAngle(bone2, 0.0F, 1.5708F, 0.0F);
		bone2.setTextureOffset(0, 6).addBox(-3.5F, -1.0F, -0.75F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(0, 3).addBox(-3.5F, -1.0F, -8.25F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(1.0F, -6.0F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, 3.25F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(0, 12).addBox(-3.0F, 1.0F, 3.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(0, 14).addBox(-0.5F, 2.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(14, 9).addBox(0.5F, 3.0F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(10, 12).addBox(-3.5F, 2.0F, 3.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(14, 14).addBox(-3.5F, 3.0F, 3.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		this.bipedHeadwear.addChild(bone);
		this.bipedHeadwear.addChild(bone2);
		this.bipedHeadwear.addChild(bone3);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		/*		bone.render(matrixStack, buffer, packedLight, packedOverlay);
		bone2.render(matrixStack, buffer, packedLight, packedOverlay);
		bone3.render(matrixStack, buffer, packedLight, packedOverlay);*/
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}