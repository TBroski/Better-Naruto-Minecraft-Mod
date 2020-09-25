package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.*;
import com.benarutomod.tbroski.client.gui.player.jutsu.nature.*;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.client.gui.widgets.symbols.GuiButtonSymbol;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerJutsuScreen extends AbstractTabedBackground {

    GuiButtonSymbol eSymbol;

    GuiButtonSymbol fireSymbol;
    GuiButtonSymbol earthSymbol;
    GuiButtonSymbol lightningSymbol;
    GuiButtonSymbol windSymbol;
    GuiButtonSymbol waterSymbol;

    GuiButtonSymbol magnetSymbol;
    GuiButtonSymbol woodSymbol;
    GuiButtonSymbol lavaSymbol;
    GuiButtonSymbol boilSymbol;
    GuiButtonSymbol iceSymbol;
    GuiButtonSymbol stormSymbol;
    GuiButtonSymbol scorchSymbol;
    GuiButtonSymbol explosionSymbol;

    protected PlayerJutsuScreen() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
    }

    @Override
    public void registerTabsAndWidgets(IPlayerHandler playerCapability) {
        //Tabs
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 48, 240, 0, "Basic Jutsu", $ -> {
            openedTab = 0;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(1), this.getHeightInFromTab(1), 80, 240, 1, "Basic Nature Transformations", $ -> {
            openedTab = 1;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(2), this.getHeightInFromTab(2), 64, 240, 2, "Kekkai Genkai", $ -> {
            openedTab = 2;
        }));

        //Page 0
        addButton(eSymbol = new GuiButtonSymbol(this.guiLeft - 5, this.guiTop - 20, 10, 0, $ -> {
            if (playerCapability.hasChakraBoolean()) {
                Minecraft.getInstance().displayGuiScreen(new ERankJutsuScreen());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Chakra."));
            }
        }));

        //Page 1
        addButton(fireSymbol = new GuiButtonSymbol(this.guiLeft - 5, this.guiTop - 30, 0, 10, $ -> {
            if (playerCapability.hasFireNature()) {
                Minecraft.getInstance().displayGuiScreen(new FireNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Fire Nature."));
            }
        }));
        addButton(waterSymbol = new GuiButtonSymbol(this.guiLeft - 20, this.guiTop - 17, 0, 30, $ -> {
            if (playerCapability.hasWaterNature()) {
                Minecraft.getInstance().displayGuiScreen(new WaterNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Water Nature."));
            }
        }));
        addButton(earthSymbol = new GuiButtonSymbol(this.guiLeft - 15, this.guiTop, 0, 0, $ -> {
            if (playerCapability.hasEarthNature()) {
                Minecraft.getInstance().displayGuiScreen(new EarthNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Earth Nature."));
            }
        }));
        addButton(lightningSymbol = new GuiButtonSymbol(this.guiLeft + 5, this.guiTop, 0, 20, $ -> {
            if (playerCapability.hasLightningNature()) {
                Minecraft.getInstance().displayGuiScreen(new LightningNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Lightning Nature."));
            }
        }));
        addButton(windSymbol = new GuiButtonSymbol(this.guiLeft + 10, this.guiTop - 17, 0, 40, $ -> {
            if (playerCapability.hasWindNature()) {
                Minecraft.getInstance().displayGuiScreen(new WindNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Wind Nature."));
            }
        }));

        //Page 2
        addButton(magnetSymbol = new GuiButtonSymbol(this.guiLeft - 85, this.guiTop - 5, 0, 50, $ -> {
            if (playerCapability.hasMagnetNature()) {
                Minecraft.getInstance().displayGuiScreen(new MagnetNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Magnet Nature."));
            }
        }));
        addButton(woodSymbol = new GuiButtonSymbol(this.guiLeft - 65, this.guiTop - 5, 0, 60, $ -> {
            if (playerCapability.hasWoodNature()) {
                Minecraft.getInstance().displayGuiScreen(new WoodNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Wood Nature."));
            }
        }));
        addButton(lavaSymbol = new GuiButtonSymbol(this.guiLeft - 45, this.guiTop - 5, 0, 70, $ -> {
            if (playerCapability.hasLavaNature()) {
                Minecraft.getInstance().displayGuiScreen(new LavaNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Lava Nature."));
            }
        }));
        addButton(boilSymbol = new GuiButtonSymbol(this.guiLeft - 25, this.guiTop - 5, 0, 80, $ -> {
            if (playerCapability.hasBoilNature()) {
                Minecraft.getInstance().displayGuiScreen(new BoilNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Boil Nature."));
            }
        }));
        addButton(iceSymbol = new GuiButtonSymbol(this.guiLeft - 5, this.guiTop - 5, 0, 90, $ -> {
            if (playerCapability.hasIceNature()) {
                Minecraft.getInstance().displayGuiScreen(new IceNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Ice Nature."));
            }
        }));
        addButton(stormSymbol = new GuiButtonSymbol(this.guiLeft + 15, this.guiTop - 5, 0, 100, $ -> {
            if (playerCapability.hasStormNature()) {
                Minecraft.getInstance().displayGuiScreen(new StormNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Storm Nature."));
            }
        }));
        addButton(scorchSymbol = new GuiButtonSymbol(this.guiLeft + 35, this.guiTop - 5, 0, 110, $ -> {
            if (playerCapability.hasScorchNature()) {
                Minecraft.getInstance().displayGuiScreen(new ScorchNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Scorch Nature."));
            }
        }));
        addButton(explosionSymbol = new GuiButtonSymbol(this.guiLeft + 55, this.guiTop - 5, 0, 120, $ -> {
            if (playerCapability.hasExplosionNature()) {
                Minecraft.getInstance().displayGuiScreen(new ExplosionNatureJutsu());
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Explosion Nature."));
            }
        }));
    }

    @Override
    public void renderPage(int openedTab, int p_render_1_, int p_render_2_, float p_render_3_) {
        AbstractClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        if (openedTab != 0) {
            eSymbol.visible = false;
        }
        if (openedTab != 1) {
            fireSymbol.visible = false;
            earthSymbol.visible = false;
            windSymbol.visible = false;
            waterSymbol.visible = false;
            lightningSymbol.visible = false;
        }
        if (openedTab != 2) {
            magnetSymbol.visible = false;
            woodSymbol.visible = false;
            lavaSymbol.visible = false;
            boilSymbol.visible = false;
            iceSymbol.visible = false;
            stormSymbol.visible = false;
            scorchSymbol.visible = false;
            explosionSymbol.visible = false;
        }

        switch (openedTab) {
            case 0:
                eSymbol.visible = true;
                eSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                break;
            case 1:
                fireSymbol.visible = true;
                earthSymbol.visible = true;
                windSymbol.visible = true;
                waterSymbol.visible = true;
                lightningSymbol.visible = true;
                fireSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                earthSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                windSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                waterSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                lightningSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                break;
            case 2:
                magnetSymbol.visible = true;
                woodSymbol.visible = true;
                lavaSymbol.visible = true;
                boilSymbol.visible = true;
                iceSymbol.visible = true;
                stormSymbol.visible = true;
                scorchSymbol.visible = true;
                explosionSymbol.visible = true;
                magnetSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                woodSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                lavaSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                boilSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                iceSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                stormSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                scorchSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                explosionSymbol.renderButton(p_render_1_, p_render_2_, p_render_3_);
                break;
        }
    }
}
