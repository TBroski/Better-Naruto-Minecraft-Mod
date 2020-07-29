package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.client.renderer.layers.models.ModelScroll;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;

public class ScrollLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
    private static final ResourceLocation resourcelocation = new ResourceLocation(Main.MODID, "textures/entity/layer/scroll.png");
    private ModelScroll model = new ModelScroll();
    private PlayerModel playerModel;

    public ScrollLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
        if (entityRendererIn instanceof PlayerRenderer) this.playerModel = ((PlayerRenderer) entityRendererIn).getEntityModel();
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
            if (playerc.returnToggleScrollRenderer()) {
                matrixStackIn.push();
                this.getEntityModel().copyModelAttributesTo(this.model);
                this.model.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(bufferIn, this.model.getRenderType(resourcelocation), false, false);
                this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStackIn.pop();
            }
        }
        else if (entitylivingbaseIn instanceof AbstractCloneEntity)
        {
            AbstractCloneEntity cloneEntity = (AbstractCloneEntity) entitylivingbaseIn;
            ClientPlayerEntity playerEntity = (ClientPlayerEntity) entitylivingbaseIn.world.getEntityByID(cloneEntity.getOwnerID());
            if (playerEntity != null) {
                IPlayerHandler playercap = playerEntity.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                if (playercap.returnToggleScrollRenderer()) {
                    matrixStackIn.push();
                    this.getEntityModel().copyModelAttributesTo(this.model);
                    this.model.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(bufferIn, this.model.getRenderType(resourcelocation), false, false);
                    this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                    matrixStackIn.pop();
                }
            }
        }
    }
}