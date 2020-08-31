package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.IFaceBodyMode;
import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.ModelCurseMarkWings;
import com.benarutomod.tbroski.client.renderer.layers.models.dojutsu.ModelLeftEye;
import com.benarutomod.tbroski.client.renderer.layers.models.dojutsu.ModelRightEye;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.BeaconTileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.opengl.GL11;

public class BodyModeLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    public BodyModeLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

            if (playerc.returnBodyInfusionToggled()) {
                if (playerc.returnPlayerBodyMode() != null) {
                    if (playerc.returnPlayerBodyMode().getModelOnRender() != null) {
                        if (playerc.returnPlayerBodyMode().getModelOnRender() instanceof IFaceBodyMode) {
                            ((IFaceBodyMode) playerc.returnPlayerBodyMode().getModelOnRender()).setPartialTick(entitylivingbaseIn, partialTicks);
                        }
                        AgeableModel model = playerc.returnPlayerBodyMode().getModelOnRender();

                        if (model instanceof BipedModel)
                            ((BipedModel) model).isSneak = entitylivingbaseIn.isCrouching();
                        this.getEntityModel().copyModelAttributesTo(model);
                        model.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        IVertexBuilder iVertexBuilder = ItemRenderer.getBuffer(bufferIn, model.getRenderType(playerc.returnPlayerBodyMode().getModelResourceLocationOnRender()), false, false);
                        if (playerc.returnPlayerBodyMode().getName().equalsIgnoreCase("bijuu_mode")) {
                            AbstractBijuuEntity bijuu = null;
                            for (EntityType<?> entity : ForgeRegistries.ENTITIES.getValues()) {
                                if (entity.getRegistryName().toString().equalsIgnoreCase(playerc.returnPlayerBijuu())) {
                                    bijuu = (AbstractBijuuEntity) entity.create(Minecraft.getInstance().world);
                                    break;
                                }
                            }
                            iVertexBuilder = ItemRenderer.getBuffer(bufferIn, model.getRenderType(playerc.returnPlayerBodyMode().getModelResourceLocationOnRender()), false, true);
                            matrixStackIn.push();
                            model.render(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, bijuu.getChakraColor().getChakraColor().getRed() / 256F + 0.1F, bijuu.getChakraColor().getChakraColor().getGreen() / 256F + 0.1F, bijuu.getChakraColor().getChakraColor().getBlue() / 256F, 0.5F);
                        }
                        else {
                            matrixStackIn.push();
                            model.render(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                        }
                        matrixStackIn.pop();
                    }
                    if (playerc.returnPlayerBodyMode().getDojutsuSizeOnRender() != -1) {
                        matrixStackIn.push();
                        ModelLeftEye leftModel = new ModelLeftEye(playerc.returnPlayerBodyMode().getDojutsuSizeOnRender(), playerc.returnplayerEyeSlot() - playerc.returnPlayerBodyMode().getDojutsuOffsetOnRender());
                        ModelRightEye rightModel = new ModelRightEye(playerc.returnPlayerBodyMode().getDojutsuSizeOnRender(), playerc.returnplayerEyeSlot() - playerc.returnPlayerBodyMode().getDojutsuOffsetOnRender());
                        this.getEntityModel().copyModelAttributesTo(leftModel);
                        this.getEntityModel().copyModelAttributesTo(rightModel);
                        leftModel.setPartialTick(entitylivingbaseIn, partialTicks);
                        rightModel.setPartialTick(entitylivingbaseIn, partialTicks);
                        leftModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        rightModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                        IVertexBuilder iVertexBuilderLeft = ItemRenderer.getBuffer(bufferIn, leftModel.getRenderType(playerc.returnPlayerBodyMode().getDojutsuResourceLocationOnRender()), false, false);
                        leftModel.render(matrixStackIn, iVertexBuilderLeft, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                        IVertexBuilder iVertexBuilderRight = ItemRenderer.getBuffer(bufferIn, rightModel.getRenderType(playerc.returnPlayerBodyMode().getDojutsuResourceLocationOnRender()), false, false);
                        rightModel.render(matrixStackIn, iVertexBuilderRight, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                        matrixStackIn.pop();
                    }
                }
            }
        }
    }
}
