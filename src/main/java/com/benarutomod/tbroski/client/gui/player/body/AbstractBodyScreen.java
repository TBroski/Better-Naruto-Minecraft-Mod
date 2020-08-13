package com.benarutomod.tbroski.client.gui.player.body;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.GuiButtonArrowDown;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.init.BodyInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerBodyModeSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.lwjgl.opengl.GL11;

public abstract class AbstractBodyScreen extends Screen {

    private GuiButtonArrowDown setBodyMode;
    public BeNMBody currentBodyMode = BodyInit.NULL;
    public String bodyToggle = "";
    final ITextComponent guiTitle;
    public int guiLeft;
    public int guiTop;

    public abstract void registerBodyModes(IPlayerHandler playerCapability);
    public abstract void setBodyModeBooleans(IPlayerHandler playerCapability);

    protected AbstractBodyScreen(ITextComponent titleIn) {
        super(titleIn);
        this.guiTitle = titleIn;
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

        registerBodyModes(playerc);

        addButton(setBodyMode = new GuiButtonArrowDown(this.guiLeft - 10, this.guiTop + 70, false, $ -> {
            playerc.setPlayerBodyMode(currentBodyMode);
            NetworkLoader.INSTANCE.sendToServer(new PacketPlayerBodyModeSync(playerc.returnPlayerBodyMode().getName(), Minecraft.getInstance().player.getEntityId(), false));
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("Body Mode set to: " + new TranslationTextComponent(this.bodyToggle).getString()));
            this.bodyToggle = "";
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        LazyOptional<IPlayerHandler> player_cap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        setBodyModeBooleans(playerc);

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);

        for (Widget button : this.buttons) {
            button.renderButton(p_render_1_, p_render_2_, p_render_3_);
        }

        this.checkToggled();
        this.checkHovered(p_render_1_, p_render_2_);
        this.checkCovered();
    }


    public void checkHovered(int p_render_1_, int p_render_2_)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);
        GL11.glPushMatrix();
        for (Widget button : this.buttons) {
            if (button.isHovered() && button instanceof GuiButtonJutsu) {
                renderTooltip(new TranslationTextComponent("body." + ((GuiButtonJutsu) button).getTranslationName()).getString(), p_render_1_, p_render_2_);
            }
        }
        GL11.glPopMatrix();
    }

    public void checkCovered()
    {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);
        GL11.glPushMatrix();
        for (Widget button : this.buttons) {
            if (button instanceof GuiButtonJutsu) {
                if (!((GuiButtonJutsu) button).hasJutsu()) {
                    mc.ingameGUI.blit(((GuiButtonJutsu) button).widthIn, ((GuiButtonJutsu) button).heightIn, 240, 240, 16, 16);
                }
            }
        }
        GL11.glPopMatrix();
    }

    public void checkToggled()
    {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);
        GL11.glPushMatrix();
        for (Widget button : this.buttons) {
            if (button instanceof GuiButtonJutsu && this.bodyToggle.equalsIgnoreCase("body." + ((GuiButtonJutsu) button).getTranslationName())) {
                mc.ingameGUI.blit(((GuiButtonJutsu) button).widthIn, ((GuiButtonJutsu) button).heightIn, 240, 224, 16,16);
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
