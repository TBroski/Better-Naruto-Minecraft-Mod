package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMSharingan;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.init.DojutsuInit;

public class DojutsuHelper {

    public static BeNMDojutsu getDojutsuFromString(String dojutsu) {
        for (BeNMDojutsu benmDojutsu : BeNMRegistry.DOJUTSUS.getValues()) {
            if (benmDojutsu.getString().equalsIgnoreCase(dojutsu)) {
                return benmDojutsu;
            }
        }
        return null;
    }

    public static BeNMSharingan getBestSharinganByLevel(IPlayerHandler playerCap) {
        int numSharingans = 0;
        for (BeNMDojutsu dojutsu : new BeNMDojutsu[] {playerCap.returnPlayerLeftDojutsu(), playerCap.returnPlayerRightDojutsu()}) {
            if (dojutsu instanceof BeNMSharingan) {
                numSharingans++;
            }
        }
        if (numSharingans == 0)
            return null;
        if (numSharingans == 1) {
            if (playerCap.returnPlayerRightDojutsu() instanceof BeNMSharingan)
                return (BeNMSharingan) playerCap.returnPlayerRightDojutsu();
            if (playerCap.returnPlayerLeftDojutsu() instanceof BeNMSharingan) {
                return (BeNMSharingan) playerCap.returnPlayerLeftDojutsu();
            }
        }
        if (((BeNMSharingan) playerCap.returnPlayerLeftDojutsu()).getLevel() >= ((BeNMSharingan) playerCap.returnPlayerRightDojutsu()).getLevel())
            return (BeNMSharingan) playerCap.returnPlayerLeftDojutsu();
        return (BeNMSharingan) playerCap.returnPlayerRightDojutsu();
    }
}
