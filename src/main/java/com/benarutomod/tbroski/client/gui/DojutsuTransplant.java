package com.benarutomod.tbroski.client.gui;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.entity.AbstractShinobiEntity;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMSharingan;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonDojutsu;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.api.interfaces.IDojutsuEntity;
import com.benarutomod.tbroski.entity.shinobi.shinobi.BrotherSharinganEntity;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import com.benarutomod.tbroski.networking.packets.PacketShinobiLevel;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.TextureHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;


public class DojutsuTransplant<T extends AbstractShinobiEntity & IDojutsuEntity> extends Screen {

    GuiButtonDojutsu guiButtonDojutsu1;
    GuiButtonDojutsu guiButtonDojutsu2;
    GuiButtonDojutsu guiButtonDojutsu3;
    GuiButtonDojutsu guiButtonDojutsu4;
    private T transplantingEntity;
    private BeNMDojutsu dojutsuToggle = DojutsuInit.NULL;
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
            if (this.transplantingEntity instanceof BrotherSharinganEntity && ((BrotherSharinganEntity) this.transplantingEntity).getBrotherId() == player.getEntityId() && this.dojutsuToggle instanceof BeNMSharingan && playerc.returnPlayerLeftDojutsu() == DojutsuInit.MANGEKYOU_SHARINGAN) {
                playerc.setPlayerLeftDojutsu(DojutsuInit.ETERNAL_MANGEKYOU_SHARINGAN);
            }
            else {
                playerc.setPlayerLeftDojutsu(this.dojutsuToggle);
            }
            setChunin(player);
            NetworkLoader.INSTANCE.sendToServer(new PacketPlayerDojutsuSync(playerc.returnPlayerLeftDojutsu().getString(), true, false));
            this.dojutsuToggle = DojutsuInit.NULL;
            this.toggledDojutsu = 0;
        }));

        addButton(guiButtonDojutsu2 = new GuiButtonDojutsu(this.guiLeft - 112, this.guiTop + ((playerc.returnplayerEyeSlot() * 16) - 64), playerc.returnPlayerRightDojutsu(), $ -> {
            if (this.leftDojutsuToggled) {
                this.transplantingEntity.setLeftDojustsu(playerc.returnPlayerRightDojutsu());
            }
            else {
                this.transplantingEntity.setRightDojustsu(playerc.returnPlayerRightDojutsu());
            }
            if (this.transplantingEntity instanceof BrotherSharinganEntity && ((BrotherSharinganEntity) this.transplantingEntity).getBrotherId() == player.getEntityId() && this.dojutsuToggle instanceof BeNMSharingan && playerc.returnPlayerRightDojutsu() == DojutsuInit.MANGEKYOU_SHARINGAN) {
                playerc.setPlayerRightDojutsu(DojutsuInit.ETERNAL_MANGEKYOU_SHARINGAN);
            }
            else {
                playerc.setPlayerRightDojutsu(this.dojutsuToggle);
            }
            setChunin(player);
            NetworkLoader.INSTANCE.sendToServer(new PacketPlayerDojutsuSync(playerc.returnPlayerRightDojutsu().getString(), false, false));
            this.dojutsuToggle = DojutsuInit.NULL;
            this.toggledDojutsu = 0;
        }));

        addButton(guiButtonDojutsu3 = new GuiButtonDojutsu(this.guiLeft + 96, this.guiTop + ((this.transplantingEntity.eyeSlot() * 16) - 64), this.transplantingEntity.getLeftDojustsu(), $ -> {
            this.dojutsuToggle = this.transplantingEntity.getLeftDojustsu();
            this.toggledDojutsu = 3;
            this.leftDojutsuToggled = true;
        }));
        addButton(guiButtonDojutsu4 = new GuiButtonDojutsu(this.guiLeft + 144, this.guiTop + ((this.transplantingEntity.eyeSlot() * 16) - 64), this.transplantingEntity.getRightDojustsu(), $ -> {
            this.dojutsuToggle = this.transplantingEntity.getRightDojustsu();
            this.toggledDojutsu = 4;
            this.leftDojutsuToggled = false;
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID, "textures/gui/transplant.png"));
        mc.ingameGUI.blit(this.guiLeft - 61, this.guiTop - 21, 0, 0, 122, 42);
        mc.textureManager.bindTexture(player.getLocationSkin());
        mc.ingameGUI.blit(this.guiLeft - 192, this.guiTop - 64, (8 * 16), (8 * 16), (8 * 16), (8 * 16), 1024, 1024);
        this.checkRenderer(mc);
        this.checkToggled();
        guiButtonDojutsu1.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu2.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu3.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu4.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonDojutsu1.setDojutsu(playerc.returnPlayerLeftDojutsu());
        guiButtonDojutsu2.setDojutsu(playerc.returnPlayerRightDojutsu());
        guiButtonDojutsu3.setDojutsu(this.transplantingEntity.getLeftDojustsu());
        guiButtonDojutsu4.setDojutsu(this.transplantingEntity.getRightDojustsu());
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

    private static void setChunin(PlayerEntity playerIn) {
        LazyOptional<IPlayerHandler> player_cap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        if (playerc.returnShinobiLevel() < 2) {
            playerc.setShinobiLevel(2);
            NetworkLoader.INSTANCE.sendToServer(new PacketShinobiLevel(2, false));
        }
    }
}
