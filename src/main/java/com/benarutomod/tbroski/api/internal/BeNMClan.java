package com.benarutomod.tbroski.api.internal;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.enums.Nature;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.init.DojutsuInit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class BeNMClan {

    private static final ResourceLocation SYMBOL_LOCATION = new ResourceLocation(Main.MODID, "textures/gui/symbols.png");

    private final String clan;
    private final int weight;

    private BeNMDojutsu dojutsu = DojutsuInit.NULL;
    private String message = "";
    private Nature nature = Nature.NULL;
    private float startingChakra = 300F;
    private int startingTaijutsu = 0;
    private int startingGenjutsu = 0;
    private int u;
    private int v;
    private ResourceLocation resourceLocation;
    private boolean hasSymbol;
    private boolean hasNPC;

    private IRegisterWidgets registerWidgets = null;
    private IRenderGUI renderGUI = null;
    private boolean hasDedicatedTab;


    public BeNMClan(String clan, int weight, boolean hasNPC) {
        this.clan = clan;
        this.weight = weight;
        this.hasNPC = hasNPC;
    }

    public BeNMClan(String clan, int weight, boolean hasNPC, int u, int v) {
        this(clan, weight, hasNPC);
        this.u = u;
        this.v = v;
        this.resourceLocation = SYMBOL_LOCATION;
        this.hasSymbol = true;
    }

    public BeNMClan(String clan, int weight, boolean hasNPC, int u, int v, ResourceLocation resourceLocation) {
        this(clan, weight, hasNPC);
        this.u = u;
        this.v = v;
        this.resourceLocation = resourceLocation;
        this.hasSymbol = true;
    }

    public int getWeight() {
        return weight;
    }

    public String getString() {
        return this.clan;
    }

    public BeNMClan setClanDojutsu(BeNMDojutsu dojutsu) {
        this.dojutsu = dojutsu;
        return this;
    }

    public BeNMClan setClanMessage(String message) {
        this.message = message;
        return this;
    }

    public BeNMClan setClanNature(Nature nature) {
        this.nature = nature;
        return this;
    }

    public BeNMClan setStartingChakra(float amount) {
        this.startingChakra = amount;
        return this;
    }

    public BeNMClan setStartingTaijutsu(int amount) {
        this.startingTaijutsu = amount;
        return this;
    }

    public BeNMClan setStartingGenjutsu(int amount) {
        this.startingGenjutsu = amount;
        return this;
    }

    public BeNMClan addDedicatedTab(IRegisterWidgets registerWidgets, IRenderGUI renderGUI) {
        this.registerWidgets = registerWidgets;
        this.renderGUI = renderGUI;
        this.hasDedicatedTab = true;
        return this;
    }

    public BeNMDojutsu getClanDojutsu() {
        return this.dojutsu;
    }

    public String getClanMessage() {
        return this.message;
    }

    public Nature getClanNature() {
        return this.nature;
    }

    public float getStartingChakra() {
        return this.startingChakra;
    }

    public int getStartingTaijutsu() {
        return startingTaijutsu;
    }

    public int getStartingGenjutsu() {
        return startingGenjutsu;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public boolean hasSymbol() {
        return hasSymbol;
    }

    public boolean hasNPC() {
        return hasNPC;
    }

    public boolean hasDedicatedTab() {
        return hasDedicatedTab;
    }

    public void registerWidgets(Screen screen, IPlayerHandler playerCapability) {
        this.registerWidgets.registerWidgets(screen, playerCapability);
    }

    public void renderGUI(Screen screen, int p_render_1_, int p_render_2_, float p_render_3_, boolean isOpened) {
        this.renderGUI.renderGui(screen, p_render_1_, p_render_2_, p_render_3_, isOpened);
    }

    public interface IRegisterWidgets {
        void registerWidgets(Screen screen, IPlayerHandler playerCapability);
    }

    public interface IRenderGUI {
        void renderGui(Screen screen, int p_render_1_, int p_render_2_, float p_render_3_, boolean isOpened);
    }
}
