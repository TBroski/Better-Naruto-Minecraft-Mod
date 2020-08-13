package com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.kakuzu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.entity.models.kakuzu.WaterStyleMaskedAnimalModel;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.WaterStyleMaskedAnimalEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WaterStyleMaskedAnimalRenderer extends MobRenderer<WaterStyleMaskedAnimalEntity, WaterStyleMaskedAnimalModel<WaterStyleMaskedAnimalEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/akatsuki/kakuzu/water_style_masked_animal.png");
    public WaterStyleMaskedAnimalRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WaterStyleMaskedAnimalModel<>(), 0.5F);
        this.addLayer(new ClayLayer<>(this));
    }
    @Override
    public ResourceLocation getEntityTexture(WaterStyleMaskedAnimalEntity entity) {
        return TEXTURE;
    }
}
