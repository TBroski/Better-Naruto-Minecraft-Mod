package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;


public abstract class TabedBackground extends Screen {

    private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/tabedbackground.png");


    private int guiLeft;
    private int guiTop;

    private int openedTab;
    private GuiButtonTab guiButtonTab0;
    private GuiButtonTab guiButtonTab1;
    private GuiButtonTab guiButtonTab2;
    private GuiButtonTab guiButtonTab3;
    private GuiButtonTab guiButtonTab4;
    private GuiButtonTab guiButtonTab5;

    public abstract ArrayList<GuiButtonTab> getTabs();

    protected TabedBackground(ITextComponent titleIn) {
        super(titleIn);
    }



    @Override
    protected void init() {
        buttons.clear();

        this.guiLeft = this.width / 2;
        this.guiTop = this.height / 2;

        int tabSize = getTabs().size();
        if (tabSize > 6) {
            System.out.println("ERROR: Too many tabs in." + getTabs());
        }

        for (GuiButtonTab tab : getTabs()) {
            if (tabSize > 0 && tab == getTabs().get(0)) {
                addButton(guiButtonTab0 = tab);
            }
            if (tabSize > 1 && tab == getTabs().get(1)) {
                addButton(guiButtonTab1 = tab);
            }
            if (tabSize > 2 && tab == getTabs().get(2)) {
                addButton(guiButtonTab2 = tab);
            }
            if (tabSize > 3 && tab == getTabs().get(3)) {
                addButton(guiButtonTab3 = tab);
            }
            if (tabSize > 4 && tab == getTabs().get(4)) {
                addButton(guiButtonTab4 = tab);
            }
            if (tabSize > 5 && tab == getTabs().get(5)) {
                addButton(guiButtonTab5 = tab);
            }
        }
        super.init();
    }


    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        mc.textureManager.bindTexture(MAIN_TEXTURE);
        mc.ingameGUI.blit(guiLeft - 84, guiTop - 55, 0, 63, 168, 111);

        for (GuiButtonTab tab : getTabs()) {
            tab.renderButton(p_render_1_, p_render_2_, p_render_3_);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }

    public int getWidthInFromTab(int tabNumber) {
        if (tabNumber > 5) {
            System.out.println("Too many tabs (Max 6, including 0). " + tabNumber);
            return 0;
        }
        return (this.guiLeft - 84) + tabNumber * 28;
    }


    public int getHeightInFromTab(int tabNumber) {
        return guiTop - 86;
    }
}
