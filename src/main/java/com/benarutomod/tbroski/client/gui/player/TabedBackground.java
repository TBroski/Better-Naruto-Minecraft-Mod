package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.LazyOptional;


public abstract class TabedBackground extends Screen {

    private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/tabedbackground.png");

    public int guiLeft;
    public int guiTop;

    public int openedTab;

    public abstract void registerTabs();
    public abstract void renderPage(int openedTab, int p_render_1_, int p_render_2_, float p_render_3_);

    protected TabedBackground(ITextComponent titleIn) {
        super(titleIn);
    }



    @Override
    protected void init() {
        this.guiLeft = this.width / 2;
        this.guiTop = this.height / 2;
        registerTabs();

        int tabSize = 0;
        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                tabSize += 1;
            }
        }

        if (tabSize > 8) {
            System.out.println("ERROR: Too many tabs in. Tabs: " + buttons);
        }

        super.init();
    }


    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);

        boolean flag = true;
        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                if (((GuiButtonTab) tab).toggled) flag = false;
            }
        }
        if (flag) {
            for (Widget tab : this.buttons) {
                if (tab instanceof GuiButtonTab) {
                     ((GuiButtonTab) tab).toggled = true;
                     break;
                }
            }
        }

        mc.textureManager.bindTexture(MAIN_TEXTURE);
        mc.ingameGUI.blit(guiLeft - 112, guiTop - 73, 0, 63, 224, 145);

        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                if (!((GuiButtonTab) tab).toggled) tab.renderButton(p_render_1_, p_render_2_, p_render_3_);
            }
        }
        mc.ingameGUI.blit(guiLeft - 112, guiTop - 73, 0, 209, 224, 4);
        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                if (((GuiButtonTab) tab).toggled) tab.renderButton(p_render_1_, p_render_2_, p_render_3_);
            }
        }

        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                if (((GuiButtonTab) tab).getTab() == openedTab) {
                    font.drawStringWithShadow(((GuiButtonTab) tab).getToolTip(), this.guiLeft - (font.getStringWidth(((GuiButtonTab) tab).getToolTip()) / 2), this.guiTop - 63, 0x453100);
                }
            }
        }

        checkForToggled();
        checkForToolTip(p_render_1_, p_render_2_);
        renderPage(openedTab, p_render_1_, p_render_2_, p_render_3_);
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
        if (tabNumber > 7) {
            System.out.println("Too many tabs (Max 8, including 0). " + tabNumber);
            return 0;
        }
        return (this.guiLeft - 112) + tabNumber * 28;
    }

    public int getHeightInFromTab(int tabNumber) {
        return guiTop - 101;
    }

    private void checkForToggled() {
        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                if (((GuiButtonTab) tab).toggled && ((GuiButtonTab) tab).getTab() != openedTab) {
                    ((GuiButtonTab) tab).toggled = false;
                }
            }
        }
    }

    private void checkForToolTip(int p_render_1_, int p_render_2_) {
        for (Widget tab : this.buttons) {
            if (tab instanceof GuiButtonTab) {
                if (tab.isHovered()) {
                    renderTooltip(((GuiButtonTab) tab).getToolTip(), p_render_1_, p_render_2_);
                }
            }
        }
    }
}
