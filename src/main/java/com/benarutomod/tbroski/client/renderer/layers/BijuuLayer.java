package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

public class BijuuLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    public BijuuLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn instanceof PlayerEntity && entitylivingbaseIn.getPersistentData().getBoolean(Main.MODID + "_tailed_beast_transformation")) {
            PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

            AbstractBijuuEntity bijuu = null;
            for (EntityType<?> entity : ForgeRegistries.ENTITIES.getValues()) {
                if (entity.getRegistryName().toString().equalsIgnoreCase(playerc.returnPlayerBijuu())) {
                    bijuu = (AbstractBijuuEntity) entity.create(Minecraft.getInstance().world);
                    break;
                }
            }
            if (bijuu != null) {
                EntityRenderer r = Minecraft.getInstance().getRenderManager().getRenderer(bijuu);
                if (r instanceof LivingRenderer) {
                    LivingRenderer l = (LivingRenderer) r;
                    EntityModel model = l.getEntityModel();
                    ResourceLocation texture = l.getEntityTexture(bijuu);
                    matrixStackIn.push();
                    matrixStackIn.translate(0.0, 0.0, 8.2);
                    this.getEntityModel().copyModelAttributesTo(model);
                    model.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    IVertexBuilder iVertexBuilder = ItemRenderer.getBuffer(bufferIn, model.getRenderType(texture), false, false);
                    model.render(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                    matrixStackIn.pop();
                }
            }
            else {
                System.out.println(player.getDisplayName() + " is using Tailed-Beast transformation without tailed beast.");
            }
        }
    }
}
