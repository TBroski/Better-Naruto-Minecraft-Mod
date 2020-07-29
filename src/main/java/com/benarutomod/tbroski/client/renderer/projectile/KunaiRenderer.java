package com.benarutomod.tbroski.client.renderer.projectile;

import com.benarutomod.tbroski.entity.projectile.KunaiEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class KunaiRenderer extends SpriteRenderer<KunaiEntity> {

    public KunaiRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<KunaiEntity> {

        @Override
        public EntityRenderer<? super KunaiEntity> createRenderFor(EntityRendererManager manager) {
            return new KunaiRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }

    }
}
