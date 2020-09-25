package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMClanJutsu;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;

import java.util.ArrayList;
import java.util.List;

public class JutsuHelper {

    private static List<BeNMClanJutsu> beNMClanJutsus = new ArrayList<>();

    public static BeNMJutsu getJutsuFromString(String registryName) {
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            if (jutsu.getName().equalsIgnoreCase(registryName))
                return jutsu;
        }
        return null;
    }

    public static List<BeNMClanJutsu> getClanJutsus() {
        return beNMClanJutsus;
    }

    public static void create() {
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            if (jutsu instanceof BeNMClanJutsu) {
                beNMClanJutsus.add((BeNMClanJutsu) jutsu);
            }
        }
    }
}
