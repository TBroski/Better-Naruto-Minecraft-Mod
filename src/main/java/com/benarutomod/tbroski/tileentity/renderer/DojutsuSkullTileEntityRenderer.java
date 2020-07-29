package com.benarutomod.tbroski.tileentity.renderer;

import com.benarutomod.tbroski.client.renderer.layers.models.dojutsu.ModelLeftEye;
import com.benarutomod.tbroski.client.renderer.layers.models.dojutsu.ModelRightEye;
import com.benarutomod.tbroski.entity.shinobi.IDojutsuEntity;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import com.benarutomod.tbroski.util.helpers.TextureHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.entity.model.HumanoidHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DojutsuSkullTileEntityRenderer extends TileEntityRenderer<DojutsuSkullTileEntity> {
    public DojutsuSkullTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(DojutsuSkullTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockState blockstate = tileEntityIn.getBlockState();
        boolean flag = blockstate.getBlock() instanceof WallSkullBlock;
        Direction direction = flag ? blockstate.get(WallSkullBlock.FACING) : null;
        LivingEntity livingEntity = tileEntityIn.getLivingEntity();
        render(direction, matrixStackIn, bufferIn, combinedLightIn, livingEntity);
    }

    public static void render(@Nullable Direction directionIn, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int combinedLight, LivingEntity livingEntity) {
        GenericHeadModel genericheadmodel = new HumanoidHeadModel();
        matrixStackIn.push();
        if (directionIn == null) {
            matrixStackIn.translate(0.5D, 0.0D, 0.5D);
        } else {
            switch(directionIn) {
                case NORTH:
                    matrixStackIn.translate(0.5D, 0.25D, (double)0.74F);
                    break;
                case SOUTH:
                    matrixStackIn.translate(0.5D, 0.25D, (double)0.26F);
                    break;
                case WEST:
                    matrixStackIn.translate((double)0.74F, 0.25D, 0.5D);
                    break;
                case EAST:
                default:
                    matrixStackIn.translate((double)0.26F, 0.25D, 0.5D);
            }
        }
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        IVertexBuilder ivertexbuilder = buffer.getBuffer(getRenderType(livingEntity));
        genericheadmodel.render(matrixStackIn, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        if (livingEntity instanceof IDojutsuEntity) {
            IDojutsuEntity dojutsuEntity = (IDojutsuEntity) livingEntity;
            ModelLeftEye modelLeftEye = new ModelLeftEye(dojutsuEntity.leftDojustsu().getSize(), dojutsuEntity.eyeSlot() - 5);
            ModelRightEye modelRightEye = new ModelRightEye(dojutsuEntity.rightDojustsu().getSize(), dojutsuEntity.eyeSlot() - 5);
            if (directionIn == null) {
                matrixStackIn.translate(0.0D, 0.0D, -0.13D);
            } else {
                switch(directionIn) {
                    case NORTH:
                        matrixStackIn.translate(-0.5D, -0.25D, (double)-0.74F);
                        break;
                    case SOUTH:
                        matrixStackIn.translate(0.5D, 0.25D, (double)0.26F);
                        break;
                    case WEST:
                        matrixStackIn.translate((double)0.74F, 0.25D, 0.5D);
                        break;
                    case EAST:
                    default:
                        matrixStackIn.translate((double)0.26F, 0.25D, 0.5D);
                }
            }
            IVertexBuilder ivertexbuilderlefteye = buffer.getBuffer(modelLeftEye.getRenderType(dojutsuEntity.leftDojustsu().getResourceLocation()));
            IVertexBuilder ivertexbuilderrighteye = buffer.getBuffer(modelLeftEye.getRenderType(dojutsuEntity.leftDojustsu().getResourceLocation()));
            if (DojutsuHelper.dojutsuNotNull(dojutsuEntity.leftDojustsu())) modelLeftEye.render(matrixStackIn, ivertexbuilderlefteye, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            if (DojutsuHelper.dojutsuNotNull(dojutsuEntity.rightDojustsu())) modelRightEye.render(matrixStackIn, ivertexbuilderrighteye, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        matrixStackIn.pop();
    }

    private static RenderType getRenderType(LivingEntity livingEntity) {
        if (TextureHelper.getResourceLocationFromEntity(Minecraft.getInstance(), livingEntity) != null) {
            ResourceLocation resourcelocation = TextureHelper.getResourceLocationFromEntity(Minecraft.getInstance(), livingEntity);
            return RenderType.getEntityCutoutNoCull(resourcelocation);
        }
        return RenderType.getCutout();
    }
}
