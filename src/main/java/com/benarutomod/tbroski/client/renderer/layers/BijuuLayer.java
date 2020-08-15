package com.benarutomod.tbroski.client.renderer.layers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
                bijuu.rotationYawHead = netHeadYaw;
                bijuu.rotationPitch = headPitch;
                matrixStackIn.push();
                matrixStackIn.translate(0, 0.1, 0);
                Quaternion quaternion = new Quaternion(0, 180, 180, true); //Vector3f.ZP.rotationDegrees(180.0F)
                matrixStackIn.rotate(quaternion);
                EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
                rendererManager.renderEntityStatic(bijuu, 0,0,0, entitylivingbaseIn.rotationYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                matrixStackIn.pop();
            }
        }
    }
}
