package com.benarutomod.tbroski.common;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

public class BeNMJutsu {

    private final String name;
    private final IBeNMPlugin correlatedPlugin;
    private final Type type;
    private final boolean toggle;
    private final int u;
    private final int v;
    private final int cost;
    private final float chakraCost;
    private final IAction action;
    private final IGUIJutsuButtonPress press;
    private final IGUIJutsuUpdateButton update;
    private final IJutsuServerSync sync;
    private final IHasJutsu hasJutsu;
    private ResourceLocation resourceLocation;
    private IExtraCheck extraCheck;

    public BeNMJutsu(IBeNMPlugin plugin, String registryName, Type type, int beNMPointCost, float chakraCost, int u, int v, boolean toggle, IAction action, IGUIJutsuButtonPress buttonPress, IGUIJutsuUpdateButton updateButton, IJutsuServerSync serverSync, IHasJutsu hasJutsu) {
        this.name = registryName;
        this.correlatedPlugin = plugin;
        this.type = type;
        this.toggle = toggle;
        this.u = u;
        this.v = v;
        this.cost = beNMPointCost;
        this.chakraCost = chakraCost;
        this.action = action;
        this.press = buttonPress;
        this.update = updateButton;
        this.hasJutsu = hasJutsu;
        this.sync = serverSync;

        this.resourceLocation = new ResourceLocation(Main.MODID, "textures/gui/jutsu.png");
    }

    public IBeNMPlugin getCorrelatedPlugin() {
        return correlatedPlugin;
    }

    public BeNMJutsu setResourceLocationForGUI(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
        return this;
    }

    public BeNMJutsu setExtraJutsuChecks(IExtraCheck extraCheck) {
        this.extraCheck = extraCheck;
        return this;
    }

    public ResourceLocation getResourceLocationForGUI() {
        return this.resourceLocation;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public boolean isToggle() {
        return toggle;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void act(ServerPlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1) {
        IPlayerHandler playercap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        if (this.extraCheck != null) {
         if (!this.extraCheck.check(playerIn, taijutsuModifier0, taijutsuModifier1)) {
             return;
         }
        }
        if (playercap.returnChakra() >= (this.getChakraCost() * ((100F - playercap.returnChakraControl()) * 0.01F))) {
            playercap.addChakra((-this.getChakraCost() * ((100F - playercap.returnChakraControl()) * 0.01F)));
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent(new TranslationTextComponent("jutsu." + this.getCorrelatedPlugin().getPluginId() + "." + this.getName()).getString() + "! " + + (-this.getChakraCost() * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
            action.action(playerIn, taijutsuModifier0, taijutsuModifier1, playercap);
        }
        else {
            playerIn.sendStatusMessage(new TranslationTextComponent("jutsu." + Main.MODID + ".message.notenoughchakra"), true);
        }
    }

    public void update(GuiButtonJutsu jutsuButton, IPlayerHandler playerCapability) {
        update.update(jutsuButton, playerCapability);
    }

    public void sync(IPlayerHandler playerCapability, boolean has) {
        sync.update(playerCapability, has);
    }

    public IGUIJutsuButtonPress getPress() {
        return press;
    }

    public float getChakraCost() {
        return chakraCost;
    }

    public boolean hasJutsu(IPlayerHandler playerCapability) {
        return hasJutsu.has(playerCapability);
    }

    public enum Type {
        FIRE_NATURE, LIGHTNING_NATURE, EARTH_NATURE, WIND_NATURE, WATER_NATURE, MAGNET_NATURE, E_RANK, SHARINGAN_ABILITY, EIGHT_PATH_TECHNIQUE
    }

    public interface IAction {
        void action(ServerPlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1, IPlayerHandler playerCapability);
    }

    @OnlyIn(Dist.CLIENT)
    public interface IGUIJutsuUpdateButton {
        void update(GuiButtonJutsu buttonJutsu, IPlayerHandler playerCapability);
    }

    @OnlyIn(Dist.CLIENT)
    public interface IGUIJutsuButtonPress extends Button.IPressable {
        void onPress(GuiButtonJutsu p_onPress_1_, IPlayerHandler playerCapability);

        @Override
        default void onPress(Button p_onPress_1_) {
            if (p_onPress_1_ instanceof GuiButtonJutsu) {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

                onPress((GuiButtonJutsu) p_onPress_1_, playerc);
            }
        }
    }

    public interface IJutsuServerSync {
        void update(IPlayerHandler playerCapability, boolean has);
    }

    public interface IHasJutsu {
        boolean has(IPlayerHandler playerCapability);
    }

    public interface IExtraCheck {
        boolean check(ServerPlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1);
    }
}
