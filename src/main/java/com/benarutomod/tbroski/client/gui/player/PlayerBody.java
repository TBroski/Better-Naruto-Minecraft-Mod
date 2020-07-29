package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraControlSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerBody extends Screen {

    private int guiLeft;
    private int guiTop;
    Button chakraControlUp;
    TextComponent guiTitle;
    TextComponent availableChakraColors;

    protected PlayerBody() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra"));
        this.guiTitle = new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra");
        this.availableChakraColors = new TranslationTextComponent("gui." + Main.MODID + ".text.availablechakracolors");
    }

    @Override
    protected void init() {
        buttons.clear();
        this.guiLeft = (this.width) / 2;
        this.guiTop = (this.height) / 2;
        addButton(chakraControlUp = new Button(this.guiLeft - 90, this.guiTop + 80, 180, 20, "-0.05% Chakra per Jutsu (Cost 2)", $ -> {
            this.chakraControlUpPressed();
        }));
    }


    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        mc.textureManager.bindTexture(player.getLocationSkin());
        mc.ingameGUI.blit(this.guiLeft - 100, this.guiTop - 105, (20 * 4), (20 * 4), (8 * 4), (12 * 4));
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);
        font.drawStringWithShadow(this.availableChakraColors.getString(), this.guiLeft + 40 - (font.getStringWidth(this.availableChakraColors.getString()) / 2), this.guiTop - 90, 0x32cd32);
        font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".chakracontrol.bodychakra").getString() + (100 - playerc.returnChakraControl()) + "%", this.guiLeft - 85, this.guiTop + 70, 0x32cd32);
        chakraControlUp.renderButton(p_render_1_, p_render_2_,p_render_3_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
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
