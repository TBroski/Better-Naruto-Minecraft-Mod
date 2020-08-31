package com.benarutomod.tbroski.client.gui.widgets;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButtonSusanooItem extends Button {

    private ItemStack itemStack;
    private int offsetX;
    int widthIn;
    int heightIn;
    boolean background;

    public GuiButtonSusanooItem(int widthIn, int heightIn, int offsetX, ItemStack susanooItem, boolean background, IPressable onPress) {
        super(widthIn, heightIn, 90, 90, "", onPress);
        this.itemStack = susanooItem;
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.background = background;
        this.offsetX = offsetX;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        if (!visible)
        {
            return;
        }
        GL11.glPushMatrix();
        if (this.background) {
            mc.textureManager.bindTexture(new ResourceLocation(Main.MODID, "textures/gui/transplant.png"));
            mc.ingameGUI.blit(widthIn, heightIn, 0, 184, 72, 72);
        }
        GL11.glScalef(2.5F, 2.5F, 2.5F);
        //GL11.glTranslated(-135, -45, 0);
        mc.getItemRenderer().renderItemIntoGUI(this.itemStack, this.widthIn - offsetX, this.heightIn - 48);
        GL11.glPopMatrix();
    }

    public void setItemStack(ItemStack susanooItem) {
        this.itemStack = susanooItem;
    }
}
