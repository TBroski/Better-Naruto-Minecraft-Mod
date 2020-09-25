package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMClanJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonClanConfiguration;
import com.benarutomod.tbroski.client.gui.widgets.symbols.GuiButtonClanCrest;
import com.benarutomod.tbroski.util.helpers.JutsuHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;
import java.util.List;

public class PlayerClanConfigurationScreen extends AbstractTabedBackground {

    private List<BeNMClan> clans = new ArrayList<>();
    private List<GuiButtonClanCrest> clanCrests = new ArrayList<>();

    protected PlayerClanConfigurationScreen() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.clan_config"));
    }

    @Override
    public void registerTabsAndWidgets(IPlayerHandler playerCapability) {
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 48, 240, 0, "Clan Jutsu", $ -> {
            openedTab = 0;
        }));

        int i = 1;
        int x = -80;
        int y = 80;
        for (BeNMClan clan : playerCapability.getLearntClans()) {
            if (clan.hasDedicatedTab()) {
                clans.add(clan);
                int finalI = i;
                addButton(new GuiButtonTab(this.getWidthInFromTab(i), this.getHeightInFromTab(i), 128, 240, i, clan.getString().substring(0,1).toUpperCase() + clan.getString().substring(1).toLowerCase(), $ -> {
                    openedTab = finalI;
                }));
                clan.registerWidgets(this, playerCapability);
                i++;
            }
            for (BeNMClanJutsu clanJutsu : JutsuHelper.getClanJutsus()) {
                if (clanJutsu.getClan() == clan) {
                    GuiButtonClanCrest clanCrest = new GuiButtonClanCrest(x, y, clan, p_onPress_1_ -> {

                    });
                    this.clanCrests.add(clanCrest);
                    addButton(clanCrest);
                }
            }
        }
    }

    @Override
    public void renderPage(int openedTab, int p_render_1_, int p_render_2_, float p_render_3_) {
        AbstractClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        int i = 1;
        for (BeNMClan clan : this.clans) {
            clan.renderGUI(this, p_render_1_, p_render_2_, p_render_3_, openedTab == i);
            i++;
        }

        for (GuiButtonClanCrest clanCrest : this.clanCrests) {
            clanCrest.renderButton(p_render_1_, p_render_2_, p_render_3_);
        }
    }
}
