package com.benarutomod.tbroski.client.gui.chakrastyles;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class FirePaperGui extends Screen implements INamedContainerProvider {

    private int guiTop;
    private int guiLeft;

    public FirePaperGui() {
        super(new StringTextComponent("Fire Style"));
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        super.render(p_render_1_, p_render_2_, p_render_3_);
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;

        mc.textureManager.bindTexture(new ResourceLocation(Main.MODID + ":textures/gui/chakrastyles/firepaper.png"));
        mc.ingameGUI.blit(this.guiLeft + 2, this.guiTop - 20, 0, 0, 256, 256);
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - 185) / 2;
        this.guiTop = (this.height - 185) / 2;

    }
}
