package com.benarutomod.tbroski.client.gui.overlay;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Notifications {

    private static final ResourceLocation NOTIFS = new ResourceLocation(Main.MODID + ":textures/gui/notifs.png");

    private List<BeNMJutsu> toggledJutsu = new ArrayList<>();
    private int jutsuNotification1 = -1;
    private int jutsuNotification2 = -1;
    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Minecraft mc = Minecraft.getInstance();
            mc.textureManager.bindTexture(NOTIFS);
            ClientPlayerEntity player = mc.player;
            LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());
            if (player_cap.returnHandInfusionToggled())
            {
                mc.ingameGUI.blit(5, 8, 0,0, 16, 16);
            }
            if (player_cap.returnBodyInfusionToggled())
            {
                mc.ingameGUI.blit(5, 2, 0, 32, 16, 24);
            }
            if (player_cap.returnLegInfusionToggled())
            {
                mc.ingameGUI.blit(5,20,0,16,16,16);
            }
            this.setJutsuNotifications(player);
            int i = 30;
            for (BeNMJutsu toggledJutsu : this.toggledJutsu) {
                mc.textureManager.bindTexture(toggledJutsu.getResourceLocationForGUI());
                mc.ingameGUI.blit(5, i, toggledJutsu.getU(), toggledJutsu.getV(), 16, 16);
                i += 20;
            }
            this.toggledJutsu.clear();
        }
    }

    private void setJutsuNotifications(PlayerEntity playerIn)
    {
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            if (jutsu.isToggle()) {
                String nbtName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
                if (playerIn.getPersistentData().getBoolean(nbtName)) {
                    this.toggledJutsu.add(jutsu);
                }
            }
        }
    }
}
