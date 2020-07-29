package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.settings.GuiButtonScrollRenderer;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.settings.PacketToggleScrollBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class RenderSettings extends Screen {

    GuiButtonScrollRenderer guiButtonScrollRenderer;

    private int guiTop;
    private int guiLeft;
    private TextComponent guiTitle;

    protected RenderSettings() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.rendersettings"));
        this.guiTitle = new TranslationTextComponent("gui." + Main.MODID + ".title.rendersettings");
    }

    @Override
    protected void init() {
        buttons.clear();
        this.guiLeft = (this.width) / 2;
        this.guiTop = (this.height) / 2;

        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        addButton(guiButtonScrollRenderer = new GuiButtonScrollRenderer(this.guiLeft - 60, this.guiTop - 40, $ -> {
            this.onGuiButtonScrollRendererPress();
        }));
    }


    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/rendersettings.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        this.renderFonts();
        guiButtonScrollRenderer.renderButton(p_render_1_, p_render_2_, p_render_3_);
        this.checkHovered(p_render_1_, p_render_2_);
    }

    private void checkHovered(int p_render_1_, int p_render_2_) {
        if (guiButtonScrollRenderer.isHovered())
        {
            renderTooltip("Render Scroll (Only if you have Summoning Contract)", p_render_1_, p_render_2_);
        }
    }

    private void renderFonts() {
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);
    }

    private void onGuiButtonScrollRendererPress() {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (playerc.returnPlayerEntityAffiliation() != "") {
            if (playerc.returnToggleScrollRenderer()) {
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleScrollBoolean(false, false, player.getEntityId()));
                playerc.setToggleScrollRenderer(false);
            } else {
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleScrollBoolean(true, false, player.getEntityId()));
                playerc.setToggleScrollRenderer(true);
            }
        }
        else {
            player.sendMessage(new StringTextComponent("You need a Summoning Contract"));
        }
    }
}