package com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.kakuzu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.entity.models.LightningStyleMaskedAnimalModel;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.LightningStyleMaskedAnimalEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class LightningStyleMaskedAnimalRenderer extends MobRenderer<LightningStyleMaskedAnimalEntity, LightningStyleMaskedAnimalModel<LightningStyleMaskedAnimalEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/akatsuki/kakuzu/lightning_style_masked_animal.png");
    public LightningStyleMaskedAnimalRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LightningStyleMaskedAnimalModel<>(), 0.5F);
        this.addLayer(new ClayLayer<>(this));
    }
    @Override
    public ResourceLocation getEntityTexture(LightningStyleMaskedAnimalEntity entity) {
        return TEXTURE;
    }
}
