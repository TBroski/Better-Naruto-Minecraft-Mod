package com.benarutomod.tbroski.client.gui.overlay;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Notifications {

    private final ResourceLocation notifs = new ResourceLocation(Main.MODID + ":textures/gui/notifs.png");

    private int jutsuNotification1 = -1;
    private int jutsuNotification2 = -1;
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Minecraft mc = Minecraft.getInstance();
            mc.textureManager.bindTexture(notifs);
            AbstractClientPlayerEntity abstractClientPlayerEntity = mc.player;
            mc.textureManager.bindTexture(notifs);
            if (abstractClientPlayerEntity.getPersistentData().getBoolean("handinfusion") == true)
            {
                mc.ingameGUI.blit(5, 8, 0,0, 16, 16);
            }
            if (abstractClientPlayerEntity.getPersistentData().getBoolean("leginfusion") == true)
            {
                mc.ingameGUI.blit(5,20,0,16,16,16);
            }
            if (abstractClientPlayerEntity.getPersistentData().getBoolean("bodyinfusion") == true)
            {
                mc.ingameGUI.blit(5, 2, 0, 32, 16, 24);
            }
            this.setJutsuNotifications(abstractClientPlayerEntity);
            if (jutsuNotification1 != -1)
            {
                mc.ingameGUI.blit(5, 27, jutsuNotification1, 0, 16, 16);
            }
            if (jutsuNotification2 != -1)
            {
                mc.ingameGUI.blit(5, 45, jutsuNotification2,0, 16, 16);
            }
        }
    }

    private void setJutsuNotifications(PlayerEntity playerIn)
    {
        if (playerIn.getPersistentData().getBoolean("moltenfisttechnigue") == true)
        {
            if (jutsuNotification1 == -1 && jutsuNotification2 != 20)
            {
                jutsuNotification1 = 20;
            }
            else if (jutsuNotification2 == -1 && jutsuNotification1 != 20)
            {
                jutsuNotification2 = 20;
            }
        }
        else {
            if (jutsuNotification1 == 20)
            {
                jutsuNotification1 = -1;
            }
            else if (jutsuNotification2 == 20)
            {
                jutsuNotification2 = -1;
            }
        }

        if (playerIn.getPersistentData().getBoolean("fistrocktechnigue") == true)
        {
            if (jutsuNotification1 == -1 && jutsuNotification2 != 35)
            {
                jutsuNotification1 = 35;
            }
            else if (jutsuNotification2 == -1 && jutsuNotification1 != 35)
            {
                jutsuNotification2 = 35;
            }
        }
        else {
            if (jutsuNotification1 == 35)
            {
                jutsuNotification1 = -1;
            }
            else if (jutsuNotification2 == 35)
            {
                jutsuNotification2 = -1;
            }
        }
    }
}
