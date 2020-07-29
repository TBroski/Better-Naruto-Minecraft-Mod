package com.benarutomod.tbroski.client.renderer.projectile.jutsu.water;

import com.benarutomod.tbroski.entity.projectile.jutsu.water.RagingWavesEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RagingWavesRenderer extends SpriteRenderer<RagingWavesEntity> {

    public RagingWavesRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<RagingWavesEntity> {

        @Override
        public EntityRenderer<? super RagingWavesEntity> createRenderFor(EntityRendererManager manager) {
            return new RagingWavesRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
