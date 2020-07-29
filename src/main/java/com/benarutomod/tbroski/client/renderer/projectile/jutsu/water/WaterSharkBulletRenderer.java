package com.benarutomod.tbroski.client.renderer.projectile.jutsu.water;

import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterSharkBulletEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class WaterSharkBulletRenderer extends SpriteRenderer<WaterSharkBulletEntity> {

    public WaterSharkBulletRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<WaterSharkBulletEntity> {

        @Override
        public EntityRenderer<? super WaterSharkBulletEntity> createRenderFor(EntityRendererManager manager) {
            return new WaterSharkBulletRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
