package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.BeNMRegistry;

public class ClanHelper {


    public static BeNMClan getClanFromString(String clan) {
        for (BeNMClan benmClan : BeNMRegistry.CLANS.getValues()) {
            if (benmClan.getString().equalsIgnoreCase(clan)) {
                return benmClan;
            }
        }
        return null;
    }
}
