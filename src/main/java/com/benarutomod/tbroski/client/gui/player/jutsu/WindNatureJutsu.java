package com.benarutomod.tbroski.client.gui.player.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.common.jutsu.ShootingJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.settings.PacketKeybindSet;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketSetJutsuBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.lwjgl.opengl.GL11;

public class WindNatureJutsu extends Screen {

    TextComponent guiTitle;
    private int guiLeft;
    private int guiTop;
    private String jutsuToggle = "";
    Button buttonKey1;
    Button buttonKey2;
    Button buttonKey3;
    Button buttonKey4;
    Button buttonKey5;
    Button buttonKey6;
    Button buttonKey7;
    Button buttonKey8;
    Button buttonKey9;
    GuiButtonJutsu guiButtonGalePalmJutsu;
    GuiButtonJutsu guiButtonWindExplosionJutsu;
    GuiButtonJutsu guiButtonWindArrowJutsu;
    private int galePalmCost = 4;
    private int windExplosionCost = 6;
    private int windArrowCost = 5;

    public WindNatureJutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.windnaturejutsu"));
        this.guiTitle = new TranslationTextComponent("gui." + Main.MODID + ".title.windnaturejutsu");
    }


    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);

        guiButtonGalePalmJutsu.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonWindExplosionJutsu.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonWindArrowJutsu.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey1.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey2.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey3.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey4.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey5.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey6.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey7.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey8.renderButton(p_render_1_, p_render_2_, p_render_3_);
        buttonKey9.renderButton(p_render_1_, p_render_2_, p_render_3_);
        this.checkToggled();
        this.checkHovered(p_render_1_, p_render_2_);
        this.checkCovered();
    }

    @Override
    protected void init() {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        buttons.clear();
        this.guiLeft = (this.width) / 2;
        this.guiTop = (this.height) / 2;

        addButton(guiButtonGalePalmJutsu = new GuiButtonJutsu(this.guiLeft - 90, this.guiTop - 90, 80, 0, $ -> {
            if (playerc.hasGalePalmJutsuBoolean())
            {
                this.jutsuToggle = "jutsu." + Main.MODID + ".galepalm";
            }
            else if (playerc.returnBeNMPoints() >= this.galePalmCost)
            {
                playerc.addBeNMPoints(-this.galePalmCost);
                playerc.setGalePalmJutsuBoolean(true);
                NetworkLoader.INSTANCE.sendToServer(new PacketBeNMPointsSync(playerc.returnBeNMPoints(), false));
                NetworkLoader.INSTANCE.sendToServer(new PacketSetJutsuBoolean(ShootingJutsu.GalePalmJutsuID, playerc.hasGalePalmJutsuBoolean(), false));
            }
            else {
                player.sendMessage(new StringTextComponent("Not Enough BeNM Points (Need " + this.galePalmCost + ")"));
            }
        }));

        addButton(guiButtonWindExplosionJutsu = new GuiButtonJutsu(this.guiLeft - 70, this.guiTop - 90, 80, 16, $ -> {
            if (playerc.hasWindExplosionJutsuBoolean())
            {
                this.jutsuToggle = "jutsu." + Main.MODID + ".windexplosion";
            }
            else if (playerc.returnBeNMPoints() >= this.windExplosionCost)
            {
                playerc.addBeNMPoints(-this.windExplosionCost);
                playerc.setWindExplosionJutsuBoolean(true);
                NetworkLoader.INSTANCE.sendToServer(new PacketBeNMPointsSync(playerc.returnBeNMPoints(), false));
                NetworkLoader.INSTANCE.sendToServer(new PacketSetJutsuBoolean(ShootingJutsu.WindExplosionJutsuID, playerc.hasWindExplosionJutsuBoolean(), false));
            }
            else {
                player.sendMessage(new StringTextComponent("Not Enough BeNM Points (Need " + this.windExplosionCost + ")"));
            }
        }));

        addButton(guiButtonWindArrowJutsu = new GuiButtonJutsu(this.guiLeft - 50, this.guiTop - 90, 80, 32, $ -> {
            if (playerc.hasWindArrowJutsuBoolean())
            {
                this.jutsuToggle = "jutsu." + Main.MODID + ".windarrow";
            }
            else if (playerc.returnBeNMPoints() >= this.windArrowCost)
            {
                playerc.addBeNMPoints(-this.windArrowCost);
                playerc.setWindArrowJutsuBoolean(true);
                NetworkLoader.INSTANCE.sendToServer(new PacketBeNMPointsSync(playerc.returnBeNMPoints(), false));
                NetworkLoader.INSTANCE.sendToServer(new PacketSetJutsuBoolean(ShootingJutsu.WindArrowJutsuID, playerc.hasWindArrowJutsuBoolean(), false));
            }
            else {
                player.sendMessage(new StringTextComponent("Not Enough BeNM Points (Need " + this.windArrowCost + ")"));
            }
        }));

        addButton(buttonKey1 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft - 30, this.guiTop + 50, 20, 20, "1", $ -> {
            playerc.setKeybind1(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(1, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 1 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey2 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft - 10, this.guiTop + 50, 20, 20, "2", $ -> {
            playerc.setKeybind2(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(2, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 2 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey3 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft + 10, this.guiTop + 50, 20, 20, "3", $ -> {
            playerc.setKeybind3(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(3, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 3 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey4 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft - 30, this.guiTop + 70, 20, 20, "4", $ -> {
            playerc.setKeybind4(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(4, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 4 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey5 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft - 10, this.guiTop + 70, 20, 20, "5", $ -> {
            playerc.setKeybind5(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(5, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 5 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey6 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft + 10, this.guiTop + 70, 20, 20, "6", $ -> {
            playerc.setKeybind6(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(6, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 6 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey7 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft - 30, this.guiTop + 90, 20, 20, "7", $ -> {
            playerc.setKeybind7(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(7, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 7 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey8 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft - 10, this.guiTop + 90, 20, 20, "8", $ -> {
            playerc.setKeybind8(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(8, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 8 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
        addButton(buttonKey9 = new net.minecraft.client.gui.widget.button.Button(this.guiLeft + 10, this.guiTop + 90, 20, 20, "9", $ -> {
            playerc.setKeybind9(this.jutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketKeybindSet(9, this.jutsuToggle, false));
            player.sendMessage(new StringTextComponent("Keybind 9 Set to: " + new TranslationTextComponent(this.jutsuToggle).getString()));
            this.jutsuToggle = "";
        }));
    }


    public void checkHovered(int p_render_1, int p_render_2)
    {
        if (guiButtonGalePalmJutsu.isHovered())
        {
            renderTooltip(new TranslationTextComponent("jutsu." + Main.MODID + ".galepalm").getString(), p_render_1, p_render_2);
        }
        if (guiButtonWindExplosionJutsu.isHovered())
        {
            renderTooltip(new TranslationTextComponent("jutsu." + Main.MODID + ".windexplosion").getString(), p_render_1, p_render_2);
        }
        if (guiButtonWindArrowJutsu.isHovered())
        {
            renderTooltip(new TranslationTextComponent("jutsu." + Main.MODID + ".windarrow").getString(), p_render_1, p_render_2);
        }
    }

    public void checkCovered()
    {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);
        if (!playerc.hasGalePalmJutsuBoolean())
        {
            mc.ingameGUI.blit(this.guiLeft - 90, this.guiTop - 90, 240, 240, 16, 16);
        }
        if (!playerc.hasWindExplosionJutsuBoolean())
        {
            mc.ingameGUI.blit(this.guiLeft - 70, this.guiTop - 90, 240, 240, 16, 16);
        }
        if (!playerc.hasWindArrowJutsuBoolean())
        {
            mc.ingameGUI.blit(this.guiLeft - 50, this.guiTop - 90, 240, 240, 16, 16);
        }
    }

    public void checkToggled()
    {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.002F);

        if (this.jutsuToggle.equalsIgnoreCase("jutsu." + Main.MODID + ".galepalm"))
        {
            mc.ingameGUI.blit(this.guiLeft - 90, this.guiTop - 90, 240, 224, 16,16);
        }
        if (this.jutsuToggle.equalsIgnoreCase("jutsu." + Main.MODID + ".windexplosion"))
        {
            mc.ingameGUI.blit(this.guiLeft - 70, this.guiTop - 90, 240, 224, 16,16);
        }
        if (this.jutsuToggle.equalsIgnoreCase("jutsu." + Main.MODID + ".windarrow"))
        {
            mc.ingameGUI.blit(this.guiLeft - 50, this.guiTop - 90, 240, 224, 16,16);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
