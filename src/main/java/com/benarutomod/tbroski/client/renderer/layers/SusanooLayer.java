package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMSharingan;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.layers.models.susanoo.ModelRibCage;
import com.benarutomod.tbroski.client.renderer.layers.models.susanoo.ModelSasukeSusanoo;
import com.benarutomod.tbroski.client.renderer.layers.models.susanoo.ModelSkeleton;
import com.benarutomod.tbroski.client.renderer.layers.models.susanoo.ModelSusanoo;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import com.benarutomod.tbroski.util.helpers.TextureHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.fonts.providers.TextureGlyphProvider;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

public class SusanooLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private ModelRibCage stage_1 = new ModelRibCage();
    private ModelSkeleton stage_2 = new ModelSkeleton();

    private ModelSasukeSusanoo sasuke = new ModelSasukeSusanoo();

    public SusanooLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        if (entitylivingbaseIn instanceof PlayerEntity && entitylivingbaseIn.getPersistentData().getBoolean(Main.MODID + "_susanoo")) {
            PlayerEntity player = (PlayerEntity) entitylivingbaseIn;
            IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            BeNMSharingan sharingan = DojutsuHelper.getBestSharinganByLevel(playercap);
            if (sharingan == null)
                return;
            float tick = player.getPersistentData().getInt(Main.MODID + "_susanoo_tick") * sharingan.getLevel();
            ModelSusanoo susanoo = stage_1;
            if (tick > Config.COMMON.susanooStageIncrement.get()) {
                susanoo = stage_2;
            }
            if (tick > Config.COMMON.susanooStageIncrement.get() * 2) {
                susanoo = sasuke;
            }
            Color color = TextureHelper.getColorFromColorChakra(playercap.returncolorChakra());
            matrixStackIn.push();
            matrixStackIn.translate(0,0.8,0);
            this.getEntityModel().copyModelAttributesTo(susanoo);
            susanoo.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(bufferIn, susanoo.getRenderType(susanoo.getSusanooTexture(player)), false, false);
            susanoo.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, color.getRed() / 256F, color.getGreen() / 256F, color.getBlue() / 256F, 0.3F);
            matrixStackIn.pop();

            if (susanoo == sasuke) {
                matrixStackIn.push();
                susanoo.manipulateItemToRender(matrixStackIn, playercap.getSusanooMainHand(), Hand.MAIN_HAND);
                matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-90.0F));
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(185.0F));
                matrixStackIn.translate(0.05, 0.0, -0.3); // 0.05 x
                Minecraft.getInstance().getItemRenderer().renderItem(playercap.getSusanooMainHand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
                matrixStackIn.pop();

                matrixStackIn.push();
                susanoo.manipulateItemToRender(matrixStackIn, playercap.getSusanooOffHand(), Hand.OFF_HAND);
                matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-70.0F));
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-5.0F));
                //matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(45.0F));
                matrixStackIn.translate(0.05, 0.0, 0.15); //0.05, 0.0, -0.15
                Minecraft.getInstance().getItemRenderer().renderItem(playercap.getSusanooOffHand(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
                matrixStackIn.pop();
            }
        }
    }
}
