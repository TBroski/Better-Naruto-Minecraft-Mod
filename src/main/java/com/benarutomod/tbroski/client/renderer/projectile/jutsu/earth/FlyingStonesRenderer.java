package com.benarutomod.tbroski.client.renderer.projectile.jutsu.earth;

import com.benarutomod.tbroski.entity.projectile.jutsu.earth.FlyingStonesEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class FlyingStonesRenderer extends SpriteRenderer<FlyingStonesEntity> {

    public FlyingStonesRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<FlyingStonesEntity> {

        @Override
        public EntityRenderer<? super FlyingStonesEntity> createRenderFor(EntityRendererManager manager) {
            return new FlyingStonesRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
