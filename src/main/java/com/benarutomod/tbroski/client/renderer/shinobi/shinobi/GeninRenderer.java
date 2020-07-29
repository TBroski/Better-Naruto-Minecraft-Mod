package com.benarutomod.tbroski.client.renderer.shinobi.shinobi;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.entity.shinobi.shinobi.GeninEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class GeninRenderer extends MobRenderer<GeninEntity, PlayerModel<GeninEntity>> {

    public static final ResourceLocation[] TEXTURES =
    {
            new ResourceLocation(Main.MODID, "textures/entity/genin/steve.png"),
            new ResourceLocation(Main.MODID, "textures/entity/genin/8pxpig.png"),
    };


    public GeninRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<>(0.0f, false), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer(this, new BipedModel(0.5f), new BipedModel(1.0f)));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new ClayLayer<>(this));
    }
    @Override
    public ResourceLocation getEntityTexture(GeninEntity entity) {
        return TEXTURES[entity.getVariant()];
    }
}
