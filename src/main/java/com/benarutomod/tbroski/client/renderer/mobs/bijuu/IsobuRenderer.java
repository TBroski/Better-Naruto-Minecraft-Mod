package com.benarutomod.tbroski.client.renderer.mobs.bijuu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.mobs.bijuu.IsobuEntity;
import com.benarutomod.tbroski.entity.models.bijuu.IsobuModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class IsobuRenderer extends MobRenderer<IsobuEntity, IsobuModel<IsobuEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/bijuu/isobu.png");

    public IsobuRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new IsobuModel<>(), 2F);
    }

    @Override
    public ResourceLocation getEntityTexture(IsobuEntity entity) {
        return TEXTURE;
    }
}
