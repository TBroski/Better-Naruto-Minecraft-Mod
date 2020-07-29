package com.benarutomod.tbroski.client.renderer.shinobi.akatsuki;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.DeidaraEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class DeidaraRenderer extends MobRenderer<DeidaraEntity, PlayerModel<DeidaraEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/akatsuki/deidara.png");
    public DeidaraRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<>(0.0f, false), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer(this, new BipedModel(0.5f), new BipedModel(1.0f)));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new ClayLayer<>(this));
    }
    @Override
    public ResourceLocation getEntityTexture(DeidaraEntity entity) {
        return TEXTURE;
    }
}
