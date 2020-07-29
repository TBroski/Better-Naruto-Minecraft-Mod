package com.benarutomod.tbroski.client.renderer.shinobi.shinobi;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.client.renderer.layers.DojutsuLayer;
import com.benarutomod.tbroski.entity.shinobi.shinobi.BasicSharinganEntity;
import com.benarutomod.tbroski.entity.shinobi.shinobi.ChuninEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class BasicSharinganRenderer extends MobRenderer<BasicSharinganEntity, PlayerModel<BasicSharinganEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/sharingan/king.png");


    public BasicSharinganRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<>(0.0f, false), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer(this, new BipedModel(0.5f), new BipedModel(1.0f)));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new ClayLayer<>(this));
        this.addLayer(new DojutsuLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(BasicSharinganEntity entity) {
        return TEXTURE;
    }
}