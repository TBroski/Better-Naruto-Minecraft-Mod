package com.benarutomod.tbroski.client.renderer.layers.models.bodymode;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class ModelTruthSeekingOrbs<T extends LivingEntity> extends AgeableModel<T> {

    private final ModelRenderer orb1;
    private final ModelRenderer orb2;
    private final ModelRenderer orb3;
    private final ModelRenderer orb4;
    private final ModelRenderer orb5;
    private final ModelRenderer orb6;
    private final ModelRenderer orb7;
    private final ModelRenderer orb8;
    private final ModelRenderer orb9;
    private final ModelRenderer orb10;

    public ModelTruthSeekingOrbs() {
        textureWidth = 64;
        textureHeight = 64;

        orb1 = new ModelRenderer(this);
        orb1.setRotationPoint(5.0F, 27.0F, 1.0F);
        orb1.setTextureOffset(6, 36).addBox(-14.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb1.setTextureOffset(0, 28).addBox(-15.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb1.setTextureOffset(0, 18).addBox(-14.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb2 = new ModelRenderer(this);
        orb2.setRotationPoint(0.0F, 20.0F, 2.0F);
        orb2.setTextureOffset(34, 24).addBox(-14.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb2.setTextureOffset(22, 26).addBox(-15.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb2.setTextureOffset(16, 6).addBox(-14.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb3 = new ModelRenderer(this);
        orb3.setRotationPoint(-1.0F, 12.0F, 1.0F);
        orb3.setTextureOffset(34, 18).addBox(-14.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb3.setTextureOffset(10, 26).addBox(-15.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb3.setTextureOffset(16, 0).addBox(-14.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb4 = new ModelRenderer(this);
        orb4.setRotationPoint(2.0F, 5.0F, 2.0F);
        orb4.setTextureOffset(34, 2).addBox(-14.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb4.setTextureOffset(24, 16).addBox(-15.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb4.setTextureOffset(16, 16).addBox(-14.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb5 = new ModelRenderer(this);
        orb5.setRotationPoint(9.0F, 1.0F, 4.0F);
        orb5.setTextureOffset(32, 34).addBox(-14.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb5.setTextureOffset(24, 6).addBox(-15.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb5.setTextureOffset(8, 14).addBox(-14.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb6 = new ModelRenderer(this);
        orb6.setRotationPoint(-9.0F, 1.0F, 4.0F);
        orb6.setTextureOffset(0, 32).addBox(12.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb6.setTextureOffset(24, 0).addBox(11.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb6.setTextureOffset(0, 12).addBox(12.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb7 = new ModelRenderer(this);
        orb7.setRotationPoint(-2.0F, 5.0F, 2.0F);
        orb7.setTextureOffset(30, 10).addBox(12.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb7.setTextureOffset(0, 24).addBox(11.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb7.setTextureOffset(8, 2).addBox(12.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb8 = new ModelRenderer(this);
        orb8.setRotationPoint(1.0F, 12.0F, 1.0F);
        orb8.setTextureOffset(26, 30).addBox(12.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb8.setTextureOffset(22, 22).addBox(11.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb8.setTextureOffset(8, 8).addBox(12.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb9 = new ModelRenderer(this);
        orb9.setRotationPoint(0.0F, 20.0F, 2.0F);
        orb9.setTextureOffset(18, 30).addBox(12.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb9.setTextureOffset(10, 22).addBox(11.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb9.setTextureOffset(0, 6).addBox(12.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        orb10 = new ModelRenderer(this);
        orb10.setRotationPoint(-5.0F, 27.0F, 1.0F);
        orb10.setTextureOffset(10, 30).addBox(12.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        orb10.setTextureOffset(18, 12).addBox(11.0F, -13.0F, 2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        orb10.setTextureOffset(0, 0).addBox(12.0F, -13.0F, 1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

/*    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        orb1.render(matrixStack, buffer, packedLight, packedOverlay);
        orb2.render(matrixStack, buffer, packedLight, packedOverlay);
        orb3.render(matrixStack, buffer, packedLight, packedOverlay);
        orb4.render(matrixStack, buffer, packedLight, packedOverlay);
        orb5.render(matrixStack, buffer, packedLight, packedOverlay);
        orb6.render(matrixStack, buffer, packedLight, packedOverlay);
        orb7.render(matrixStack, buffer, packedLight, packedOverlay);
        orb8.render(matrixStack, buffer, packedLight, packedOverlay);
        orb9.render(matrixStack, buffer, packedLight, packedOverlay);
        orb10.render(matrixStack, buffer, packedLight, packedOverlay);
    }*/

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.orb1, this.orb2, this.orb3, this.orb4, this.orb5, this.orb6, this.orb7, this.orb8, this.orb9, this.orb10);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


    public void setNumberOfOrbsToRender(int amount) {
        switch (amount) {
            case 1:
                this.orb1.showModel = false;
                this.orb2.showModel = false;
                this.orb3.showModel = false;
                this.orb4.showModel = true;
                this.orb5.showModel = false;
                this.orb6.showModel = false;
                this.orb7.showModel = false;
                this.orb8.showModel = false;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 2:
                this.orb1.showModel = false;
                this.orb2.showModel = false;
                this.orb3.showModel = false;
                this.orb4.showModel = true;
                this.orb5.showModel = false;
                this.orb6.showModel = false;
                this.orb7.showModel = true;
                this.orb8.showModel = false;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 3:
                this.orb1.showModel = false;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = false;
                this.orb6.showModel = false;
                this.orb7.showModel = true;
                this.orb8.showModel = false;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 4:
                this.orb1.showModel = false;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = false;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = false;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 5:
                this.orb1.showModel = false;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = true;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = false;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 6:
                this.orb1.showModel = false;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = true;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = true;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 7:
                this.orb1.showModel = true;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = true;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = true;
                this.orb9.showModel = false;
                this.orb10.showModel = false;
                break;
            case 8:
                this.orb1.showModel = true;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = true;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = true;
                this.orb9.showModel = true;
                this.orb10.showModel = false;
                break;
            case 9:
                this.orb1.showModel = true;
                this.orb2.showModel = false;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = true;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = true;
                this.orb9.showModel = true;
                this.orb10.showModel = true;
                break;
            case 10:
                this.orb1.showModel = true;
                this.orb2.showModel = true;
                this.orb3.showModel = true;
                this.orb4.showModel = true;
                this.orb5.showModel = true;
                this.orb6.showModel = true;
                this.orb7.showModel = true;
                this.orb8.showModel = true;
                this.orb9.showModel = true;
                this.orb10.showModel = true;
                break;
        }
    }
}
