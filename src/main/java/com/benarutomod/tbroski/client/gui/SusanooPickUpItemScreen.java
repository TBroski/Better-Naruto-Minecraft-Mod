package com.benarutomod.tbroski.client.gui;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.GuiButtonSusanooItem;
import com.benarutomod.tbroski.client.renderer.layers.models.susanoo.ModelSasukeSusanoo;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketSusanooItemsSync;
import com.benarutomod.tbroski.util.helpers.TextureHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SusanooPickUpItemScreen extends Screen {

    private ModelSasukeSusanoo sasuke = new ModelSasukeSusanoo();

    private GuiButtonSusanooItem mainHandItemButton;
    private GuiButtonSusanooItem offHandItemButton;

    private PlayerEntity susanooOwner;
    private ItemStack pickedUpItem;
    private int guiLeft;
    private int guiTop;

    public SusanooPickUpItemScreen(PlayerEntity susanooOwner, ItemStack pickedUpItem) {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.susanoopickup"));
        this.susanooOwner = susanooOwner;
        this.pickedUpItem = pickedUpItem;
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

        addButton(mainHandItemButton = new GuiButtonSusanooItem(this.guiLeft - 90, this.guiTop - 36, 84, playerc.getSusanooMainHand(), true, p_onPress_1_ -> {
            ItemStack oldItem = playerc.getSusanooMainHand().copy();
            playerc.setSusanooMainHand(this.pickedUpItem);
            NetworkLoader.INSTANCE.sendToServer(new PacketSusanooItemsSync(player.getEntityId(), this.pickedUpItem, playerc.getSusanooOffHand(), false));
            this.pickedUpItem = oldItem;
        }));
        addButton(offHandItemButton = new GuiButtonSusanooItem(this.guiLeft + 54, this.guiTop - 36, 169, playerc.getSusanooOffHand(), true, p_onPress_1_ -> {
            ItemStack oldItem = playerc.getSusanooOffHand().copy();
            playerc.setSusanooOffHand(this.pickedUpItem);
            NetworkLoader.INSTANCE.sendToServer(new PacketSusanooItemsSync(player.getEntityId(), playerc.getSusanooMainHand(), this.pickedUpItem, false));
            this.pickedUpItem = oldItem;
        }));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        AbstractClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        GL11.glPushMatrix();
        GL11.glScalef(2.5F, 2.5F, 2.5F);
        //GL11.glTranslated(-135, -45, 0);
        mc.getItemRenderer().renderItemIntoGUI(this.pickedUpItem, this.guiLeft - 143, this.guiTop - 95);
        GL11.glPopMatrix();
        /*        mc.getItemRenderer().renderItemIntoGUI(playerc.getSusanooMainHand(), this.guiLeft - 90, this.guiTop - 36);
        mc.getItemRenderer().renderItemIntoGUI(playerc.getSusanooOffHand(), this.guiLeft + 54, this.guiTop - 36);*/

/*        Color color = TextureHelper.getColorFromColorChakra(playerc.returncolorChakra());
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.push();
        matrixStack.translate(0,0.8,0);
        sasuke.setRotationAngles(player, 0, 0, player.ticksExisted, 0, 0);
        IVertexBuilder ivertexbuilder = ItemRenderer.getBuffer(mc.getRenderTypeBuffers().getBufferSource(), sasuke.getRenderType(sasuke.getSusanooTexture(player)), false, false);
        sasuke.render(matrixStack, iverltexbuilder, 1, OverlayTexture.NO_OVERLAY, color.getRed() / 256F, color.getGreen() / 256F, color.getBlue() / 256F, 0.3F);
        matrixStack.pop();*/
        offHandItemButton.renderButton(p_render_1_, p_render_2_, p_render_3_);
        mainHandItemButton.renderButton(p_render_1_, p_render_2_, p_render_3_);
        offHandItemButton.setItemStack(playerc.getSusanooOffHand());
        mainHandItemButton.setItemStack(playerc.getSusanooMainHand());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
