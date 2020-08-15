package com.benarutomod.tbroski.api.interfaces;

import com.benarutomod.tbroski.api.internal.BeNMDojutsu;

public interface IDojutsuEntity {

    BeNMDojutsu rightDojustsu();
    BeNMDojutsu leftDojustsu();

    void setRightDojustsu(BeNMDojutsu rightEye);
    void setLeftDojustsu(BeNMDojutsu leftEye);

    int eyeSlot();
}
