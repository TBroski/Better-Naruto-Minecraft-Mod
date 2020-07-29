package com.benarutomod.tbroski.client.renderer.projectile.jutsu.earth;

import com.benarutomod.tbroski.entity.projectile.jutsu.earth.MudMoatEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MudMoatRenderer extends SpriteRenderer<MudMoatEntity> {

    public MudMoatRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn) {
        super(renderManagerIn, itemRendererIn);
    }

    public static class Factory implements IRenderFactory<MudMoatEntity> {

        @Override
        public EntityRenderer<? super MudMoatEntity> createRenderFor(EntityRendererManager manager) {
            return new MudMoatRenderer(manager, Minecraft.getInstance().getItemRenderer());
        }
    }
}
