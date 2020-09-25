package com.benarutomod.tbroski.client.renderer.npc;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.client.renderer.layers.DojutsuLayer;
import com.benarutomod.tbroski.entity.npc.ClanHelperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class ClanHelperRenderer extends MobRenderer<ClanHelperEntity, PlayerModel<ClanHelperEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/clan_helper/old_guy.png");

    public ClanHelperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PlayerModel<>(0.0F, false), 0.1F);
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new BipedArmorLayer(this, new BipedModel(0.5F), new BipedModel(1.0f)));
        this.addLayer(new ElytraLayer<>(this));
        this.addLayer(new ClayLayer<>(this));
        this.addLayer(new DojutsuLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(ClanHelperEntity entity) {
        return TEXTURE;
    }
}
