package com.benarutomod.tbroski.common;

import net.minecraft.potion.Effect;

public class BeNMBody {

    private final String mode;
    private boolean flight;
    private Effect effect;

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

    public boolean allowsPlayerFlight() {
        return this.flight;
    }

    public Effect getPlayerEffect() {
        return this.effect;
    }
}