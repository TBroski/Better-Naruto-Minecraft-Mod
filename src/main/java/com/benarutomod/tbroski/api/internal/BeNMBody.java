package com.benarutomod.tbroski.api.internal;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.player.body.AbstractBodyScreen;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.api.interfaces.IBeNMJutsuButtonPress;
import com.benarutomod.tbroski.api.interfaces.IBeNMJutsuButtonUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class BeNMBody {

    private final String mode;
    private IBeNMPlugin plugin;
    private boolean flight;
    private List<Effect> effects = new ArrayList<>();
    private Effect attackingEffect;
    private AgeableModel model;
    private int dojutsuSize = -1;
    private int dojutsuOffset;
    private ResourceLocation modelTexture;
    private ResourceLocation dojutsuTexture;
    private ResourceLocation guiTexture;
    private final IBeNMJutsuButtonPress buttonPress;
    private final IBeNMJutsuButtonUpdate buttonUpdate;
    private final int u;
    private final int v;

    public BeNMBody(IBeNMPlugin pluginIn, String mode, int u, int v, IBeNMJutsuButtonPress buttonPress, IBeNMJutsuButtonUpdate buttonUpdate) {
        this.plugin = pluginIn;
        this.mode = mode;
        this.u = u;
        this.v = v;
        this.buttonPress = buttonPress;
        this.buttonUpdate = buttonUpdate;

        this.guiTexture = new ResourceLocation(Main.MODID, "textures/gui/jutsu.png");
    }

    public BeNMBody(IBeNMPlugin pluginIn, String mode, int u, int v, IBeNMJutsuButtonUpdate buttonUpdate) {
        this.plugin = pluginIn;
        this.mode = mode;
        this.u = u;
        this.v = v;
        this.buttonPress = (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractBodyScreen) {
                buttonJutsu.doBodyPress((AbstractBodyScreen) Minecraft.getInstance().currentScreen, this);
            }
        };
        this.buttonUpdate = buttonUpdate;

        this.guiTexture = new ResourceLocation(Main.MODID, "textures/gui/jutsu.png");
    }

    public String getName() {
        return this.mode;
    }

    public IBeNMPlugin getCorrelatedPlugin() {
        return plugin;
    }

    public BeNMBody setPlugin(IBeNMPlugin pluginIn) {
        this.plugin = pluginIn;
        return this;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public BeNMBody setResourceLocationForGUI(ResourceLocation resourceLocation) {
        this.guiTexture = resourceLocation;
        return this;
    }

    public BeNMBody setAllowsPlayerFlight() {
        this.flight = true;
        return this;
    }

    public BeNMBody addPlayerEffect(Effect effect) {
        this.effects.add(effect);
        return this;
    }

    public BeNMBody setAttackingEffect(Effect effect) {
        this.attackingEffect = effect;
        return this;
    }

    public BeNMBody setModelOnRender(AgeableModel model, ResourceLocation texture) {
        this.model = model;
        this.modelTexture = texture;
        return this;
    }

    public BeNMBody setDojutsuModelOnRender(int size, int offset, ResourceLocation texture) {
        this.dojutsuSize = size;
        this.dojutsuOffset = offset;
        this.dojutsuTexture = texture;
        return this;
    }

    public boolean allowsPlayerFlight() {
        return this.flight;
    }

    public List<Effect> getPlayerEffects() {
        return this.effects;
    }

    public Effect getAttackingEffect() {
        return this.attackingEffect;
    }

    public AgeableModel getModelOnRender() {
        return this.model;
    }

    public int getDojutsuSizeOnRender() {
        return this.dojutsuSize;
    }

    public int getDojutsuOffsetOnRender() {
        return this.dojutsuOffset;
    }

    public ResourceLocation getModelResourceLocationOnRender() {
        return this.modelTexture;
    }

    public ResourceLocation getDojutsuResourceLocationOnRender() {
        return this.dojutsuTexture;
    }

    public ResourceLocation getResourceLocationForGUI() {
        return this.guiTexture;
    }

    public void update(GuiButtonJutsu buttonBody, IPlayerHandler playerCapability) {
        buttonUpdate.update(buttonBody, playerCapability);
    }

    public IBeNMJutsuButtonPress getPress() {
        return this.buttonPress;
    }
}
