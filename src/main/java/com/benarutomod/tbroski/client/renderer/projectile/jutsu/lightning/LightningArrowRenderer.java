package com.benarutomod.tbroski.client.renderer.projectile.jutsu.lightning;

import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningArrowEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class LightningArrowRenderer extends SpriteRenderer<LightningArrowEntity> {

    public LightningArrowRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<LightningArrowEntity> {

        @Override
        public EntityRenderer<? super LightningArrowEntity> createRenderFor(EntityRendererManager manager) {
            return new LightningArrowRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
