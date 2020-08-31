package com.benarutomod.tbroski.client.renderer.mobs.bijuu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.mobs.bijuu.SonGokuEntity;
import com.benarutomod.tbroski.entity.models.bijuu.SonGokuModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SonGokuRenderer extends MobRenderer<SonGokuEntity, SonGokuModel<SonGokuEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/bijuu/son_goku.png");

    public SonGokuRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SonGokuModel<>(), 2F);
    }

    @Override
    public ResourceLocation getEntityTexture(SonGokuEntity entity) {
        return TEXTURE;
    }
}
