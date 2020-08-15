package com.benarutomod.tbroski.api.internal;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.api.interfaces.IBeNMJutsuButtonPress;
import com.benarutomod.tbroski.api.interfaces.IBeNMJutsuButtonUpdate;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraSync;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsuNBTSync;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class BeNMJutsu {

    private final String name;
    private IBeNMPlugin correlatedPlugin;
    private final Type type;
    private final boolean toggle;
    private final int u;
    private final int v;
    private final int cost;
    private final float chakraCost;
    private final IAction action;
    private final IBeNMJutsuButtonPress press;
    private final IBeNMJutsuButtonUpdate update;
    private final IJutsuServerSync sync;
    private final IHasJutsu hasJutsu;
    private ResourceLocation resourceLocation;
    private IExtraCheck extraCheck;
    private ICancelEventListener onCancelEvent;

    public BeNMJutsu(IBeNMPlugin plugin, String registryName, Type type, int beNMPointCost, float chakraCost, int u, int v, boolean toggle, IAction action, IBeNMJutsuButtonPress buttonPress, IBeNMJutsuButtonUpdate updateButton, IJutsuServerSync serverSync, IHasJutsu hasJutsu) {
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

    public BeNMJutsu setPlugin(IBeNMPlugin pluginIn) {
        this.correlatedPlugin = pluginIn;
        return this;
    }

    public BeNMJutsu setResourceLocationForGUI(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
        return this;
    }

    public BeNMJutsu setExtraJutsuChecks(IExtraCheck extraCheck) {
        this.extraCheck = extraCheck;
        return this;
    }

    public BeNMJutsu addCancelEventListener(ICancelEventListener onCancelEvent) {
        this.onCancelEvent = onCancelEvent;
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
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> playerIn), new PacketChakraSync(playercap.returnChakra()));
            if (!playercap.returnToggleJutsuMessage() && !this.isToggle()) playerIn.sendMessage(new StringTextComponent(new TranslationTextComponent("jutsu." + this.getCorrelatedPlugin().getPluginId() + "." + this.getName()).getString() + "! " + + (-this.getChakraCost() * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
            this.action.action(playerIn, taijutsuModifier0, taijutsuModifier1, playercap);
        }
        else {
            if (isToggle()) {
                throwCancelEvent(playerIn);
                playerIn.getPersistentData().putBoolean(getCorrelatedPlugin().getPluginId() + "_" + getName(), false);
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerIn), new PacketJutsuNBTSync(playerIn.getEntityId(), getCorrelatedPlugin().getPluginId() + "_" + getName(), false));
            }
            playerIn.sendStatusMessage(new TranslationTextComponent("jutsu." + Main.MODID + ".message.notenoughchakra"), true);
        }
    }

    public void update(GuiButtonJutsu jutsuButton, IPlayerHandler playerCapability) {
        this.update.update(jutsuButton, playerCapability);
    }

    public void throwCancelEvent(ServerPlayerEntity playerIn) {
        if (this.onCancelEvent != null) this.onCancelEvent.onCancel(playerIn);
    }

    public void sync(IPlayerHandler playerCapability, boolean has) {
        this.sync.update(playerCapability, has);
    }

    public IBeNMJutsuButtonPress getPress() {
        return this.press;
    }

    public float getChakraCost() {
        return this.chakraCost;
    }

    public boolean hasJutsu(IPlayerHandler playerCapability) {
        return hasJutsu.has(playerCapability);
    }

    public enum Type {
        FIRE_NATURE, LIGHTNING_NATURE, EARTH_NATURE, WIND_NATURE, WATER_NATURE, MAGNET_NATURE, E_RANK, SHARINGAN_ABILITY, SIX_PATH_TECHNIQUE, BIJUU_ABILITY
    }

    public interface IAction {
        void action(ServerPlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1, IPlayerHandler playerCapability);
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

    public interface ICancelEventListener {
        void onCancel(ServerPlayerEntity playerIn);
    }
}
