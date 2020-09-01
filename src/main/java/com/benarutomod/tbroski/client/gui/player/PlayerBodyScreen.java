package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.body.BodyModeScreen;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowDown;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowUp;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraControlSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class PlayerBodyScreen extends AbstractTabedBackground {

    private ArrayList<GuiButtonTab> tabs = new ArrayList<>();

    private GuiButtonArrowUp chakraControlUp;
    private GuiButtonArrowDown setBodyMode;

    private String bodyToggle = "";
    private BeNMBody currentBodyMode = BodyInit.NULL;

    protected PlayerBodyScreen() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
    }

    @Override
    public void registerTabsAndWidgets(IPlayerHandler playerCapability) {
        //Tabs
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 0, 240, 0, "Chakra Control", $ -> {
            openedTab = 0;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(1), this.getHeightInFromTab(1), 16, 240, 1, "Body Mode Selection & Attributes", $ -> {
            openedTab = 1;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(2), this.getHeightInFromTab(2), 0, 240, 2, "Chakra Configuration", $ -> {
            openedTab = 2;
        }));

        //Page 1
        addButton(chakraControlUp = new GuiButtonArrowUp(this.guiLeft + 50, this.guiTop + 30, false, $ -> {
            chakraControlUpPressed();
        }));

        //Page 2
        addButton(setBodyMode = new GuiButtonArrowDown(this.guiLeft - 11, this.guiTop + 40, false, $ -> {
            Minecraft.getInstance().displayGuiScreen(new BodyModeScreen());
            // Uncomment these playerCapability.setPlayerBodyMode(currentBodyMode);
            //NetworkLoader.INSTANCE.sendToServer(new PacketPlayerBodyModeSync(playerCapability.returnPlayerBodyMode().getName(), Minecraft.getInstance().player.getEntityId(), false));
            //Minecraft.getInstance().player.sendMessage(new StringTextComponent("Body Mode set to: " + new TranslationTextComponent(this.bodyToggle).getString()));
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
        if (openedTab != 1) {
            setBodyMode.visible = false;
        }
        switch (openedTab) {
            case 0:
                chakraControlUp.visible = true;
                chakraControlUp.renderButton(p_render_1_, p_render_2_, p_render_3_);
                font.drawStringWithShadow("Statistics", this.guiLeft - 70, this.guiTop - 50, 0x453100);
                font.drawString("Cost 2 BeNM Points", this.guiLeft + 15, this.guiTop + 20, 0x453100);
                font.drawString("Chakra Control Level = " + playerc.returnChakraControl(), this.guiLeft - 105, this.guiTop - 35, 0x453100);
                font.drawString("Jutsu Cost = Jutsu Cost * 0." + (int) (100 - playerc.returnChakraControl()), this.guiLeft - 105, this.guiTop - 25, 0x453100);
                break;
            case 1:
                setBodyMode.visible = true;
                setBodyMode.renderButton(p_render_1_, p_render_2_, p_render_3_);
                if (playerc.returnPlayerBodyMode() != BodyInit.NULL) font.drawString("Selected Body Mode: " + new TranslationTextComponent("body." + playerc.returnPlayerBodyMode().getCorrelatedPlugin().getPluginId() + "." + playerc.returnPlayerBodyMode().getName()).getString(), this.guiLeft - (font.getStringWidth("Selected Body Mode: " + new TranslationTextComponent("body." + playerc.returnPlayerBodyMode().getCorrelatedPlugin().getPluginId() + "." + playerc.returnPlayerBodyMode().getName()).getString()) / 2), this.guiTop - 45, 0x453100);

                List<String> attribs = new ArrayList<>();
                if (playerc.returnPlayerBodyMode().getPlayerEffect() != null) {
                    attribs.add("Player Effect: " + playerc.returnPlayerBodyMode().getPlayerEffect().getDisplayName().getString());
                }
                if (playerc.returnPlayerBodyMode().getAttackingEffect() != null) {
                    attribs.add("Attacking Effect: " + playerc.returnPlayerBodyMode().getAttackingEffect().getDisplayName().getString());
                }
                if (playerc.returnPlayerBodyMode().allowsPlayerFlight()) {
                    attribs.add("Flight");
                }

                int i = -30;
                for (String attrib : attribs) {
                    font.drawString(attrib, this.guiLeft - 105, this.guiTop + i, 0x453100);
                    i += 15;
                }
                attribs.clear();
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

    public void checkHovered(int p_render_1, int p_render_2, GuiButtonJutsu... widgets)
    {
        for (GuiButtonJutsu button : widgets) {
            if (button.isHovered()) {
                renderTooltip(new TranslationTextComponent("body." + button.getTranslationName()).getString(), p_render_1, p_render_2);
            }
        }
    }

    public void checkCovered(GuiButtonJutsu... widgets)
    {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);
        for (GuiButtonJutsu button : widgets) {
            if (!button.hasJutsu()) {
                mc.ingameGUI.blit(button.widthIn, button.heightIn, 240, 240, 16, 16);
            }
        }
    }

    public void checkToggled(GuiButtonJutsu... widgets)
    {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);

        for (GuiButtonJutsu button : widgets) {
            if (this.bodyToggle.equalsIgnoreCase("body." + button.getTranslationName())) {
                mc.ingameGUI.blit(button.widthIn, button.heightIn, 240, 224, 16,16);
            }
        }
    }
}
