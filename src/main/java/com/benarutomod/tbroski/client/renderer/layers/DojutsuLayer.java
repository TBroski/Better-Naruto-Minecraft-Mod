package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.layers.models.dojutsu.ModelLeftEye;
import com.benarutomod.tbroski.client.renderer.layers.models.dojutsu.ModelRightEye;
import com.benarutomod.tbroski.api.internal.BeNMDojutsu;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.api.interfaces.IDojutsuEntity;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;

public class DojutsuLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
    private AgeableModel leftModel = new ModelLeftEye(0, 4);
    private AgeableModel rightModel = new ModelLeftEye(0, 4);

    public DojutsuLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
            if (playerc.returnBodyInfusionToggled()) {
                matrixStackIn.push();
                this.leftModel = findLeftEyeModel(playerc.returnplayerEyeSlot(), playerc.returnPlayerLeftDojutsu());
                this.rightModel = findRightEyeModel(playerc.returnplayerEyeSlot(), playerc.returnPlayerRightDojutsu());
                this.getEntityModel().copyModelAttributesTo(this.leftModel);
                this.getEntityModel().copyModelAttributesTo(this.rightModel);
                if (this.leftModel instanceof ModelLeftEye) ((ModelLeftEye) this.leftModel).setPartialTick(entitylivingbaseIn, partialTicks);
                if (this.rightModel instanceof ModelRightEye) ((ModelRightEye) this.rightModel).setPartialTick(entitylivingbaseIn, partialTicks);
                this.leftModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                this.rightModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                IVertexBuilder iVertexBuilderLeft = ItemRenderer.getBuffer(bufferIn, this.leftModel.getRenderType(playerc.returnPlayerLeftDojutsu().getResourceLocation()), false, false);
                if (DojutsuHelper.dojutsuNotNull(playerc.returnPlayerLeftDojutsu())) this.leftModel.render(matrixStackIn, iVertexBuilderLeft, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                IVertexBuilder iVertexBuilderRight = ItemRenderer.getBuffer(bufferIn, this.rightModel.getRenderType(playerc.returnPlayerRightDojutsu().getResourceLocation()), false, false);
                if (DojutsuHelper.dojutsuNotNull(playerc.returnPlayerRightDojutsu())) this.rightModel.render(matrixStackIn, iVertexBuilderRight, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStackIn.pop();
            }
        }
        else if (entitylivingbaseIn instanceof AbstractCloneEntity) {
            AbstractCloneEntity cloneEntity = (AbstractCloneEntity) entitylivingbaseIn;
            ClientPlayerEntity playerEntity = (ClientPlayerEntity) entitylivingbaseIn.world.getEntityByID(cloneEntity.getOwnerID());
            if (playerEntity != null) {
                IPlayerHandler playerc = playerEntity.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                if (playerc.returnBodyInfusionToggled()) {
                    this.leftModel = findLeftEyeModel(playerc.returnplayerEyeSlot(), playerc.returnPlayerLeftDojutsu());
                    this.rightModel = findRightEyeModel(playerc.returnplayerEyeSlot(), playerc.returnPlayerRightDojutsu());
                    matrixStackIn.push();
                    this.getEntityModel().copyModelAttributesTo(this.leftModel);
                    this.getEntityModel().copyModelAttributesTo(this.rightModel);
                    this.leftModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    this.rightModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                    IVertexBuilder iVertexBuilderLeft = ItemRenderer.getBuffer(bufferIn, this.leftModel.getRenderType(playerc.returnPlayerLeftDojutsu().getResourceLocation()), false, false);
                    if (DojutsuHelper.dojutsuNotNull(playerc.returnPlayerLeftDojutsu())) this.leftModel.render(matrixStackIn, iVertexBuilderLeft, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                    IVertexBuilder iVertexBuilderRight = ItemRenderer.getBuffer(bufferIn, this.rightModel.getRenderType(playerc.returnPlayerRightDojutsu().getResourceLocation()), false, false);
                    if (DojutsuHelper.dojutsuNotNull(playerc.returnPlayerRightDojutsu()))this.rightModel.render(matrixStackIn, iVertexBuilderRight, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
                    matrixStackIn.pop();
                }
            }
        }
        else if (entitylivingbaseIn instanceof IDojutsuEntity) {
            IDojutsuEntity dojutsuEntity = (IDojutsuEntity) entitylivingbaseIn;
            this.leftModel = findLeftEyeModel(dojutsuEntity.eyeSlot(), dojutsuEntity.leftDojustsu());
            this.rightModel = findRightEyeModel(dojutsuEntity.eyeSlot(), dojutsuEntity.rightDojustsu());
            matrixStackIn.push();
            this.getEntityModel().copyModelAttributesTo(this.leftModel);
            this.getEntityModel().copyModelAttributesTo(this.rightModel);
            this.leftModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.rightModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder iVertexBuilderLeft = ItemRenderer.getBuffer(bufferIn, this.leftModel.getRenderType(dojutsuEntity.leftDojustsu().getResourceLocation()), false, false);
            if (DojutsuHelper.dojutsuNotNull(dojutsuEntity.leftDojustsu())) this.leftModel.render(matrixStackIn, iVertexBuilderLeft, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            IVertexBuilder iVertexBuilderRight = ItemRenderer.getBuffer(bufferIn, this.rightModel.getRenderType(dojutsuEntity.rightDojustsu().getResourceLocation()), false, false);
            if (DojutsuHelper.dojutsuNotNull(dojutsuEntity.rightDojustsu())) this.rightModel.render(matrixStackIn, iVertexBuilderRight, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStackIn.pop();
        }
    }

    private AgeableModel findLeftEyeModel(int eyeSlot, BeNMDojutsu leftEye)
    {
        //return new ModelLeftEye(DojutsuHelper.dojutsuSize(leftEye), eyeSlot);
        return new ModelLeftEye(leftEye.getSize(), eyeSlot);
    }

    private AgeableModel findRightEyeModel(int eyeSlot, BeNMDojutsu rightEye)
    {
        return new ModelRightEye(rightEye.getSize(), eyeSlot);
    }
}