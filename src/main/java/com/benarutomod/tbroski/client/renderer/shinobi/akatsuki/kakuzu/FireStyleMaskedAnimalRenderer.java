package com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.kakuzu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.entity.models.FireWindStyleMaskedAnimalModel;
import com.benarutomod.tbroski.entity.models.LightningStyleMaskedAnimalModel;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.FireStyleMaskedAnimalEntity;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.LightningStyleMaskedAnimalEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class FireStyleMaskedAnimalRenderer extends MobRenderer<FireStyleMaskedAnimalEntity, FireWindStyleMaskedAnimalModel<FireStyleMaskedAnimalEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/akatsuki/kakuzu/fire_style_masked_animal.png");
    public FireStyleMaskedAnimalRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FireWindStyleMaskedAnimalModel<>(), 0.5F);
        this.addLayer(new ClayLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(FireStyleMaskedAnimalEntity entity) {
        return TEXTURE;
    }
}
