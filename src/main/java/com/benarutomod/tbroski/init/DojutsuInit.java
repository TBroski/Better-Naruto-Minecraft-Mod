package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.common.BeNMDojutsu;
import net.minecraft.util.DamageSource;

import java.util.ArrayList;

public class DojutsuInit {
    
    public static final BeNMDojutsu NULL = new BeNMDojutsu("null", BeNMDojutsu.Type.MISC, 0);
    public static final BeNMDojutsu EMPTY = new BeNMDojutsu("empty", BeNMDojutsu.Type.MISC, 0);

    public static final BeNMDojutsu SHARINGAN = new BeNMDojutsu("sharingan", BeNMDojutsu.Type.SHARINGAN, 0).setCanDodgeDamage();
    public static final BeNMDojutsu MANGEKYOU_SHARINGAN = new BeNMDojutsu("mangekyou_sharingan", BeNMDojutsu.Type.SHARINGAN, 0).setCanDodgeDamage();
    public static final BeNMDojutsu RINNEGAN = new BeNMDojutsu("rinnegan", BeNMDojutsu.Type.SHARINGAN, 1).setCanDodgeDamage().setCanUseSixPathsTechnique();
    public static final BeNMDojutsu BYAKUGAN = new BeNMDojutsu("byakugan", BeNMDojutsu.Type.BYAKUGAN, 1).setCanSeeChakra().setRestrictsChakra();
}
