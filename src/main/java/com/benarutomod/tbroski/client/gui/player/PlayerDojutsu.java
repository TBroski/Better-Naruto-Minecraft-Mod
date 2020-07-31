package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.SharinganJutsuScreen;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonDojutsu;
import com.benarutomod.tbroski.common.BeNMDojutsu;
import com.benarutomod.tbroski.init.DojutsuInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerDojutsu extends Screen{

    private int guiLeft;
    private int guiTop;
    TextComponent guiTitle;
    TextComponent availableDojutsu;

    GuiButtonDojutsu guiButtonSharingan;

    protected PlayerDojutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.dojutsu"));
        this.guiTitle = new TranslationTextComponent("gui." + Main.MODID + ".title.dojutsu");
        this.availableDojutsu = new TranslationTextComponent("gui." + Main.MODID + ".text.availabledojutsu");
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

        addButton(guiButtonSharingan = new GuiButtonDojutsu(this.guiLeft - 32, this.guiTop - 32, DojutsuInit.MANGEKYOU_SHARINGAN, $ -> {
                if ((playerc.returnPlayerLeftDojutsu().getType() == BeNMDojutsu.Type.SHARINGAN && playerc.returnPlayerLeftDojutsu() != DojutsuInit.SHARINGAN) || (playerc.returnPlayerLeftDojutsu().getType() == BeNMDojutsu.Type.SHARINGAN && playerc.returnPlayerRightDojutsu() != DojutsuInit.SHARINGAN)) {
                    //mc.displayGuiScreen(new SharinganJutsu());
                    mc.displayGuiScreen(new SharinganJutsuScreen());
                }
                else {
                    player.sendMessage(new StringTextComponent("You don't posses a Mangekyou Sharingan."));
                }
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        mc.textureManager.bindTexture(player.getLocationSkin());
        mc.ingameGUI.blit(this.guiLeft - 100, this.guiTop - 105, (8 * 4), (8 * 4), (8 * 4), (8 * 4));
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);
        font.drawStringWithShadow(this.availableDojutsu.getString(), this.guiLeft + 40 - (font.getStringWidth(this.availableDojutsu.getString()) / 2), this.guiTop - 90, 0x32cd32);

        guiButtonSharingan.renderButton(p_render_1_, p_render_2_, p_render_3_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }
}
