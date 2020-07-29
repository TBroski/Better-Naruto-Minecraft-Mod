package com.benarutomod.tbroski.client.renderer.projectile;

import com.benarutomod.tbroski.entity.projectile.ExplosiveKunaiEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ExplosiveKunaiRenderer extends SpriteRenderer<ExplosiveKunaiEntity> {

    public ExplosiveKunaiRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<ExplosiveKunaiEntity> {

        @Override
        public EntityRenderer<? super ExplosiveKunaiEntity> createRenderFor(EntityRendererManager manager) {
            return new ExplosiveKunaiRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
