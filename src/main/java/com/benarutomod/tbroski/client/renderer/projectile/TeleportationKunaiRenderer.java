package com.benarutomod.tbroski.client.renderer.projectile;

import com.benarutomod.tbroski.entity.projectile.TeleportationKunaiEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TeleportationKunaiRenderer extends SpriteRenderer<TeleportationKunaiEntity> {

    public TeleportationKunaiRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<TeleportationKunaiEntity> {

        @Override
        public EntityRenderer<? super TeleportationKunaiEntity> createRenderFor(EntityRendererManager manager) {
            return new TeleportationKunaiRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
