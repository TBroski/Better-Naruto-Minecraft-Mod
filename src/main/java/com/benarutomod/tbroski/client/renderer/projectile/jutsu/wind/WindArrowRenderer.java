package com.benarutomod.tbroski.client.renderer.projectile.jutsu.wind;

import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class WindArrowRenderer extends SpriteRenderer<WindArrowEntity> {

    public WindArrowRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<WindArrowEntity> {

        @Override
        public EntityRenderer<? super WindArrowEntity> createRenderFor(EntityRendererManager manager) {
            return new WindArrowRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
