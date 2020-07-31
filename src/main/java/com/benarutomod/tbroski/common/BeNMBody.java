package com.benarutomod.tbroski.common;

import net.minecraft.potion.Effect;

public class BeNMBody {

    private final String mode;
    private boolean flight;
    private Effect effect;
    private Effect attackingEffect;

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

    public boolean allowsPlayerFlight() {
        return this.flight;
    }

    public Effect getPlayerEffect() {
        return this.effect;
    }

    public Effect getAttackingEffect() {
        return this.attackingEffect;
    }
}
