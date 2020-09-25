package com.benarutomod.tbroski.api.internal.jutsu;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.api.interfaces.IBeNMJutsuButtonPress;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraSync;
import com.benarutomod.tbroski.util.helpers.JutsuHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class BeNMJutsu {

    private static final ResourceLocation JUTSU_LOCATION = new ResourceLocation(Main.MODID, "textures/gui/jutsu.png");

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
    private ResourceLocation resourceLocation;
    private IExtraCheck extraCheck;
    private ICancelEventListener onCancelEvent;
    private IAttackEventListener onAttackEvent;
    private IDamageEventListener onDamageEvent;
    private IDeathEventListener onDeathEvent;

    public BeNMJutsu(IBeNMPlugin plugin, String registryName, Type type, int beNMPointCost, float chakraCost, int u, int v, boolean toggle, IAction action) {
        this.name = registryName;
        this.correlatedPlugin = plugin;
        this.type = type;
        this.toggle = toggle;
        this.u = u;
        this.v = v;
        this.cost = beNMPointCost;
        this.chakraCost = chakraCost * Config.COMMON.chakraCostMultiplier.get().floatValue();
        this.action = action;
        this.press = new IBeNMJutsuButtonPress() {
            @Override
            public void onPress(GuiButtonJutsu buttonJutsu, IPlayerHandler playerCapability) {
                if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                    boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                    if (didBuy) {
                        playerCapability.setJutsuBoolean(JutsuHelper.getJutsuFromString(buttonJutsu.getJutsuName()), true);
                        buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                    }
                }
            }
        };
        this.resourceLocation = JUTSU_LOCATION;
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

    public BeNMJutsu addAttackEventListener(IAttackEventListener onAttackEvent) {
        this.onAttackEvent = onAttackEvent;
        return this;
    }

    public BeNMJutsu addDamageEventListener(IDamageEventListener onDamageEvent) {
        this.onDamageEvent = onDamageEvent;
        return this;
    }

    public BeNMJutsu addDeathEventListener(IDeathEventListener onDeathEvent) {
        this.onDeathEvent = onDeathEvent;
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

    public void act(PlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1) {
        IPlayerHandler playercap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        if (this.extraCheck != null) {
         if (!this.extraCheck.check(playerIn, taijutsuModifier0, taijutsuModifier1)) {
             return;
         }
        }
        if (playercap.returnChakra() >= (this.getChakraCost() * ((100F - playercap.returnChakraControl()) * 0.01F))) {
            if (!playerIn.world.isRemote) {
                playercap.addChakra((-this.getChakraCost() * ((100F - playercap.returnChakraControl()) * 0.01F)));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new PacketChakraSync(playercap.returnChakra()));
            }
            if (!playercap.returnToggleJutsuMessage() && !this.isToggle() && !playerIn.world.isRemote)
                playerIn.sendMessage(new StringTextComponent(new TranslationTextComponent("jutsu." + this.getCorrelatedPlugin().getPluginId() + "." + this.getName()).getString() + "! " + + (-this.getChakraCost() * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
            this.action.action(playerIn, taijutsuModifier0, taijutsuModifier1, playercap);
        }
        else {
            if (isToggle()) {
                throwCancelEvent(playerIn);
                playerIn.getPersistentData().putBoolean(getCorrelatedPlugin().getPluginId() + "_" + getName(), false);
                //NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> playerIn), new PacketJutsuNBTSync(playerIn.getEntityId(), getCorrelatedPlugin().getPluginId() + "_" + getName(), false));
            }
            playerIn.sendStatusMessage(new TranslationTextComponent("jutsu." + Main.MODID + ".message.notenoughchakra"), true);
        }
    }

    public void update(GuiButtonJutsu jutsuButton, BeNMJutsu jutsu, IPlayerHandler playerCapability) {
        jutsuButton.setHasJutsu(playerCapability.hasJutsuBoolean(jutsu));
        //this.update.update(jutsuButton, playerCapability);
    }

    public void throwCancelEvent(PlayerEntity playerIn) {
        if (this.onCancelEvent != null)
            this.onCancelEvent.onCancel(playerIn);
    }
    public void throwAttackEvent(PlayerEntity attacker, LivingEntity target) {
        if (this.onAttackEvent != null)
            this.onAttackEvent.onAttack(attacker, target);
    }
    public boolean throwDamageEvent(float amount, DamageSource source, PlayerEntity defender) {
        if (this.onDamageEvent != null)
            return this.onDamageEvent.onDamage(amount, source, defender);
        return false;
    }
    public boolean throwDeathEvent(PlayerEntity dieingEntity) {
        if (this.onDeathEvent != null)
            return this.onDeathEvent.onDeath(dieingEntity);
        return false;
    }

    public void sync(IPlayerHandler playerCapability, BeNMJutsu jutsu, boolean has) {
        playerCapability.setJutsuBoolean(jutsu, has);
        //this.sync.update(playerCapability, has);
    }

    public IBeNMJutsuButtonPress getPress() {
        return this.press;
    }

    public float getChakraCost() {
        return this.chakraCost;
    }

    public boolean hasJutsu(BeNMJutsu jutsu, IPlayerHandler playerCapability) {
        return playerCapability.hasJutsuBoolean(jutsu);
        //return hasJutsu.has(playerCapability);
    }

    public enum Type {
        FIRE_NATURE, LIGHTNING_NATURE, EARTH_NATURE, WIND_NATURE, WATER_NATURE, MAGNET_NATURE, WOOD_NATURE, LAVA_NATURE, BOIL_NATURE, STORM_NATURE, SCORCH_NATURE, EXPLOSION_NATURE, ICE_NATURE, E_RANK, SHARINGAN_ABILITY, SIX_PATH_TECHNIQUE, BIJUU_ABILITY, CLAN
    }

    public interface IAction {
        void action(PlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1, IPlayerHandler playerCapability);
    }

    public interface IJutsuServerSync {
        void update(IPlayerHandler playerCapability, boolean has);
    }

    public interface IHasJutsu {
        boolean has(IPlayerHandler playerCapability);
    }

    public interface IExtraCheck {
        boolean check(PlayerEntity playerIn, int taijutsuModifier0, int taijutsuModifier1);
    }

    public interface ICancelEventListener {
        void onCancel(PlayerEntity playerIn);
    }

    public interface IAttackEventListener {
        void onAttack(PlayerEntity attacker, LivingEntity target);
    }

    public interface IDamageEventListener {
        boolean onDamage(float amount, DamageSource source, PlayerEntity defender);
    }

    public interface IDeathEventListener {
        boolean onDeath(PlayerEntity dieingEntity);
    }
}
