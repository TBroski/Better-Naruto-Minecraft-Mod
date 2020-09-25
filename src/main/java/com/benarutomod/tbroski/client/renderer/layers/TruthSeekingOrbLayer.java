package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.ModelTruthSeekingOrbs;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class TruthSeekingOrbLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private ModelTruthSeekingOrbs truthSeekingOrbs = new ModelTruthSeekingOrbs();
    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/truth_seeking_orbs.png");

    public TruthSeekingOrbLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

            if (playerc.getTruthSeekingOrbAmount() > 0) {
                this.truthSeekingOrbs.setNumberOfOrbsToRender(playerc.getTruthSeekingOrbAmount());
                matrixStackIn.push();
                this.getEntityModel().copyModelAttributesTo(this.truthSeekingOrbs);
                this.truthSeekingOrbs.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                IVertexBuilder iVertexBuilder = ItemRenderer.getBuffer(bufferIn, this.truthSeekingOrbs.getRenderType(TEXTURE), false, false);
                this.truthSeekingOrbs.render(matrixStackIn, iVertexBuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStackIn.pop();
            }
        }
    }
}
