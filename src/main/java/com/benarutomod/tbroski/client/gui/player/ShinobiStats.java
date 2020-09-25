package com.benarutomod.tbroski.client.gui.player;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.BijuuJutsuScreen;
import com.benarutomod.tbroski.client.gui.widgets.shinobistats.*;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerArm;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerBody;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerHead;
import com.benarutomod.tbroski.client.gui.widgets.player.GuiButtonPlayerLeg;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.entity.mobs.bijuu.ShukakuEntity;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.settings.PacketEyeSlotSync;
import com.benarutomod.tbroski.networking.packets.settings.PacketToggleMessageBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShinobiStats extends Screen {

    private static final ResourceLocation STATS_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/shinobistatsbackground.png");
    private static final ResourceLocation BIJUU_OVERLAY_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/bijuuoverlay.png");

    GuiButtonEyeSlot guiButtonEyeSlot;
    GuiButtonMessageJutsu guiButtonMessageJutsu;
    GuiButtonSettingsWheel guiButtonSettingsWheel;
    GuiButtonArrowUp guiButtonArrowUp;
    GuiButtonArrowDown guiButtonArrowDown;
    GuiButtonBijuuOverlay guiButtonBijuuOverlay;
    GuiButtonClanConfiguration guiButtonClanConfiguration;

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
    private AbstractBijuuEntity bijuu;

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
        for (EntityType<?> entity : ForgeRegistries.ENTITIES.getValues()) {
            if (entity.getRegistryName().toString().equalsIgnoreCase(playerc.returnPlayerBijuu())) {
                this.bijuu = (AbstractBijuuEntity) entity.create(Minecraft.getInstance().world);
                break;
            }
        }
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
        addButton(guiButtonBijuuOverlay = new GuiButtonBijuuOverlay(this.guiLeft - 40, this.guiTop - 40, $ -> {
            Minecraft.getInstance().displayGuiScreen(new BijuuJutsuScreen());
        }));

        if (this.bijuu == null)
            guiButtonBijuuOverlay.visible = false;

        addButton(guiButtonClanConfiguration = new GuiButtonClanConfiguration(font.getStringWidth(new TranslationTextComponent("gui." + Main.MODID + ".clan.shinobistats").getString() + playerc.returnPlayerClan().getString().substring(0, 1).toUpperCase() + playerc.returnPlayerClan().getString().substring(1).toLowerCase()) + 15, 27, p_onPress_1_ -> {
            Minecraft.getInstance().displayGuiScreen(new PlayerClanConfigurationScreen());
        }));

        if (playerc.returnPlayerClan() == ClanInit.NULL)
            guiButtonClanConfiguration.visible = false;

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
        mc.textureManager.bindTexture(STATS_TEXTURE);
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
            font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".playeraffiliation.shinobistats").getString() + this.entityName, 10, 50, 0xFF0000);
        }
        font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".benmpoints.shinobistats").getString() + playerc.returnBeNMPoints(), 10, 10, 0x32cd32);
        String clanName = playerc.returnPlayerClan().getString();
        font.drawStringWithShadow(new TranslationTextComponent("gui." + Main.MODID + ".clan.shinobistats").getString() + clanName.substring(0, 1).toUpperCase() + clanName.substring(1).toLowerCase(), 10, 30, 0x4A70CB);
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
        ClientPlayerEntity player = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
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
        if (guiButtonBijuuOverlay.isHovered()) {
            if (this.bijuu != null) {
                guiButtonBijuuOverlay.visible = true;
                Minecraft.getInstance().textureManager.bindTexture(BIJUU_OVERLAY_TEXTURE);
                Minecraft.getInstance().ingameGUI.blit(this.guiLeft - 16, this.guiTop - 16, 0, 0, 32, 48);
                InventoryScreen.drawEntityOnScreen(this.guiLeft, this.guiTop + 24, 1, 0 + (p_render_1_ / 20), 0 + (p_render_2_ / 20), bijuu);
            }
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
