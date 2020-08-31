package com.benarutomod.tbroski.util.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class TextureHelper {

    private static final Color purple = new Color(160,32,240);

    public static ResourceLocation getResourceLocationFromEntity(Minecraft minecraftIn, LivingEntity entityIn) {
        EntityRendererManager rendererManager = minecraftIn.getRenderManager();
        if (rendererManager != null)
            return rendererManager.getRenderer(entityIn).getEntityTexture(entityIn);
        return DefaultPlayerSkin.getDefaultSkinLegacy();
    }

    public static Color getColorFromColorChakra(int colorChakra) {
        switch (colorChakra) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.GRAY;
            case 4:
                return purple;
            case 5:
                return Color.YELLOW;
        }
        return Color.BLUE;
    }
}
