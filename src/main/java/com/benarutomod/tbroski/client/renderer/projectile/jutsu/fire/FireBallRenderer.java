package com.benarutomod.tbroski.client.renderer.projectile.jutsu.fire;

import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class FireBallRenderer extends SpriteRenderer<FireballEntity> {

    public FireBallRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<FireballEntity> {

        @Override
        public EntityRenderer<? super FireballEntity> createRenderFor(EntityRendererManager manager) {
            return new FireBallRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
