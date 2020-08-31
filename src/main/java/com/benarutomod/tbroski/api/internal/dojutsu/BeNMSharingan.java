package com.benarutomod.tbroski.api.internal.dojutsu;

public class BeNMSharingan extends BeNMDojutsu {

    private final float level;

    public BeNMSharingan(String dojutsu, int size, float level) {
        super(dojutsu, Type.SHARINGAN, size);
        this.level = level;
    }

    public float getLevel() {
        return level;
    }
}
