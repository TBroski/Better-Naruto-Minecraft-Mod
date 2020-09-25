package com.benarutomod.tbroski.api.internal.jutsu;

import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.BeNMClan;

public class BeNMClanJutsu extends BeNMJutsu {

    private final BeNMClan clan;

    public BeNMClanJutsu(IBeNMPlugin plugin, String registryName, BeNMClan clan, int beNMPointCost, float chakraCost, int u, int v, boolean toggle, IAction action) {
        super(plugin, registryName, Type.CLAN, beNMPointCost, chakraCost, u, v, toggle, action);
        this.clan = clan;
    }

    public BeNMClan getClan() {
        return this.clan;
    }
}
