package com.benarutomod.tbroski.client.renderer.projectile.jutsu.fire;

import com.benarutomod.tbroski.entity.projectile.jutsu.fire.PhoenixFlowerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class PhoenixFlowerRenderer extends SpriteRenderer<PhoenixFlowerEntity> {

    public PhoenixFlowerRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<PhoenixFlowerEntity> {

        @Override
        public EntityRenderer<? super PhoenixFlowerEntity> createRenderFor(EntityRendererManager manager) {
            return new PhoenixFlowerRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
