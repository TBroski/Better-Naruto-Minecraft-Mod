package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.*;
import com.benarutomod.tbroski.client.gui.widgets.symbols.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerJutsu extends Screen {

    private int guiLeft;
    private int guiTop;
    TextComponent guiTitle;
    TextComponent availableJutsu;
    GuiButtonFireSymbol fireNature;
    GuiButtonWaterSymbol waterNature;
    GuiButtonEarthSymbol earthNature;
    GuiButtonLightningSymbol lightningNature;
    GuiButtonWindSymbol windNature;
    Button eRankJutsu;

    protected PlayerJutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.handjutsu"));
        this.guiTitle = new TranslationTextComponent("gui." + Main.MODID + ".title.handjutsu");
        this.availableJutsu = new TranslationTextComponent("gui." + Main.MODID + ".text.availablejutsu");
    }

    @Override
    protected void init() {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        buttons.clear();
        this.guiLeft = (this.width) / 2;
        this.guiTop = (this.height) / 2;

        addButton(eRankJutsu = new Button(this.guiLeft + 60 - (font.getStringWidth(this.availableJutsu.getString()) / 2), this.guiTop - 40, 70, 20, new StringTextComponent("E Rank Jutsu").getString(), $ -> {
            Minecraft.getInstance().displayGuiScreen(new ERankJutsu());
        }));
        addButton(fireNature = new GuiButtonFireSymbol(this.guiLeft - 60, this.guiTop - 13, $ -> {
            if (playerc.hasFireNature()) {
                Minecraft.getInstance().displayGuiScreen(new FireNatureJutsu());
            }
            else {
                player.sendMessage(new StringTextComponent("You don't have Fire Nature"));
            }
        }));
        addButton(waterNature = new GuiButtonWaterSymbol(this.guiLeft - 45, this.guiTop, $ -> {
            if (playerc.hasWaterNature()) {
                Minecraft.getInstance().displayGuiScreen(new WaterNatureJutsu());
            }
            else {
                player.sendMessage(new StringTextComponent("You don't have Water Nature"));
            }
        }));
        addButton(earthNature = new GuiButtonEarthSymbol(this.guiLeft - 75, this.guiTop, $ -> {
            if (playerc.hasEarthNature()) {
                Minecraft.getInstance().displayGuiScreen(new EarthNatureJutsu());
            }
            else {
                player.sendMessage(new StringTextComponent("You don't have Earth Nature"));
            }
        }));
        addButton(windNature = new GuiButtonWindSymbol(this.guiLeft - 68, this.guiTop + 15, $ -> {
            if (playerc.hasWindNature()) {
                Minecraft.getInstance().displayGuiScreen(new WindNatureJutsu());
            }
            else {
                player.sendMessage(new StringTextComponent("You don't have Wind Nature"));
            }
        }));
        addButton(lightningNature = new GuiButtonLightningSymbol(this.guiLeft - 52, this.guiTop + 15, $ -> {
            if (playerc.hasLightningNature()) {
                Minecraft.getInstance().displayGuiScreen(new LightningNatureJutsu());
            }
            else {
                player.sendMessage(new StringTextComponent("You don't have Lightning Nature"));
            }
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        AbstractClientPlayerEntity player = mc.player;

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        mc.textureManager.bindTexture(player.getLocationSkin());
        mc.ingameGUI.blit(this.guiLeft - 100, this.guiTop - 105, (44 * 4), (20 * 4), (4 * 4), (12 * 4));
        mc.ingameGUI.blit(this.guiLeft - 72, this.guiTop - 105, (44 * 4), (20 * 4), (4 * 4), (12 * 4));
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);
        font.drawStringWithShadow(this.availableJutsu.getString(), this.guiLeft + 40 - (font.getStringWidth(this.availableJutsu.getString()) / 2), this.guiTop - 90, 0x32cd32);
        eRankJutsu.renderButton(p_render_1_,p_render_2_,p_render_3_);
        fireNature.renderButton(p_render_1_, p_render_2_, p_render_3_);
        waterNature.renderButton(p_render_1_, p_render_2_, p_render_3_);
        lightningNature.renderButton(p_render_1_, p_render_2_, p_render_3_);
        earthNature.renderButton(p_render_1_, p_render_2_, p_render_3_);
        windNature.renderButton(p_render_1_, p_render_2_, p_render_3_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }
}
