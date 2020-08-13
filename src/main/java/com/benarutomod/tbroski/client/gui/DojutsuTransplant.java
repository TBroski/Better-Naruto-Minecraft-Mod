package com.benarutomod.tbroski.client.gui;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonDojutsu;
import com.benarutomod.tbroski.api.internal.BeNMDojutsu;
import com.benarutomod.tbroski.entity.shinobi.IDojutsuEntity;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;


public class DojutsuTransplant<T extends LivingEntity & IDojutsuEntity> extends Screen {

    GuiButtonDojutsu guiButtonDojutsu1;
    GuiButtonDojutsu guiButtonDojutsu2;
    GuiButtonDojutsu guiButtonDojutsu3;
    GuiButtonDojutsu guiButtonDojutsu4;
    private T transplantingEntity;
    private BeNMDojutsu dojutsuToggle = DojutsuInit.EMPTY;
    private boolean leftDojutsuToggled;
    private int guiLeft;
    private int guiTop;
    private int toggledDojutsu;

    public DojutsuTransplant(T transplantingEntity) {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.dojutsutransplant"));
        this.transplantingEntity = transplantingEntity;
    }

    public DojutsuTransplant(DojutsuSkullTileEntity transplantingTileEntity) {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.dojutsutransplant"));
        if (transplantingTileEntity.getLivingEntity() instanceof IDojutsuEntity) this.transplantingEntity = (T) transplantingTileEntity.getLivingEntity();
    }

    @Override
    protected void init() {
        buttons.clear();
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        this.guiLeft = (this.width) / 2;
        this.guiTop = (this.height) / 2;

        addButton(guiButtonDojutsu1 = new GuiButtonDojutsu(this.guiLeft - 160, this.guiTop + ((playerc.returnplayerEyeSlot() * 16) - 64), playerc.returnPlayerLeftDojutsu(), $ -> {
            if (this.leftDojutsuToggled) {
                this.transplantingEntity.setLeftDojustsu(playerc.returnPlayerLeftDojutsu());
            }
            else {
                this.transplantingEntity.setRightDojustsu(playerc.returnPlayerLeftDojutsu());
            }
            playerc.setPlayerLeftDojutsu(this.dojutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketPlayerDojutsuSync(playerc.returnPlayerLeftDojutsu().getString(), true, false));
            this.dojutsuToggle = DojutsuInit.EMPTY;
            this.toggledDojutsu = 0;
        }));

        addButton(guiButtonDojutsu2 = new GuiButtonDojutsu(this.guiLeft - 112, this.guiTop + ((playerc.returnplayerEyeSlot() * 16) - 64), playerc.returnPlayerRightDojutsu(), $ -> {
            if (this.leftDojutsuToggled) {
                this.transplantingEntity.setLeftDojustsu(playerc.returnPlayerRightDojutsu());
            }
            else {
                this.transplantingEntity.setRightDojustsu(playerc.returnPlayerRightDojutsu());
            }
            playerc.setPlayerRightDojutsu(this.dojutsuToggle);
            NetworkLoader.INSTANCE.sendToServer(new PacketPlayerDojutsuSync(playerc.returnPlayerRightDojutsu().getString(), false, false));
            this.dojutsuToggle = DojutsuInit.EMPTY;
            this.toggledDojutsu = 0;
        }));

        addButton(guiButtonDojutsu3 = new GuiButtonDojutsu(this.guiLeft + 96, this.guiTop + ((this.transplantingEntity.eyeSlot() * 16) - 64), this.transplantingEntity.leftDojustsu(), $ -> {
            this.dojutsuToggle = this.transplantingEntity.leftDojustsu();
            this.toggledDojutsu = 3;
            this.leftDojutsuToggled = true;
        }));
        addButton(guiButtonDojutsu4 = new GuiButtonDojutsu(this.guiLeft + 144, this.guiTop + ((this.transplantingEntity.eyeSlot() * 16) - 64), this.transplantingEntity.rightDojustsu(), $ -> {
            this.dojutsuToggle = this.transplantingEntity.rightDojustsu();
            this.toggledDojutsu = 4;
            this.leftDojutsuToggled = false;
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/transplant.png"));
        mc.ingameGUI.blit(this.guiLeft - 61, this.guiTop - 21, 0, 0, 122, 42);
        mc.textureManager.bindTexture(player.getLocationSkin());
        mc.ingameGUI.blit(this.guiLeft - 192, this.guiTop - 64, (8 * 16), (8 * 16), (8 * 16), (8 * 16), 1024, 1024);
        this.checkRenderer(mc);
        this.checkToggled();
        guiButtonDojutsu1.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu2.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu3.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu4.renderButton(p_render_1_, p_render_2_, p_render_3_);
        this.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }

    private void checkToggled() {
        if (this.toggledDojutsu == 4) {
            guiButtonDojutsu4.toggled = true;
        }
        else {
            guiButtonDojutsu4.toggled = false;
        }
        if (this.toggledDojutsu == 3) {
            guiButtonDojutsu3.toggled = true;
        }
        else {
            guiButtonDojutsu3.toggled = false;
        }
    }

    private void checkRenderer(Minecraft mc) {
        if (TextureHelper.getResourceLocationFromEntity(mc, this.transplantingEntity) != null) {
            mc.textureManager.bindTexture(TextureHelper.getResourceLocationFromEntity(mc, this.transplantingEntity));
            mc.ingameGUI.blit(this.guiLeft + 64, this.guiTop - 64, (8 * 16), (8 * 16), (8 * 16), (8 * 16), 1024, 1024);
            this.checkToggled();
        }
    }
}
