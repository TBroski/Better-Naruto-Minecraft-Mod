package com.benarutomod.tbroski.client.renderer.projectile.jutsu.water;

import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterShurikenEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class WaterShurikenRenderer extends SpriteRenderer<WaterShurikenEntity> {

    public WaterShurikenRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<WaterShurikenEntity> {

        @Override
        public EntityRenderer<? super WaterShurikenEntity> createRenderFor(EntityRendererManager manager) {
            return new WaterShurikenRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
