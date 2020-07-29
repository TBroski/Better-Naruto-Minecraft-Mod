package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
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

public class PlayerTaijutsu extends Screen {

    private int guiLeft;
    private int guiTop;
    Button taijutsuUp;
    TextComponent guiTitle;
    TextComponent availableTaijutsu;

    protected PlayerTaijutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.legtaijutsu"));
        this.guiTitle = new TranslationTextComponent("gui." + Main.MODID + ".title.legtaijutsu");
        this.availableTaijutsu = new TranslationTextComponent("gui." + Main.MODID + ".text.availabletaijutsu");
    }

    @Override
    protected void init() {
        buttons.clear();
        this.guiLeft = (this.width) / 2;
        this.guiTop = (this.height) / 2;
        addButton(taijutsuUp = new Button(this.guiLeft - 80, this.guiTop + 80, 80, 20, "+1 Taijutsu (Cost 1)", $ -> {
            this.taijutsuUpPressed();
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        mc.ingameGUI.blit(this.guiLeft - 113, this.guiTop - 120, 0, 0, 227, 241);
        mc.textureManager.bindTexture(player.getLocationSkin());
        mc.ingameGUI.blit(this.guiLeft - 100, this.guiTop - 105, (4 * 4), (20 * 4), (4 * 4), (12 * 4));
        mc.ingameGUI.blit(this.guiLeft - 84, this.guiTop - 105, (4 * 4), (20 * 4), (4 * 4), (12 * 4));
        font.drawStringWithShadow(this.guiTitle.getString(), this.guiLeft - (font.getStringWidth(this.guiTitle.getString()) / 2), this.guiTop - 105, 0x2B2B2B);
        font.drawStringWithShadow(this.availableTaijutsu.getString(), this.guiLeft + 40 - (font.getStringWidth(this.availableTaijutsu.getString()) / 2), this.guiTop - 90, 0x32cd32);
        font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".legtaijutsu.taijutsu").getString() + playerc.returnTaijutsu(), this.guiLeft - 100, this.guiTop + 70, 0x32cd32);
        taijutsuUp.renderButton(p_render_1_, p_render_2_, p_render_3_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
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
