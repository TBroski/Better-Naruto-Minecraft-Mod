package com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.kakuzu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.entity.models.kakuzu.FireWindStyleMaskedAnimalModel;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.WindStyleMaskedAnimalEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WindStyleMaskedAnimalRenderer extends MobRenderer<WindStyleMaskedAnimalEntity, FireWindStyleMaskedAnimalModel<WindStyleMaskedAnimalEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/akatsuki/kakuzu/wind_style_masked_animal.png");
    public WindStyleMaskedAnimalRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FireWindStyleMaskedAnimalModel<>(), 0.5F);
        this.addLayer(new ClayLayer<>(this));
    }
    @Override
    public ResourceLocation getEntityTexture(WindStyleMaskedAnimalEntity entity) {
        return TEXTURE;
    }
}
