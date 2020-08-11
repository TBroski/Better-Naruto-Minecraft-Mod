package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.common.enums.Nature;

import java.util.ArrayList;

public class ClanInit {

    public static final BeNMClan NULL = new BeNMClan("null", 0);

    public static final BeNMClan UCHIHA = new BeNMClan("uchiha", 1).setClanDojutsu(DojutsuInit.SHARINGAN).setClanNature(Nature.FIRE).setClanMessage("Your eyes sting, an Uchiha?");
    public static final BeNMClan HYUGA = new BeNMClan("hyuga", 3).setClanDojutsu(DojutsuInit.BYAKUGAN).setClanMessage("You feel enriched with a special prowess, a Hyuga?");
    public static final BeNMClan UZUMAKI = new BeNMClan("uzumaki", 3).setStartingChakra(400F).setClanMessage("You feel alone, an Uzumaki?");
}
