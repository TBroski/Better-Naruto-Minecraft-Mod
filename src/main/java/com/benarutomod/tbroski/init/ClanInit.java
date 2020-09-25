package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.enums.Nature;
import net.minecraft.client.Minecraft;

public class ClanInit {

    public static final BeNMClan NULL = new BeNMClan("null", 0, false);

    public static final BeNMClan UCHIHA = new BeNMClan("uchiha", 1, true, 236, 0).setStartingGenjutsu(5).setClanDojutsu(DojutsuInit.SHARINGAN).setClanNature(Nature.FIRE).setClanMessage("Your eyes sting, an Uchiha?");
    public static final BeNMClan HYUGA = new BeNMClan("hyuga", 3, true, 236, 20).setStartingTaijutsu(5).setClanDojutsu(DojutsuInit.BYAKUGAN).setClanMessage("You feel enriched with a special prowess, a Hyuga?");
    public static final BeNMClan UZUMAKI = new BeNMClan("uzumaki", 3, true, 236, 40).setStartingChakra(400F).setClanMessage("You feel alone, an Uzumaki?");
}
