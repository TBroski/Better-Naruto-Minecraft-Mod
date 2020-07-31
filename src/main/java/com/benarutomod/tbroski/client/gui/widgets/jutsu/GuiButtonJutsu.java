package com.benarutomod.tbroski.client.gui.widgets.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketSetJutsuBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class GuiButtonJutsu extends Button {

    final ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/gui/jutsu.png");

    protected final IPressable onPress;
    final int u;
    final int v;
    public int widthIn;
    public int heightIn;
    private final String name;
    private int cost;
    private boolean has;

    @Deprecated
    public GuiButtonJutsu(int widthIn, int heightIn, int u, int v, IPressable onPress) {
        super(widthIn, heightIn, 16, 16, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.u = u;
        this.v = v;
        this.onPress = onPress;
        this.name = "";
    }

    public GuiButtonJutsu(int widthIn, int heightIn, int u, int v, String name, boolean has, int cost, IPressable onPress) {
        super(widthIn, heightIn, 16, 16, "", onPress);
        this.widthIn = widthIn;
        this.heightIn = heightIn;
        this.u = u;
        this.v = v;
        this.onPress = onPress;
        this.name = name;
        this.has = has;
        this.cost = cost;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        Minecraft mc = Minecraft.getInstance();
        mc.textureManager.bindTexture(texture);
        if (!visible)
        {
            return;
        }
        mc.ingameGUI.blit(widthIn, heightIn, u, v, width, height);
    }

    public boolean hasJutsu() {
        return this.has;
    }

    public void setHasJutsu(boolean has) {
        this.has = has;
    }

    public String getName() {
        return this.name;
    }

    public boolean doNormalPress(AbstractJutsuScreen screen) {
        PlayerEntity playerEntity = Minecraft.getInstance().player;
        LazyOptional<IPlayerHandler> player_cap = playerEntity.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        boolean flag = false;

        if (this.hasJutsu())
        {
            System.out.println("Selected");
            screen.jutsuToggle = "jutsu." + Main.MODID + "." + this.getName();
        }
        else if (playerc.returnBeNMPoints() >= this.cost)
        {
            playerc.addBeNMPoints(-this.cost);
            flag = true;
            NetworkLoader.INSTANCE.sendToServer(new PacketBeNMPointsSync(playerc.returnBeNMPoints(), false));
        }
        else {
            playerEntity.sendMessage(new StringTextComponent("Not Enough BeNM Points (Need " + this.cost + ")"));
        }
        return flag;
    }

    public void sendPackets(int jutsuID, boolean hasJutsu) {
        NetworkLoader.INSTANCE.sendToServer(new PacketSetJutsuBoolean(jutsuID, hasJutsu, false));
    }
}
