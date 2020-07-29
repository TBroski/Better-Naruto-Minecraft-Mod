package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.ItachiRenderer;
import com.benarutomod.tbroski.client.renderer.shinobi.shinobi.BasicByakuganRenderer;
import com.benarutomod.tbroski.client.renderer.shinobi.shinobi.BasicSharinganRenderer;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.ItachiEntity;
import com.benarutomod.tbroski.entity.shinobi.shinobi.BasicByakuganEntity;
import com.benarutomod.tbroski.entity.shinobi.shinobi.BasicSharinganEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class TextureHelper {

    public static ResourceLocation getResourceLocationFromEntity(Minecraft minecraftIn, LivingEntity entityIn) {
        Map<EntityType<?>, EntityRenderer<?>> entityMap = minecraftIn.getRenderManager().renderers;
        for (Map.Entry<EntityType<?>, EntityRenderer<?>> renderer : entityMap.entrySet())
        {
            if (renderer.getValue() instanceof BasicSharinganRenderer && entityIn instanceof BasicSharinganEntity) {
                BasicSharinganEntity entity = (BasicSharinganEntity) entityIn;
                return ((BasicSharinganRenderer) renderer.getValue()).getEntityTexture(entity);
            }
            if (renderer.getValue() instanceof BasicByakuganRenderer && entityIn instanceof BasicByakuganEntity) {
                BasicByakuganEntity entity = (BasicByakuganEntity) entityIn;
                return ((BasicByakuganRenderer) renderer.getValue()).getEntityTexture(entity);
            }
            if (renderer.getValue() instanceof ItachiRenderer && entityIn instanceof ItachiEntity) {
                ItachiEntity entity = (ItachiEntity) entityIn;
                return ((ItachiRenderer) renderer.getValue()).getEntityTexture(entity);
            }
        }
        return null;
    }
}
