package com.benarutomod.tbroski.client.renderer.projectile.jutsu.wind;

import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindExplosionEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class WindExplosionRenderer extends SpriteRenderer<WindExplosionEntity> {

    public WindExplosionRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<WindExplosionEntity> {

        @Override
        public EntityRenderer<? super WindExplosionEntity> createRenderFor(EntityRendererManager manager) {
            return new WindExplosionRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
