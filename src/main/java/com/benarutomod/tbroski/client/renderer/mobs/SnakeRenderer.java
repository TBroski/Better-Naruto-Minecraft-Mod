package com.benarutomod.tbroski.client.renderer.mobs;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.mobs.SnakeEntity;
import com.benarutomod.tbroski.entity.models.SnakeModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SnakeRenderer extends MobRenderer<SnakeEntity, SnakeModel<SnakeEntity>> {

    public static final ResourceLocation NORMAL = new ResourceLocation(Main.MODID, "textures/entity/snake/normal.png");
    public static final ResourceLocation CURSED = new ResourceLocation(Main.MODID, "textures/entity/snake/cursed.png");

    public SnakeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SnakeModel<>(), 0.05F);
    }

    @Override
    public ResourceLocation getEntityTexture(SnakeEntity entity) {
        if (entity.getCursed()) {
            return CURSED;
        }
        return NORMAL;
    }
}
