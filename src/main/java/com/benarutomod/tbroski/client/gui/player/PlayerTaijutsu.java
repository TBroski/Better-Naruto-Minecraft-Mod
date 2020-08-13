package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowUp;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.PacketTaijutsu;
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

public class PlayerTaijutsu extends AbstractTabedBackground {

    Button taijutsuUp;
    TextComponent guiTitle;
    TextComponent availableTaijutsu;

    protected PlayerTaijutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.legtaijutsu"));
        this.availableTaijutsu = new TranslationTextComponent("gui." + Main.MODID + ".text.availabletaijutsu");
    }

    @Override
    public void registerTabsAndWidgets(IPlayerHandler playerCapability) {
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 96, 240, 0, "Taijutsu Statistics", $ -> {
            openedTab = 0;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(1), this.getHeightInFromTab(1), 112, 240, 1, "Eight Gates", $ -> {
            openedTab = 1;
        }));

        //Page 1
        addButton(taijutsuUp = new GuiButtonArrowUp(this.guiLeft + 50, this.guiTop + 30, false, $ -> {
            taijutsuUpPressed();
        }));
    }

    @Override
    public void renderPage(int openedTab, int p_render_1_, int p_render_2_, float p_render_3_) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (openedTab != 0) {
            taijutsuUp.visible = false;
        }

        switch (openedTab) {
            case 0:
                taijutsuUp.visible = true;
                font.drawString(new TranslationTextComponent("gui." + Main.MODID + ".legtaijutsu.taijutsu").getString() + playerc.returnTaijutsu(), this.guiLeft - 100, this.guiTop - 20, 0x453100);
                font.drawString("Cost 1 BeNM Point", this.guiLeft + 18, this.guiTop + 20, 0x453100);
                taijutsuUp.renderButton(p_render_1_, p_render_2_, p_render_3_);
                break;
        }
    }

    private void taijutsuUpPressed() {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (playerc.returnBeNMPoints() >= 1) {
            playerc.addBeNMPoints(-1);
            playerc.addTaijutsu(1);
            NetworkLoader.INSTANCE.sendToServer(new PacketBeNMPointsSync(playerc.returnBeNMPoints(), false));
            NetworkLoader.INSTANCE.sendToServer(new PacketTaijutsu(playerc.returnTaijutsu(), false));
        }
        else {
            player.sendMessage(new StringTextComponent("Need 1 BeNM Point"));
        }
    }
}
