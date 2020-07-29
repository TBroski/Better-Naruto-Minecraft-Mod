package com.benarutomod.tbroski.init;

import net.minecraft.util.DamageSource;

public class DamageInit {

    public static final DamageSource DOJUTSU = new DamageSource("dojutsu").setDamageBypassesArmor();
    public static final DamageSource AMATERASU = new DamageSource("amaterasu").setDamageBypassesArmor();
    public static final DamageSource DODGED = new DamageSource("dodged");
}
