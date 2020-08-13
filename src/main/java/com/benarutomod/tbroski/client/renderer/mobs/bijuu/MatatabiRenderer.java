package com.benarutomod.tbroski.client.renderer.mobs.bijuu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.mobs.bijuu.MatatabiEntity;
import com.benarutomod.tbroski.entity.models.bijuu.MatatabiModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MatatabiRenderer extends MobRenderer<MatatabiEntity, MatatabiModel<MatatabiEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/bijuu/matatabi.png");

    public MatatabiRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MatatabiModel<>(), 2F);
    }

    @Override
    public ResourceLocation getEntityTexture(MatatabiEntity entity) {
        return TEXTURE;
    }
}
