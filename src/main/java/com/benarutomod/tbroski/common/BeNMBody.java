package com.benarutomod.tbroski.common;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;

public class BeNMBody {

    private final String mode;
    private boolean flight;
    private Effect effect;
    private Effect attackingEffect;
    private AgeableModel model;
    private int dojutsuSize = -1;
    private int dojutsuOffset;
    private ResourceLocation modelTexture;
    private ResourceLocation dojutsuTexture;

    public BeNMBody(String mode) {
        this.mode = mode;
    }

    public String getString() {
        return this.mode;
    }

    public BeNMBody setAllowsPlayerFlight() {
        this.flight = true;
        return this;
    }

    public BeNMBody setPlayerEffect(Effect effect) {
        this.effect = effect;
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

    public Effect getPlayerEffect() {
        return this.effect;
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
}
