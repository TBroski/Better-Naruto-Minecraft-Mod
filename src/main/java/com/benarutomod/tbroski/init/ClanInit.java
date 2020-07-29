package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.common.BeNMClan;

import java.util.ArrayList;

public class ClanInit {

    public static final ArrayList<BeNMClan> CLANS = new ArrayList<>();

    public static final BeNMClan NULL = new BeNMClan("null");

    public static final BeNMClan UCHIHA = new BeNMClan("uchiha").setClanDojutsu(DojutsuInit.SHARINGAN).setClanNature(BeNMClan.Nature.FIRE).setClanMessage("Your eyes sting, an Uchiha?");
    public static final BeNMClan HYUGA = new BeNMClan("hyuga").setClanDojutsu(DojutsuInit.BYAKUGAN).setClanMessage("You feel enriched with a special prowess, a Hyuga?");
    public static final BeNMClan UZUMAKI = new BeNMClan("uzumaki").setStartingChakra(400F).setClanMessage("You feel alone, an Uzumaki?");

    public static void register() {
        CLANS.add(NULL);
        CLANS.add(UCHIHA);
        CLANS.add(HYUGA);
        CLANS.add(UZUMAKI);
    }
}
