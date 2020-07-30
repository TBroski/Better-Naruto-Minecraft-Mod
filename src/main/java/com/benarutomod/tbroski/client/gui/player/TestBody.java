package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowUp;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraControlSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.advancements.AdvancementsScreen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;

public class TestBody extends TabedBackground {

    private ArrayList<GuiButtonTab> tabs = new ArrayList<>();

    private GuiButtonArrowUp chakraControlUp;
    protected TestBody() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
    }

    @Override
    public void registerTabs() {
        //Tabs
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 0, 240, 0, "Chakra Control", $ -> {
            openedTab = 0;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(1), this.getHeightInFromTab(1), 16, 240, 1, "Body Mode", $ -> {
            openedTab = 1;
        }));

        //Page 1
        addButton(chakraControlUp = new GuiButtonArrowUp(this.guiLeft + 50, this.guiTop + 30, false, $ -> {
            chakraControlUpPressed();
        }));
    }

    @Override
    public void renderPage(int openedTab, int p_render_1_, int p_render_2_, float p_render_3_) {
        AbstractClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        if (openedTab != 0) {
            chakraControlUp.visible = false;
        }
        switch (openedTab) {
            case 0:
                chakraControlUp.visible = true;
                chakraControlUp.renderButton(p_render_1_, p_render_2_, p_render_3_);
                font.drawStringWithShadow("Statistics", this.guiLeft - 70, this.guiTop - 50, 0x453100);
                font.drawString("Cost 2 BeNM Points", this.guiLeft + 30, this.guiTop + 20, 0x453100);
                font.drawString("Chakra Control Level = " + (int) playerc.returnChakraControl(), this.guiLeft - 95, this.guiTop - 35, 0x453100);
                font.drawString("Jutsu Cost = Jutsu Cost * 0." + (int) -(100 - playerc.returnChakraControl()), this.guiLeft - 95, this.guiTop - 25, 0x453100);
                break;
        }
    }

    private void chakraControlUpPressed() {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (playerc.returnBeNMPoints() >= 2) {
            playerc.addBeNMPoints(-2);
            playerc.addChakraControl(+0.5F);
            player.sendMessage(new StringTextComponent("+0.5 Chakra Control Total: " + playerc.returnChakraControl()));
        }
        else  {
            player.sendMessage(new StringTextComponent("Not Enough BeNM Points (Need 2)"));
        }
        NetworkLoader.INSTANCE.sendToServer(new PacketBeNMPointsSync(playerc.returnBeNMPoints(), false));
        NetworkLoader.INSTANCE.sendToServer(new PacketChakraControlSync(playerc.returnChakraControl(), false));
    }
}
