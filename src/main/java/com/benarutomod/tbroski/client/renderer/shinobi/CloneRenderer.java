package com.benarutomod.tbroski.client.renderer.shinobi;

import com.benarutomod.tbroski.client.renderer.layers.ScrollLayer;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class CloneRenderer extends LivingRenderer<AbstractCloneEntity, PlayerModel<AbstractCloneEntity>> {

    public CloneRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<>(0.0f, false), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer(this, new BipedModel(0.5f), new BipedModel(1.0f)));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new ScrollLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(AbstractCloneEntity entity) {
        //return Minecraft.getInstance().player.getLocationSkin();
        return entity.getLocationSkin();
    }
}
