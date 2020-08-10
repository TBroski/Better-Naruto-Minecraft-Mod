package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.*;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerArm;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerBody;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerHead;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerLeg;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.settings.PacketEyeSlotSync;
import com.benarutomod.tbroski.networking.packets.settings.PacketToggleMessageBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

public class ShinobiStats extends Screen {

    GuiButtonEyeSlot guiButtonEyeSlot;
    GuiButtonMessageJutsu guiButtonMessageJutsu;
    GuiButtonSettingsWheel guiButtonSettingsWheel;
    GuiButtonArrowUp guiButtonArrowUp;
    GuiButtonArrowDown guiButtonArrowDown;
    GuiButtonPlayerHead guiButtonPlayerHead;
    GuiButtonPlayerBody guiButtonPlayerBody;
    GuiButtonPlayerArm guiButtonPlayerArm1;
    GuiButtonPlayerArm guiButtonPlayerArm2;
    GuiButtonPlayerLeg guiButtonPlayerLeg1;
    GuiButtonPlayerLeg guiButtonPlayerLeg2;

    private boolean toggleEyeSelection;
    private int guiTop;
    private int guiLeft;
    private int playerEyeSlot;
    private String entityName;

    public ShinobiStats() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.shinobistats"));
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

        addButton(guiButtonEyeSlot = new GuiButtonEyeSlot(this.width - 31, 40, $ -> {
            this.onGuiButtonEyeSlotPress();
            }));
        addButton(guiButtonMessageJutsu = new GuiButtonMessageJutsu(this.width - 31, 70, $ -> {
            this.onGuiButtonMessageJutsuPress();
        }));
        addButton(guiButtonSettingsWheel = new GuiButtonSettingsWheel(this.width - 31, 10, $ -> {
            Minecraft.getInstance().displayGuiScreen(new RenderSettings());
        }));
        addButton(guiButtonArrowUp = new GuiButtonArrowUp(this.guiLeft + 50, this.guiTop - 15, true, $ -> {
            this.onGuiButtonArrowUpPress();
        }));
        addButton(guiButtonArrowDown = new GuiButtonArrowDown(this.guiLeft + 50, this.guiTop + 15, true, $ -> {
            this.onGuiButtonArrowDownPress();
        }));
        addButton(guiButtonPlayerHead = new GuiButtonPlayerHead(this.guiLeft - 16, this.guiTop - 48, player, $ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerDojutsu());
        }));
        addButton(guiButtonPlayerBody = new GuiButtonPlayerBody(this.guiLeft - 16, this.guiTop - 16, player, $ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerBodyScreen());
        }));
        addButton(guiButtonPlayerArm1 = new GuiButtonPlayerArm(this.guiLeft - 32, this.guiTop - 16, player, $ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerJutsuScreen());
        }));
        addButton(guiButtonPlayerArm2 = new GuiButtonPlayerArm(this.guiLeft + 16, this.guiTop - 16, player, $ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerJutsuScreen());
        }));
        addButton(guiButtonPlayerLeg1 = new GuiButtonPlayerLeg(this.guiLeft - 16, this.guiTop + 32, player, $ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerTaijutsu());
        }));
        addButton(guiButtonPlayerLeg2 = new GuiButtonPlayerLeg(this.guiLeft, this.guiTop + 32, player, $ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerTaijutsu());
        }));
        this.guiButtonArrowUp.visible = false;
        this.guiButtonArrowDown.visible = false;
        if (playerc.returnplayerEyeSlot() <= 0)
        {
            this.playerEyeSlot = 4;
        }
        else
        {
            this.playerEyeSlot = playerc.returnplayerEyeSlot();
        }
        super.init();
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        Minecraft mc = Minecraft.getInstance();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        guiButtonPlayerArm2.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonPlayerArm1.renderButton(p_render_1_, p_render_2_, p_render_3_);
        if (this.toggleEyeSelection) {
            guiButtonArrowUp.renderButton(p_render_1_, p_render_2_, p_render_3_);
            guiButtonArrowDown.renderButton(p_render_1_, p_render_2_, p_render_3_);
        }
        guiButtonPlayerHead.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonPlayerBody.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonPlayerLeg1.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonPlayerLeg2.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonEyeSlot.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonMessageJutsu.renderButton(p_render_1_, p_render_2_, p_render_3_);
        guiButtonSettingsWheel.renderButton(p_render_1_, p_render_2_, p_render_3_);
        this.renderFonts();
        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/shinobistatsbackground.png"));
        if (this.toggleEyeSelection)
        {
            mc.ingameGUI.blit(this.guiLeft - 8, (this.guiTop - 48) + (this.playerEyeSlot * 4), 0, 252, 16, 4);
        }
        this.checkHovered(p_render_1_, p_render_2_);
    }

    private void renderFonts() {
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (playerc.returnPlayerEntityAffiliation() != "") {
            for (EntityType<?> entityType : ForgeRegistries.ENTITIES.getValues()) {
                if (entityType.getRegistryName().toString().equalsIgnoreCase(playerc.returnPlayerEntityAffiliation())) {
                    this.entityName = entityType.getRegistryName().toString();
                }
            }
            font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".playeraffiliation.shinobistats").getString() + this.entityName, 10, 30, 0xFF0000);
        }
        font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".benmpoints.shinobistats").getString() + playerc.returnBeNMPoints(), 10, 10, 0x32cd32);
        font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".benmpoints.shinobistats").getString() + playerc.returnBeNMPoints(), 10, 10, 0x32cd32);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void onGuiButtonEyeSlotPress()
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (!this.toggleEyeSelection)
        {
            this.guiButtonArrowUp.visible = true;
            this.guiButtonArrowDown.visible = true;
            this.toggleEyeSelection = true;
        }
        else {
            this.guiButtonArrowUp.visible = false;
            this.guiButtonArrowDown.visible = false;
            this.toggleEyeSelection = false;
            playerc.setplayerEyeSlot(this.playerEyeSlot);
            int eyeSlot = playerc.returnplayerEyeSlot();
            NetworkLoader.INSTANCE.sendToServer(new PacketEyeSlotSync(eyeSlot, false));
        }
    }
    public void onGuiButtonArrowUpPress()
    {
        //System.out.println("UP");
        if (this.guiButtonArrowUp.visible)
        {
            if (this.playerEyeSlot >= 1)
            {
               // System.out.println("UPC");
                this.playerEyeSlot -= 1;
                //System.out.println(this.playerEyeSlot);
            }
        }
    }
    public void onGuiButtonArrowDownPress()
    {
        //System.out.println("DOWN");
        if (this.guiButtonArrowDown.visible)
        {
            if (this.playerEyeSlot < 7)
            {
                //System.out.println("DOWNC");
                this.playerEyeSlot += 1;
                //System.out.println(this.playerEyeSlot);
            }
        }
    }

    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
    }

    @Override
    public void onClose() {
        super.onClose();
        ClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        playerc.setplayerEyeSlot(this.playerEyeSlot);
        int eyeSlot = playerc.returnplayerEyeSlot();
        NetworkLoader.INSTANCE.sendToServer(new PacketEyeSlotSync(eyeSlot, false));
        if (this.guiButtonArrowUp.visible) {
            player.sendMessage(new StringTextComponent("Saved Settings"));
        }
    }

    public void checkHovered(int p_render_1_, int p_render_2_)
    {
        if (guiButtonEyeSlot.isHovered())
        {
            renderTooltip("Change Player Eye Slot", p_render_1_, p_render_2_);
        }
        if (guiButtonSettingsWheel.isHovered())
        {
            renderTooltip("Render Settings", p_render_1_, p_render_2_);
        }
        if (guiButtonMessageJutsu.isHovered())
        {
            renderTooltip("Toggle Jutsu Messages", p_render_1_, p_render_2_);
        }
        if (guiButtonPlayerHead.isHovered())
        {
            renderTooltip(new TranslationTextComponent("gui." + Main.MODID + ".title.dojutsu").getString(), p_render_1_, p_render_2_);
        }
        if (guiButtonPlayerBody.isHovered())
        {
            renderTooltip(new TranslationTextComponent("gui." + Main.MODID + ".title.bodychakra").getString(), p_render_1_, p_render_2_);
        }
        if (guiButtonPlayerArm1.isHovered())
        {
            renderTooltip(new TranslationTextComponent("gui." + Main.MODID + ".title.handjutsu").getString(), p_render_1_, p_render_2_);
        }
        if (guiButtonPlayerArm2.isHovered())
        {
            renderTooltip(new TranslationTextComponent("gui." + Main.MODID + ".title.handjutsu").getString(), p_render_1_, p_render_2_);
        }
        if (guiButtonPlayerLeg1.isHovered())
        {
            renderTooltip(new TranslationTextComponent("gui." + Main.MODID + ".title.legtaijutsu").getString(), p_render_1_, p_render_2_);
        }
        if (guiButtonPlayerLeg2.isHovered())
        {
            renderTooltip(new TranslationTextComponent("gui." + Main.MODID + ".title.legtaijutsu").getString(), p_render_1_, p_render_2_);
        }
    }


    private void onGuiButtonMessageJutsuPress() {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if (playerc.returnToggleJutsuMessage())
        {
            NetworkLoader.INSTANCE.sendToServer(new PacketToggleMessageBoolean(false, false));
            playerc.setToggleJutsuMessage(false);
        }
        else {
            NetworkLoader.INSTANCE.sendToServer(new PacketToggleMessageBoolean(true, false));
            playerc.setToggleJutsuMessage(true);
        }
    }
}
