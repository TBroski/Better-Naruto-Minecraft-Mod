package com.benarutomod.tbroski.client.renderer.projectile.jutsu;

import com.benarutomod.tbroski.api.entity.jutsu.AbstractJutsuEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class JutsuRenderer extends SpriteRenderer<AbstractJutsuEntity> {
    public JutsuRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<AbstractJutsuEntity> {

        @Override
        public EntityRenderer<? super AbstractJutsuEntity> createRenderFor(EntityRendererManager manager) {
            return new JutsuRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
