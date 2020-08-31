package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;

public class JutsuHelper {

    public static BeNMJutsu getJutsuFromString(String registryName) {
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            if (jutsu.getName().equalsIgnoreCase(registryName))
                return jutsu;
        }
        return null;
    }
}
