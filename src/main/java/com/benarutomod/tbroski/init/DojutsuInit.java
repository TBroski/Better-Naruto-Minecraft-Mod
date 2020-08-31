package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMSharingan;

public class DojutsuInit {
    
    public static final BeNMDojutsu NULL = new BeNMDojutsu("null", BeNMDojutsu.Type.MISC, 0);

    public static final BeNMDojutsu SHARINGAN = new BeNMSharingan("sharingan", 0, 1F).setCanDodgeDamage();
    public static final BeNMDojutsu MANGEKYOU_SHARINGAN = new BeNMSharingan("mangekyou_sharingan", 0, 1.2F).setCanDodgeDamage();
    public static final BeNMDojutsu ETERNAL_MANGEKYOU_SHARINGAN = new BeNMSharingan("eternal_mangekyou_sharingan", 0, 1.4F).setCanDodgeDamage();
    public static final BeNMDojutsu RINNEGAN = new BeNMSharingan("rinnegan", 1, 2.1F).setCanDodgeDamage().setCanUseSixPathsTechnique();
    public static final BeNMDojutsu BYAKUGAN = new BeNMDojutsu("byakugan", BeNMDojutsu.Type.BYAKUGAN, 1).setCanSeeChakra().setRestrictsChakra();
}
