package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.api.internal.BeNMDojutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.init.DojutsuInit;

public class DojutsuHelper {

    public static boolean dojutsuNotNull(BeNMDojutsu dojutsu) {
        boolean dojutsuBoolean = !(dojutsu == DojutsuInit.NULL) && !(dojutsu == DojutsuInit.EMPTY);
        return dojutsuBoolean;
    }
    
    public static BeNMDojutsu getDojutsuFromString(String dojutsu) {
        for (BeNMDojutsu benmDojutsu : BeNMRegistry.DOJUTSUS.getValues()) {
            if (benmDojutsu.getString().equalsIgnoreCase(dojutsu)) {
                return benmDojutsu;
            }
        }
        return null;
    }
}
