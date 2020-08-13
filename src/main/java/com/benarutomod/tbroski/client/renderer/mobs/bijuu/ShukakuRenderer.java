package com.benarutomod.tbroski.client.renderer.mobs.bijuu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.mobs.bijuu.ShukakuEntity;
import com.benarutomod.tbroski.entity.models.bijuu.ShukakuModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ShukakuRenderer extends MobRenderer<ShukakuEntity, ShukakuModel<ShukakuEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/bijuu/shukaku.png");

    public ShukakuRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ShukakuModel<>(), 2F);
    }

    @Override
    public ResourceLocation getEntityTexture(ShukakuEntity entity) {
        return TEXTURE;
    }
}
