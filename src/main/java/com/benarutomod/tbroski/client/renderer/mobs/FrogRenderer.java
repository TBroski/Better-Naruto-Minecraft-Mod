package com.benarutomod.tbroski.client.renderer.mobs;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.HoodLayer;
import com.benarutomod.tbroski.entity.mobs.FrogEntity;
import com.benarutomod.tbroski.entity.models.FrogModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FrogRenderer extends MobRenderer<FrogEntity, FrogModel<FrogEntity>> {

    public static final ResourceLocation[] TEXTURES =
            {
                    new ResourceLocation(Main.MODID, "textures/entity/frog/green.png"),
                    new ResourceLocation(Main.MODID, "textures/entity/frog/blue.png"),
                    new ResourceLocation(Main.MODID, "textures/entity/frog/red.png"),
                    new ResourceLocation(Main.MODID, "textures/entity/frog/orange.png"),
                    new ResourceLocation(Main.MODID, "textures/entity/frog/purple.png"),
            };

    public FrogRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FrogModel<>(), 0.05F);
        this.addLayer(new HoodLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(FrogEntity entity) {
        return TEXTURES[entity.getVariant()];
    }
}
