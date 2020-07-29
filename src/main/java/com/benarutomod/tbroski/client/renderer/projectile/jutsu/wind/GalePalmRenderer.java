package com.benarutomod.tbroski.client.renderer.projectile.jutsu.wind;

import com.benarutomod.tbroski.entity.projectile.jutsu.wind.GalePalmEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GalePalmRenderer extends SpriteRenderer<GalePalmEntity> {

    public GalePalmRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<GalePalmEntity> {

        @Override
        public EntityRenderer<? super GalePalmEntity> createRenderFor(EntityRendererManager manager) {
            return new GalePalmRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
