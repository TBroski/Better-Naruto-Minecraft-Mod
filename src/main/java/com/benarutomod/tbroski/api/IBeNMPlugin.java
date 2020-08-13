package com.benarutomod.tbroski.api;

import com.benarutomod.tbroski.api.BeNMRegistry;

/*
* The main interface to register new dojutsu (like sharingan),
* clans (like Uzumaki), "body modes" (like Toad Sage Mode), jutsu (like Clone Jutsu) with BeNM.
* IBeNMPlugins must have the {@link BeNMPlugin} annotation to get loaded by BeNM.
 */
public interface IBeNMPlugin {

    String getPluginId();

    void registerNewDojutsus(BeNMRegistry.DojutsuRegistry dojutsuRegistry);

    void registerNewClans(BeNMRegistry.ClanRegistry clanRegistry);

    void registerNewBodyModes(BeNMRegistry.BodyModeRegistry bodyModeRegistry);

    void registerNewJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry);
}
