package com.benarutomod.tbroski.client.renderer;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.ClayEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class ClayRenderer extends LivingRenderer<ClayEntity, EntityModel<ClayEntity>> {

    private static final ResourceLocation POWER_0 = new ResourceLocation(Main.MODID, "textures/entity/layer/clay.png");
    private static final ResourceLocation POWER_1 = new ResourceLocation(Main.MODID, "textures/entity/layer/clay_c1.png");
    private static final ResourceLocation POWER_2 = new ResourceLocation(Main.MODID, "textures/entity/layer/clay_c2.png");
    private static final ResourceLocation POWER_3 = new ResourceLocation(Main.MODID, "textures/entity/layer/clay_c3.png");

    public ClayRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new PlayerModel<>(0F, false), 0.1F);
    }

    @Override
    public void render(ClayEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.getEntityToRepresent() == null)
            return;

        EntityRenderer renderer = Minecraft.getInstance().getRenderManager().getRenderer(entityIn.getEntityToRepresent());
        if (renderer instanceof LivingRenderer) {
            this.entityModel = ((LivingRenderer) renderer).getEntityModel();
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(ClayEntity entity) {
        switch (entity.getExplosionLevel()) {
            case 0:
                return POWER_0;
            case 1:
                return POWER_1;
            case 2:
                return POWER_2;
            case 3:
                return POWER_3;
        }
        return POWER_0;
    }
}
