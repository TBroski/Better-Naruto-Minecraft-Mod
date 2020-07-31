package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonTab;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowDown;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowUp;
import com.benarutomod.tbroski.common.BeNMBody;
import com.benarutomod.tbroski.common.jutsu.ShootingJutsu;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.PacketPlayerBodyModeSync;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraControlSync;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketSetJutsuBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class PlayerBody extends AbstractTabedBackground {

    private ArrayList<GuiButtonTab> tabs = new ArrayList<>();

    private GuiButtonArrowUp chakraControlUp;
    private GuiButtonArrowDown setBodyMode;

    private GuiButtonJutsu curseMark;
    private GuiButtonJutsu toadSage;
    private String bodyToggle = "";
    private BeNMBody currentBodyMode = BodyInit.NULL;

    protected PlayerBody() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
    }

    @Override
    public void registerTabsAndWidgets(IPlayerHandler playerCapability) {
        //Tabs
        addButton(new GuiButtonTab(this.getWidthInFromTab(0), this.getHeightInFromTab(0), 0, 240, 0, "Chakra Control", $ -> {
            openedTab = 0;
        }));
        addButton(new GuiButtonTab(this.getWidthInFromTab(1), this.getHeightInFromTab(1), 16, 240, 1, "Body Mode Selection", $ -> {
            openedTab = 1;
        }));

        //Page 1
        addButton(chakraControlUp = new GuiButtonArrowUp(this.guiLeft + 50, this.guiTop + 30, false, $ -> {
            chakraControlUpPressed();
        }));

        //Page 2
        addButton(setBodyMode = new GuiButtonArrowDown(this.guiLeft - 11, this.guiTop + 40, false, $ -> {
            playerCapability.setPlayerBodyMode(currentBodyMode);
            NetworkLoader.INSTANCE.sendToServer(new PacketPlayerBodyModeSync(playerCapability.returnPlayerBodyMode().getString(), false));
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("Body Mode set to: " + new TranslationTextComponent(this.bodyToggle).getString()));
        }));
        addButton(curseMark = new GuiButtonJutsu(this.guiLeft - 100, this.guiTop - 40, 224, 0, "cursemarkmode", playerCapability.returnPlayerCurseMark() > 0, 0, $ -> {
            if (playerCapability.returnPlayerCurseMark() > 0)
            {
                this.currentBodyMode = BodyInit.CURSE_MARK_MODE;
                this.bodyToggle = "body." + Main.MODID + "." + curseMark.getName();
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Curse Mark Mode."));
            }
        }));
        addButton(toadSage = new GuiButtonJutsu(this.guiLeft - 80, this.guiTop - 40, 224, 17, "toadsagemode", playerCapability.returnPlayerToadSageMode() > 0, 0, $ -> {
            if (playerCapability.returnPlayerToadSageMode() > 0)
            {
                this.currentBodyMode = BodyInit.TOAD_SAGE_MODE;
                this.bodyToggle = "body." + Main.MODID + "." + toadSage.getName();
            }
            else {
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("You don't have Toad Sage Mode."));
            }
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
            curseMark.visible = false;
            toadSage.visible = false;
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
                curseMark.visible = true;
                toadSage.visible = true;
                setBodyMode.visible = true;
                curseMark.renderButton(p_render_1_, p_render_2_, p_render_3_);
                toadSage.renderButton(p_render_1_, p_render_2_, p_render_3_);
                setBodyMode.renderButton(p_render_1_, p_render_2_, p_render_3_);
                checkHovered(p_render_1_, p_render_2_, curseMark, toadSage);
                checkCovered(curseMark, toadSage);
                checkToggled(curseMark, toadSage);
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
                renderTooltip(new TranslationTextComponent("body." + Main.MODID + "." + button.getName()).getString(), p_render_1, p_render_2);
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
            if (this.bodyToggle.equalsIgnoreCase("body." + Main.MODID + "." + button.getName())) {
                mc.ingameGUI.blit(button.widthIn, button.heightIn, 240, 224, 16,16);
            }
        }
    }
}
