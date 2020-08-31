package com.benarutomod.tbroski.client.gui.overlay;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChakraBar {

    private final ResourceLocation chakrabar = new ResourceLocation(Main.MODID + ":textures/gui/chakrabars.png");
    private final int tex_width = 9, tex_height = 102, bar_width = 7, bar_height = 100;


    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Post event) {

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

            ClientPlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<IPlayerHandler> chakra_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler chakra = chakra_cap.orElse(new PlayerCapability());
            if (chakra.returnmaxChakra() != 0) {
                Minecraft mc = Minecraft.getInstance();
                int color_x = ((chakra.returncolorChakra() * 8) + 9); // CORRECT FORMULA ((chakra.returncolorChakra() * 8) + 9)
                mc.textureManager.bindTexture(chakrabar);
                mc.ingameGUI.blit(20, 130, 0, 0, tex_width, tex_height);
                if (chakra.returnmaxChakra() <= 0) {
                    int set_height = tex_height;
                    mc.ingameGUI.blit(20, 130, color_x, 0, tex_width, set_height); // set_height
                } else {
                    float chakraratio = (chakra.returnChakra() / chakra.returnmaxChakra());
                    int set_height = (int) (tex_height * chakraratio);//(int) (bar_height * chakraratio)
                    int move_tex = (tex_height - set_height);
                    mc.ingameGUI.blit(20, 130 + move_tex, color_x, move_tex, tex_width, set_height); // set_height
                }

                //Byakugan Restriction Rendering
                if (player.getPersistentData().getInt("restrictedchakra") >= 1) {
                    int restrictedHeightRatio = (int) (tex_height * 0.75F);
                    int restrictedHeight = tex_height - restrictedHeightRatio;
                    mc.ingameGUI.blit(19, 130 + restrictedHeight, 0, 104, 11, 2);
                }
            }
        }
    }
}
