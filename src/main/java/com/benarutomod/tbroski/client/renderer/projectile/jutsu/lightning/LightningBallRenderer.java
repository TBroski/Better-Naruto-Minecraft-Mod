package com.benarutomod.tbroski.client.renderer.projectile.jutsu.lightning;

import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningBallEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class LightningBallRenderer extends SpriteRenderer<LightningBallEntity> {

    public LightningBallRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<LightningBallEntity> {

        @Override
        public EntityRenderer<? super LightningBallEntity> createRenderFor(EntityRendererManager manager) {
            return new LightningBallRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
