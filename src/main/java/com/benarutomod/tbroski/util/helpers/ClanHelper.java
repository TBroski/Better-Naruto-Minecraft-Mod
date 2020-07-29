package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.init.ClanInit;

public class ClanHelper {


    public static BeNMClan getClanFromString(String clan) {
        for (BeNMClan benmClan : ClanInit.CLANS) {
            if (benmClan.getString().equalsIgnoreCase(clan)) {
                return benmClan;
            }
        }
        return null;
    }
}
